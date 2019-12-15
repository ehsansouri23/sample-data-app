package com.example.sampledataapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampledataapp.adapter.AddressAdapter
import com.example.sampledataapp.data.Repository
import com.example.sampledataapp.data.Result
import com.example.sampledataapp.network.ApiFactory
import com.example.sampledataapp.util.SpaceItemDecoration
import com.example.sampledataapp.util.toast
import com.example.sampledataapp.viewmodel.MainViewModel
import com.example.sampledataapp.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_addresses.*

class AddressesActivity : AppCompatActivity() {

    private val addressAdapter: AddressAdapter by lazy { AddressAdapter() }
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, MainViewModelFactory(Repository(ApiFactory.api)))
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addresses)
        initList()
        observeAddresses()
        viewModel.getAddresses()

    }

    private fun initList() {
        addresses_list.apply {
            adapter = addressAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SpaceItemDecoration(resources.getDimension(R.dimen.margin_s)))
        }
    }

    private fun observeAddresses() {
        viewModel.addresses.observe(this, Observer {
            when (val addresses = it ?: return@Observer) {
                is Result.Loading -> showLoading()
                is Result.Error -> {
                    toast(getString(R.string.error))
                    showData()
                }
                is Result.Success -> {
                    showData()
                    addressAdapter.addresses = addresses.data!!
                    addressAdapter.notifyDataSetChanged()
                }
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
