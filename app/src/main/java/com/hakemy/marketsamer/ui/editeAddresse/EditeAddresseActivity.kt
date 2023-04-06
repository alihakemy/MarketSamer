package com.hakemy.marketsamer.ui.editeAddresse

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivityEditeAddresseBinding
import com.hakemy.marketsamer.ui.addAddress.AddAddressViewModel
import com.hakemy.marketsamer.ui.addAddress.adapter.GovernmentsAdapter
import com.hakemy.marketsamer.ui.addAddress.adapter.RegionsAdapter
import com.hakemy.marketsamer.ui.addAddress.fetchText
import com.hakemy.marketsamer.ui.addAddress.getLocation.GetLocationActivity
import com.hakemy.marketsamer.ui.addAddress.models.AddressItem
import com.hakemy.marketsamer.ui.addAddress.models.RegionItem
import com.hakemy.marketsamer.ui.register.RegisterActivity
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.SharePreferenceManager

class EditeAddresseActivity : BaseActivity() {


    companion object {

        fun startEditeAddresseActivity(
            context: Context,
            address: com.hakemy.marketsamer.ui.chooseAddresse.response.AddressItem
        ) {
            if (SharePreferenceManager.getIsVerified().not()) {
                RegisterActivity.startRegisterActivity(context)

                return
            }
            val intent = Intent(context, EditeAddresseActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("AddressItem",address)
            context.startActivity(intent)

        }

    }
    lateinit var binding: ActivityEditeAddresseBinding
    private lateinit var governmentsAdapter: GovernmentsAdapter
    private lateinit var regionsAdapter: RegionsAdapter
    lateinit var viewModel: AddAddressViewModel

    private var lat: String = ""
    private var lng: String = ""

    private var type = "house"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityEditeAddresseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel= ViewModelProvider(this)[AddAddressViewModel::class.java]
        binding.btnHouse.isActivated = true
        binding.btnWork.isActivated = false

        governmentsAdapter = GovernmentsAdapter(this)
        regionsAdapter = RegionsAdapter(this)
        viewModel.governmentsResponse.observe(this, Observer {

            when(val result=it){
                is ResultState.Error ->{

                }
                ResultState.Loading -> {

                }
                is ResultState.Success -> {
                    governmentsAdapter.setData(result.data.data)
                    binding.spGovernment.adapter = governmentsAdapter
                    binding.spGovernment.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                p0: AdapterView<*>?,
                                p1: View?,
                                p2: Int,
                                p3: Long
                            ) {
                                setRegionsAdapter(result.data.data?.get(p2)?.regions)
                            }

                            override fun onNothingSelected(p0: AdapterView<*>?) {}
                        }
                }
            }

        })
        binding.btnHouse.isActivated = true
        binding.btnWork.isActivated = false
        houseWorkSwitch()
        binding.ivImage.setImageResource(R.drawable.address_image)

        binding.ivImage.setOnClickListener {
            GetLocationActivity.startGetLocationActivity(this)
        }
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
        setData()
        binding.btnSave.setOnClickListener { updateAddress() }

        viewModel.update.observe(this, Observer {
            onBackPressed()
        })

    }
    private fun updateAddress() {
        val currentAddress = intent.getParcelableExtra<com.hakemy.marketsamer.ui.chooseAddresse.response.AddressItem>("AddressItem")
        lng=SharePreferenceManager.getlng().toString()
        lat=SharePreferenceManager.getlat().toString()
        with(binding) {
            when (type) {
                "house" -> {
                    currentAddress?.id?.let {
                        viewModel.update(
                            it.toString(),
                            lat,
                            lng,
                            type,
                            etName.fetchText(),
                            spGovernment.selectedItemId.toString(),
                            spArea.selectedItemId.toString(),
                            etDistrict.fetchText(),
                            etStreet.fetchText(),
                            etHouseBuildingNum.fetchText(),
                            etHouseFloor.fetchText(),
                            etFlatNum.fetchText(),
                            etWorkInstructions.fetchText(),
                            etPhone.fetchText()
                        )
                    }
                }
                else -> {
                    currentAddress?.id?.let {
                        viewModel.update(
                            it.toString(),
                            lat,
                            lng,
                            type,
                            etName.fetchText(),
                            spGovernment.selectedItemId.toString(),
                            spArea.selectedItemId.toString(),
                            etDistrict.fetchText(),
                            etStreet.fetchText(),
                            etWorkBuildingNum.fetchText(),
                            etWorkFloor.fetchText(),
                            etFlatNum.fetchText(),
                            etWorkInstructions.fetchText(),
                            etPhone.fetchText()
                        )
                    }
                }
            }
        }
    }

    private fun setData() {
        val currentAddress = intent.getParcelableExtra<com.hakemy.marketsamer.ui.chooseAddresse.response.AddressItem>("AddressItem")

        with(binding) {
            lat = currentAddress?.lat.toString()
            lng = currentAddress?.lng.toString()
            type = currentAddress?.type.toString()
            if (type == "house") {
                btnHouse.isActivated = true
                lnHouseFields.visibility = View.VISIBLE
                btnWork.isActivated = false
                lnWorkFields.visibility = View.GONE
            } else {
                btnWork.isActivated = true
                lnWorkFields.visibility = View.VISIBLE
                btnHouse.isActivated = false
                lnHouseFields.visibility = View.GONE
            }
            etName.setText(currentAddress?.address)
            etDistrict.setText(currentAddress?.pieceNumber)
            etStreet.setText(currentAddress?.streetNumber)
            etAlley.setText(currentAddress?.theRole)
            if (type == "house") {
                etHouseBuildingNum.setText(currentAddress?.houseNumber.toString())
                etHouseFloor.setText(currentAddress?.floorNumber.toString())
                etFlatNum.setText(currentAddress?.apartmentNumber.toString())
            } else {
                etWorkBuildingNum.setText(currentAddress?.houseNumber.toString())
                etWorkFloor.setText(currentAddress?.floorNumber.toString())
            }
            etWorkInstructions.setText(currentAddress?.information)
            etPhone.setText(currentAddress?.phone.toString())
        }
    }
    private fun setRegionsAdapter(regions: MutableList<RegionItem>?) {
        regionsAdapter.setData(regions)
        binding.spArea.adapter = regionsAdapter
        binding.spArea.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    p2: Int,
                    p3: Long
                ) {
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
    }
    private fun houseWorkSwitch() {
        with(binding) {
            btnHouse.setOnClickListener {
                if (!btnHouse.isActivated) {
                    btnHouse.isActivated = true
                    lnHouseFields.visibility = View.VISIBLE
                    btnWork.isActivated = false
                    lnWorkFields.visibility = View.GONE
                    type = "house"
                }
            }
            btnWork.setOnClickListener {
                if (!btnWork.isActivated) {
                    btnWork.isActivated = true
                    lnWorkFields.visibility = View.VISIBLE
                    btnHouse.isActivated = false
                    lnHouseFields.visibility = View.GONE
                    type = "company"
                }
            }
        }
    }


}

inline fun <reified T : Any> String.fromJson(): T {
    return Gson().fromJson(this, T::class.java)
}
