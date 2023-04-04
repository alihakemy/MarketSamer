package com.hakemy.marketsamer.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.base.BaseFragment
import com.hakemy.marketsamer.databinding.FragmentDashboardBinding
import com.hakemy.marketsamer.databinding.FragmentNotificationsBinding
import com.hakemy.marketsamer.ui.main.MainActivity
import com.hakemy.marketsamer.ui.register.RegisterActivity
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.SharePreferenceManager

class ProfileFragment :
    BaseFragment<FragmentNotificationsBinding>(FragmentNotificationsBinding::inflate) {
    override fun onCreateBinding() {

        val profileViewModel =
            ViewModelProvider(requireActivity())[ProfileViewModel::class.java]

        binding.notification.tvTitle.text = getString(R.string.notification)
        binding.notification.ivIcon.setImageResource(R.drawable.notification)

        binding.fav.tvTitle.text = getString(R.string.fav)
        binding.fav.ivIcon.setImageResource(R.drawable.fav)

        binding.addresses.tvTitle.text = getString(R.string.address)
        binding.addresses.ivIcon.setImageResource(R.drawable.address)


        binding.editPersonal.tvTitle.text = getString(R.string.personaldata)
        binding.editPersonal.ivIcon.setImageResource(R.drawable.edite)

        binding.policy.tvTitle.text = getString(R.string.policy)
        binding.policy.ivIcon.setImageResource(R.drawable.policy)

        binding.shareApp.tvTitle.text = getString(R.string.shareApp)
        binding.shareApp.ivIcon.setImageResource(R.drawable.share)

        binding.terms.tvTitle.text = getString(R.string.terms)
        binding.terms.ivIcon.setImageResource(R.drawable.documents)

        binding.callUs.tvTitle.text = getString(R.string.callus)
        binding.callUs.ivIcon.setImageResource(R.drawable.callus)


        if (SharePreferenceManager.getIsVerified()) {
            binding.appCompatTextView2.text = getString(R.string.logout)
            when(val results= SharePreferenceManager.getUserObject()){
                is ResultState.Error -> {

                }
                ResultState.Loading -> {

                }
                is ResultState.Success -> {
                    binding.appCompatTextView.text=results.data.name.toString()

                }
            }
            binding.appCompatTextView2.setOnClickListener {
                SharePreferenceManager.storeIsVerified(false)
                MainActivity.startMainActivity(requireActivity())
            }

        } else {
            binding.appCompatTextView.text=""

            binding.appCompatTextView2.text = getString(R.string.login)
            binding.appCompatTextView2.setOnClickListener {
                RegisterActivity.startRegisterActivity(requireContext())
            }

        }


    }


}