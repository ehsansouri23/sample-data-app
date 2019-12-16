package com.example.sampledataapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

const val LOCATION = 1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        selectAddress.setOnClickListener {
            val mapActivity = Intent(this, MapsActivity::class.java)
            startActivityForResult(mapActivity, LOCATION)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == LOCATION) {
            if (resultCode == Activity.RESULT_OK) {
                val lat = data?.extras?.get(LAT)
                val long = data?.extras?.get(LONG)
                location.setText("$lat , $long")
            }
        }
    }
}
