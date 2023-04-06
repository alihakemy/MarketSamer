package com.hakemy.marketsamer.ui.profile.mySavedAddress

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.awesomedialog.AwesomeDialog
import com.example.awesomedialog.onPositive
import com.example.awesomedialog.title
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivityChooseAddressBinding
import com.hakemy.marketsamer.databinding.ActivitySaveAddressSettingBinding
import com.hakemy.marketsamer.ui.addAddress.AddAddress
import com.hakemy.marketsamer.ui.chooseAddresse.ChooseAddressActivity
import com.hakemy.marketsamer.ui.chooseAddresse.ChooseAddressViewModel
import com.hakemy.marketsamer.ui.chooseAddresse.SavedAddressesAdapter
import com.hakemy.marketsamer.ui.chooseAddresse.response.AddressItem
import com.hakemy.marketsamer.ui.editeAddresse.EditeAddresseActivity
import com.hakemy.marketsamer.ui.register.RegisterActivity
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.SharePreferenceManager
import kotlinx.coroutines.launch

class SaveAddressActivitySetting : BaseActivity() , SavedAddressesAdapter.RecycleListener{
    lateinit var viewModel: ChooseAddressViewModel
    private lateinit var savedAddressesAdapter: SavedAddressesAdapter

    companion object {

        fun startSaveAddressActivitySetting(context: Context) {
            if (SharePreferenceManager.getIsVerified().not()) {
                RegisterActivity.startRegisterActivity(context)

                return
            }
            val intent = Intent(context, SaveAddressActivitySetting::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

        }

    }
    lateinit var binding:ActivitySaveAddressSettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySaveAddressSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        savedAddressesAdapter = SavedAddressesAdapter(this, this)

        viewModel = ViewModelProvider(this)[ChooseAddressViewModel::class.java]
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.addresses()
            }

        }

        viewModel.addressesResponse.observe(this, Observer {

            when (val result = it) {
                is ResultState.Error -> {
                    hideProgress()
                }
                ResultState.Loading -> {
                    showProgress()
                }
                is ResultState.Success -> {
                    hideProgress()
                    runCatching {

                        with(binding) {
                            result.data.data?.let { it1 -> savedAddressesAdapter.insertItem(it1) }
                            rvAddresses.adapter = savedAddressesAdapter
                        }

                    }


                }
            }

        })

        binding.tvAddAddress.setOnClickListener {
            AddAddress.startAddAddress(this)
        }
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }




    }

    override fun onAddressClicked(position: Int, address: AddressItem) {
        AwesomeDialog.build(this)
            .title(getString(R.string.Congratulations))
            .onPositive(getString(R.string.next)) {
                viewModel.choose(address.id.toString())

                onBackPressed()
            }



    }

    override fun onDeleteClicked(position: Int, address: AddressItem) {
        address.id?.let { viewModel.delete(it) }
    }

    override fun onEditClicked(position: Int, address: AddressItem) {

        EditeAddresseActivity.startEditeAddresseActivity(this,address)
    }
}