package com.hakemy.marketsamer.ui.addAddress

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivityAddAddressBinding
import com.hakemy.marketsamer.databinding.ActivityChooseAddressBinding
import com.hakemy.marketsamer.ui.addAddress.adapter.GovernmentsAdapter
import com.hakemy.marketsamer.ui.addAddress.adapter.RegionsAdapter
import com.hakemy.marketsamer.ui.addAddress.getLocation.GetLocationActivity
import com.hakemy.marketsamer.ui.addAddress.models.RegionItem
import com.hakemy.marketsamer.ui.chooseAddresse.ChooseAddressActivity
import com.hakemy.marketsamer.ui.chooseAddresse.response.AddressItem
import com.hakemy.marketsamer.ui.register.RegisterActivity
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.SharePreferenceManager
import com.hakemy.marketsamer.utils.showToast

class AddAddress : BaseActivity() {

    lateinit var binding:ActivityAddAddressBinding
    lateinit var viewModel: AddAddressViewModel
    private lateinit var governmentsAdapter: GovernmentsAdapter
    private lateinit var regionsAdapter: RegionsAdapter
    private var type = "house"

    companion object{

        fun startAddAddress(context: Context){
            if(SharePreferenceManager.getIsVerified().not()){
                RegisterActivity.startRegisterActivity(context)

                return
            }
            val intent= Intent(context, AddAddress::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel=ViewModelProvider(this)[AddAddressViewModel::class.java]
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
        binding.btnSave.setOnClickListener { addAddress() }

        viewModel.addResponse.observe(this, Observer {

            when(val result= it){
                is ResultState.Error -> {
                    hideProgress()
                }
                ResultState.Loading -> {
                    showProgress()
                }
                is ResultState.Success -> {
                    hideProgress()
                    onBackPressed()
                }
            }

        })

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

    private fun addAddress() {
        with(binding) {
            when (type) {
                "house" -> {
                    if( SharePreferenceManager.getlat().toString().equals("")){
                        showToast(getString(R.string.select_location))
                        return
                    }
                    if(etDistrict.isValid().not()){
                       etDistrict.setError("")
                        return
                    }
                    if(etStreet.isValid().not()){
                        etStreet.setError("")
                        return
                    }
                    if(etHouseBuildingNum.isValid().not()){
                        etHouseBuildingNum.setError("")
                        return
                    }
                    if(etHouseFloor.isValid().not()){
                        etHouseFloor.setError("")
                        return
                    }
                    if(etFlatNum.isValid().not()){
                        etFlatNum.setError("")
                        return
                    }


                    viewModel.add(
                        SharePreferenceManager.getlat().toString(),
                        SharePreferenceManager.getlng().toString(),
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
                else -> {
                    if( SharePreferenceManager.getlat().toString().equals("")){
                        showToast(getString(R.string.select_location))
                        return
                    }
                    if(etDistrict.isValid().not()){
                        etDistrict.setError("")
                        return
                    }
                    if(etStreet.isValid().not()){
                        etStreet.setError("")
                        return
                    }

                    if(etFlatNum.isValid().not()){
                        etFlatNum.setError("")
                        return
                    }


                    viewModel.add(
                        SharePreferenceManager.getlat().toString(),
                        SharePreferenceManager.getlng().toString(),
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
fun EditText.fetchText(): String {
    return this.text.toString().trim()
}
fun EditText.isValid(): Boolean {
    return text.toString().isNullOrEmpty().not()
}
