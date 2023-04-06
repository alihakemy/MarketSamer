package com.hakemy.marketsamer.ui.addAddress.getLocation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivityGetLocationBinding
import com.hakemy.marketsamer.ui.register.RegisterActivity
import com.hakemy.marketsamer.utils.CurrentLocation
import com.hakemy.marketsamer.utils.MapUtil.addMarker
import com.hakemy.marketsamer.utils.MapUtil.converter
import com.hakemy.marketsamer.utils.MapUtil.getAddressLine
import com.hakemy.marketsamer.utils.SharePreferenceManager

class GetLocationActivity : BaseActivity() {
    companion object{

        fun startGetLocationActivity(context: Context){
            if(SharePreferenceManager.getIsVerified().not()){
                RegisterActivity.startRegisterActivity(context)

                return
            }
            val intent= Intent(context, GetLocationActivity::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

        }

    }
    lateinit var binding:ActivityGetLocationBinding
    private lateinit var map: GoogleMap

    private lateinit var address: String
    private var lat =30.0
    private var lng =31.0

    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap
        currentLocation(map)
        pickLocation(map)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityGetLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        with(binding) {
            btnChoose.setOnClickListener { setAddress(lat.toString(), lng.toString(), address) }
        }
    }
    private fun currentLocation(map: GoogleMap) {
        val instance = CurrentLocation.getInstance(this)
        instance.observe(this) {
            lat = it?.latitude!!
            lng = it?.longitude!!

            addMarker(
                this,
                map,
                converter(lat, lng),
            )
            address = getAddressLine(this, converter(lat, lng))!!
        }
    }
    private fun pickLocation(map: GoogleMap) {
        map.setOnMapClickListener {
            lat = it.latitude
            lng = it.longitude
            address = getAddressLine(this, converter(it.latitude.toDouble(),
                it.longitude.toDouble())).toString()
            //
            map.clear()
            addMarker(
                this,
                map,
                converter(lat.toDouble(), lng.toDouble()),
            )
        }
    }
    private fun setAddress(lat: String, lng: String, address: String) {

        SharePreferenceManager.storelat(lat.toString())
        SharePreferenceManager.storelng(lng.toString())
        SharePreferenceManager.storeaddress(address.toString())
        onBackPressed()


    }
}