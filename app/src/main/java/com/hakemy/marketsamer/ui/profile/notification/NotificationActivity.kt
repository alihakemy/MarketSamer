package com.hakemy.marketsamer.ui.profile.notification

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivityNotificationBinding
import com.hakemy.marketsamer.ui.addAddress.AddAddress
import com.hakemy.marketsamer.ui.profile.notification.model.NotificationItem
import com.hakemy.marketsamer.ui.register.RegisterActivity
import com.hakemy.marketsamer.utils.SharePreferenceManager
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.launch

class NotificationActivity : BaseActivity() {
    companion object{

        fun startNotificationActivity(context: Context){

            val intent= Intent(context, NotificationActivity::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

        }

    }
    lateinit var binding:ActivityNotificationBinding
    private lateinit var notificationsAdapter: NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notificationsAdapter = NotificationAdapter(this)

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
        lifecycleScope.launch {

            try {

                showProgress()

                val result =RetrofitService.servicesApi().notifications()


                result.data?.data?.let { checkEmpty(it) }
                result.data?.data?.let { notificationsAdapter.insertItem(it) }
                binding.rvNotifications.adapter = notificationsAdapter
                hideProgress()
            }catch (e:java.lang.Exception){
                hideProgress()

            }

        }

    }
    private fun checkEmpty(data: MutableList<NotificationItem>) {
        if (data.isEmpty())
            binding.tvEmpty.visibility = View.VISIBLE
        else
            binding.tvEmpty.visibility = View.GONE
    }
}