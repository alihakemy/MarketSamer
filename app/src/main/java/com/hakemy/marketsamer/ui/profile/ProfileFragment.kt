package com.hakemy.marketsamer.ui.profile

import android.content.Context
import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.base.BaseFragment
import com.hakemy.marketsamer.databinding.FragmentNotificationsBinding
import com.hakemy.marketsamer.ui.main.MainActivity
import com.hakemy.marketsamer.ui.profile.contactUs.ContactUsActivity
import com.hakemy.marketsamer.ui.profile.editePersonalData.EditePersonalData
import com.hakemy.marketsamer.ui.profile.favList.FavListActivity
import com.hakemy.marketsamer.ui.profile.mySavedAddress.SaveAddressActivitySetting
import com.hakemy.marketsamer.ui.profile.notification.NotificationActivity
import com.hakemy.marketsamer.ui.profile.otherPages.OtherPages
import com.hakemy.marketsamer.ui.register.RegisterActivity
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.SharePreferenceManager
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.launch


class ProfileFragment :
    BaseFragment<FragmentNotificationsBinding>(FragmentNotificationsBinding::inflate) {
    override fun onCreateBinding() {

        val profileViewModel =
            ViewModelProvider(requireActivity())[ProfileViewModel::class.java]

        binding.notification.tvTitle.text = getString(R.string.notification)
        binding.notification.ivIcon.setImageResource(R.drawable.notification)
        binding.notification.tvTitle.setOnClickListener {
            NotificationActivity.startNotificationActivity(requireContext())
        }
        if (SharePreferenceManager.getLang().equals("ar")) {
            binding.button.text = "en"
        } else {
            binding.button.text = "ar"
        }
        binding.button.setOnClickListener {
            if (SharePreferenceManager.getLang().equals("ar")) {
                binding.button.text = "en"
                SharePreferenceManager.storeLang("en")
            } else {
                binding.button.text = "ar"
                SharePreferenceManager.storeLang("ar")

            }
            MainActivity.startMainActivity(requireActivity())
        }


        binding.fav.tvTitle.text = getString(R.string.fav)
        binding.fav.ivIcon.setImageResource(R.drawable.fav)
        binding.fav.tvTitle.setOnClickListener {
            FavListActivity.startFavListActivity(requireContext())
        }

        binding.addresses.tvTitle.text = getString(R.string.address)
        binding.addresses.ivIcon.setImageResource(R.drawable.address)
        binding.addresses.tvTitle.setOnClickListener {
            SaveAddressActivitySetting.startSaveAddressActivitySetting(requireContext())
        }

        binding.editPersonal.tvTitle.text = getString(R.string.personaldata)
        binding.editPersonal.ivIcon.setImageResource(R.drawable.edite)
        binding.editPersonal.tvTitle.setOnClickListener {

            EditePersonalData.startEditePersonalData(requireContext())
        }

        binding.policy.tvTitle.text = getString(R.string.policy)
        binding.policy.ivIcon.setImageResource(R.drawable.policy)

        binding.shareApp.tvTitle.text = getString(R.string.shareApp)
        binding.shareApp.ivIcon.setImageResource(R.drawable.share)

        binding.shareApp.tvTitle.setOnClickListener {

            shareApp(requireContext())
        }

        binding.terms.tvTitle.text = getString(R.string.terms)
        binding.terms.ivIcon.setImageResource(R.drawable.documents)

        binding.callUs.tvTitle.text = getString(R.string.callus)
        binding.callUs.ivIcon.setImageResource(R.drawable.callus)
        binding.callUs.tvTitle.setOnClickListener {
            ContactUsActivity.startContactUsActivity(requireContext())
        }


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                if (SharePreferenceManager.getIsVerified()) {
                    binding.appCompatTextView2.text = getString(R.string.logout)
                    when (val results = SharePreferenceManager.getUserObject()) {
                        is ResultState.Error -> {

                        }
                        ResultState.Loading -> {

                        }
                        is ResultState.Success -> {
                            kotlin.runCatching {
                                if(results.data.name.isNullOrEmpty().not()){
                                    binding.appCompatTextView.text = results.data.name.toString()

                                }

                            }

                        }
                    }
                    binding.appCompatTextView2.setOnClickListener {
                        SharePreferenceManager.storeIsVerified(false)
                        MainActivity.startMainActivity(requireActivity())
                    }

                } else {
                    binding.appCompatTextView.text = ""

                    binding.appCompatTextView2.text = getString(R.string.login)
                    binding.appCompatTextView2.setOnClickListener {
                        RegisterActivity.startRegisterActivity(requireContext())
                    }

                }
            }
        }

        lifecycleScope.launch {

            kotlin.runCatching {

                val result = RetrofitService.servicesApi().getpage()

                binding.terms.tvTitle.setOnClickListener {

                    result.data.forEach {
                        if (it.id == 1) {
                            OtherPages.startOtherPages(requireContext(), it.name, it.content)
                        }
                    }

                }

                binding.policy.tvTitle.setOnClickListener {

                    result.data.forEach {
                        if (it.id == 2) {
                            OtherPages.startOtherPages(requireContext(), it.name, it.content)
                        }
                    }

                }


            }

        }


    }

    fun shareApp(context: Context) {
        val appPackageName: String = context.getPackageName()
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Check out the App at: https://play.google.com/store/apps/details?id=$appPackageName"
        )
        sendIntent.type = "text/plain"
        context.startActivity(sendIntent)
    }

}