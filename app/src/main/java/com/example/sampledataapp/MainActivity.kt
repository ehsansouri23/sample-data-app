package com.example.sampledataapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.sampledataapp.data.Repository
import com.example.sampledataapp.data.Result
import com.example.sampledataapp.data.model.Address
import com.example.sampledataapp.network.ApiFactory
import com.example.sampledataapp.util.toast
import com.example.sampledataapp.viewmodel.MainViewModel
import com.example.sampledataapp.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_addresses.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.mobile
import kotlinx.android.synthetic.main.activity_main.progress
import kotlinx.android.synthetic.main.address_item.*

const val LOCATION = 1

class MainActivity : AppCompatActivity() {

    private var lat: Float = 0F
    private var long: Float = 0F
    private var gender = "male"
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, MainViewModelFactory(Repository(ApiFactory.api)))
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        selectAddress.setOnClickListener {
            val mapActivity = Intent(this, MapsActivity::class.java)
            startActivityForResult(mapActivity, LOCATION)
        }
        next.setOnClickListener {
            val address = Address(
                0,
                address.text.toString(),
                lat.toInt(),
                long.toInt(),
                mobile.text.toString().toInt(),
                phone.text.toString().toInt(),
                firstName.text.toString(),
                lastName.text.toString(),
                gender
            )
            viewModel.addAddress(address)
        }
        genderField.setOnCheckedChangeListener { buttonView, isChecked ->
            gender = if (isChecked) "female" else "male"
        }
        observeAddAddress()
    }

    private fun observeAddAddress() {
        viewModel.addAddress.observe(this, Observer { addAddress ->
            when (addAddress ?: return@Observer) {
                is Result.Loading -> showLoading()
                is Result.Error -> {
                    toast(getString(R.string.error))
                    showData()
                }
                is Result.Success -> {
                    showData()
                    val allAddresses = Intent(this@MainActivity, AddressesActivity::class.java)
                    startActivity(allAddresses)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == LOCATION) {
            if (resultCode == Activity.RESULT_OK) {
                lat = data?.extras?.getFloat(LAT) ?: 0F
                long = data?.extras?.getFloat(LONG) ?: 0F
                location.setText("$lat , $long")
            }
        } else super.onActivityResult(requestCode, resultCode, data)
    }
}
