package com.hakemy.marketsamer.ui.chooseAddresse

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.*
import com.example.awesomedialog.AwesomeDialog
import com.example.awesomedialog.onPositive
import com.example.awesomedialog.title
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivityChooseAddressBinding
import com.hakemy.marketsamer.ui.addAddress.AddAddress
import com.hakemy.marketsamer.ui.chooseAddresse.response.AddressItem
import com.hakemy.marketsamer.ui.editeAddresse.EditeAddresseActivity
import com.hakemy.marketsamer.ui.register.RegisterActivity
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.SharePreferenceManager
import kotlinx.coroutines.launch

class ChooseAddressActivity : BaseActivity(), SavedAddressesAdapter.RecycleListener {
    lateinit var binding: ActivityChooseAddressBinding
    lateinit var viewModel: ChooseAddressViewModel
    private lateinit var savedAddressesAdapter: SavedAddressesAdapter

    companion object {

        fun startChooseAddressActivity(context: Context,totolPrice:String) {
            if (SharePreferenceManager.getIsVerified().not()) {
                RegisterActivity.startRegisterActivity(context)

                return
            }
            val intent = Intent(context, ChooseAddressActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("totolPrice",totolPrice)
            context.startActivity(intent)

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseAddressBinding.inflate(layoutInflater)
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
        binding.tvTotal.text=intent.getStringExtra("totolPrice")

        binding.tvAddAddress.setOnClickListener {
            AddAddress.startAddAddress(this)
        }
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }



    }

    override fun onAddressClicked(position: Int, address: AddressItem) {
        viewModel.choose(address.id.toString())
        AwesomeDialog.build(this)
            .title(getString(R.string.Congratulations))
            .onPositive(getString(R.string.next)) {

            }


    }

    override fun onDeleteClicked(position: Int, address: AddressItem) {
        address.id?.let { viewModel.delete(it) }
    }

    override fun onEditClicked(position: Int, address: AddressItem) {

        EditeAddresseActivity.startEditeAddresseActivity(this,address)
    }
}