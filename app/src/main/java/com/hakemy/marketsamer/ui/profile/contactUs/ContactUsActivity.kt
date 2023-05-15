package com.hakemy.marketsamer.ui.profile.contactUs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.awesomedialog.AwesomeDialog
import com.example.awesomedialog.onPositive
import com.example.awesomedialog.title
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivityContactUsBinding
import com.hakemy.marketsamer.ui.addAddress.fetchText
import com.hakemy.marketsamer.ui.register.RegisterActivity
import com.hakemy.marketsamer.ui.search.SearchActivity
import com.hakemy.marketsamer.utils.SharePreferenceManager
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.launch

class ContactUsActivity : BaseActivity() {
    companion object{

        fun startContactUsActivity(context: Context){

            val intent= Intent(context, ContactUsActivity::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

        }

    }
    lateinit var binding: ActivityContactUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView6.setOnClickListener {
            onBackPressed()
        }
        binding.imageView4.setOnClickListener {
            SearchActivity.startSearchActivity(this)
        }

        binding.send.setOnClickListener {
            showProgress()
            lifecycleScope.launch {

                try {

                    val map=HashMap<String,String>()
                    map.put("reason",binding.etPhone.fetchText())
                    map.put("title_shipment",binding.details.fetchText())
                    map.put("msg",binding.details.fetchText())
                    RetrofitService.servicesApi().support(map)
                    AwesomeDialog.build(this@ContactUsActivity)
                        .title(getString(R.string.Done))
                        .onPositive(getString(R.string.thanks)) {
                            onBackPressed()
                        }

                } catch (e: java.lang.Exception) {

                }

                hideProgress()
            }


        }


    }
}