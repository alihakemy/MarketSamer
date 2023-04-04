package com.hakemy.marketsamer.ui.onboarding

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider

import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivityOnboardingBinding
import com.hakemy.marketsamer.ui.main.MainActivity
import com.hakemy.marketsamer.ui.onboarding.viewModel.OnboardingViewModel
import com.hakemy.marketsamer.utils.LocaleHelper
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.SharePreferenceManager


class OnboardingActivity : BaseActivity() {

    lateinit var binding: ActivityOnboardingBinding
    lateinit var viewModel:OnboardingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel= ViewModelProvider(this)[OnboardingViewModel::class.java]
        if(SharePreferenceManager.getIsShowOnboarding()){
            MainActivity.startMainActivity(this)
            finish()
        }
        SharePreferenceManager.storeIsShowOnboarding(true)



    }
}