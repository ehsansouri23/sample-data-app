package com.example.sampledataapp

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Point
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.sampledataapp.util.toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_maps.*


const val LOCATION_PERMISSION = 110

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        if (!permissionGranted())
            requestPermission()
    }

    private fun permissionGranted() = ContextCompat.checkSelfPermission(
        applicationContext,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this@MapsActivity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION
        )
    }

    private fun configPin() {
        val pin = ImageView(this)
        pin.setImageResource(R.drawable.ic_pin_drop_black_24dp)
        val params = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            Gravity.CENTER
        )
        val density = resources.displayMetrics.density
        params.bottomMargin = (12 * density).toInt()
        pin.layoutParams = params
        addContentView(pin, params)
        selectAddress.setOnClickListener {
            val position = mMap.projection.fromScreenLocation(
                Point((pin.left + pin.width / 2), pin.bottom)
            )

        }
    }

    private fun configMap() {
        mMap.apply {
            isMyLocationEnabled = true
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (permissionGranted())
            configMap()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                configMap()
            } else
                toast(getString(R.string.must_accept_address_permission))
        }

    }
}
