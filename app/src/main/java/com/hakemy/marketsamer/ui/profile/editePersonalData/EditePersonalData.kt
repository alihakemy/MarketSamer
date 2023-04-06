package com.hakemy.marketsamer.ui.profile.editePersonalData

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivityEditePersonalDataBinding
import com.hakemy.marketsamer.ui.addAddress.fetchText
import com.hakemy.marketsamer.ui.profile.favList.FavListActivity
import com.hakemy.marketsamer.ui.register.RegisterActivity
import com.hakemy.marketsamer.ui.register.serviceModel.response.User
import com.hakemy.marketsamer.utils.SharePreferenceManager
import com.hakemy.marketsamer.utils.services.RetrofitService
import com.squareup.moshi.Json
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.launch

class EditePersonalData : BaseActivity() {

    companion object{

        fun startEditePersonalData(context: Context){
            if(SharePreferenceManager.getIsVerified().not()){
                RegisterActivity.startRegisterActivity(context)

                return
            }
            val intent= Intent(context, EditePersonalData::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

        }

    }
    lateinit var binding: ActivityEditePersonalDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditePersonalDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnUpdate.setOnClickListener {
            if (binding.etName.fetchText().isNullOrEmpty() ||
                binding.etPhone.fetchText().isNullOrEmpty() ||
                binding.etPhone.fetchText().isNullOrEmpty()
            ) {

                Toasty.error(this@EditePersonalData, "تاكد من بياناتك").show()
                return@setOnClickListener
            }
            lifecycleScope.launch {

                try {

                    showProgress()


                  val result=  RetrofitService.servicesApi().updateProfile(
                        binding.etName.fetchText(),
                        binding.etPhone.fetchText(), binding.etPhone.fetchText()
                    )

                    result.data?.user?.let { it1 -> SharePreferenceManager.storeUserObject(it1) }
                    hideProgress()
                    onBackPressed()


                } catch (e: java.lang.Exception) {
                    hideProgress()
                    onBackPressed()

                }

            }

        }

    }
}

data class UserDataResponse(

    @Json(name = "activation_code") val activationCode: String? = null,

    @Json(name = "user") val user: User? = null,

    @Json(name = "token") val token: String?
)
