package com.example.sampledataapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.sampledataapp.data.Repository
import com.example.sampledataapp.data.Result
import com.example.sampledataapp.network.ApiFactory
import com.example.sampledataapp.viewmodel.MainViewModel
import com.example.sampledataapp.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_addresses.*

class AddressesActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, MainViewModelFactory(Repository(ApiFactory.api)))
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addresses)
        observeAddresses()
        viewModel.getAddresses()

    }

    fun observeAddresses() {
        viewModel.addresses.observe(this, Observer {
            val addresses = it ?: return@Observer
            when (addresses) {
                Result.Loading -> showLoading()
            }
        })
    }

    private fun showLoading() {
        progress.visibility = View.VISIBLE
        addresses_list.visibility = View.GONE
    }

    private fun showData() {
        progress.visibility = View.GONE
        addresses_list.visibility = View.VISIBLE
    }

}
