package com.hakemy.marketsamer.ui.onboarding

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

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

        viewModel.result.observe(this, Observer {

            when(val result= it){
                is ResultState.Error -> {
                    MainActivity.startMainActivity(this)

                }
                ResultState.Loading -> {

                    binding.root.visibility=View.GONE
                }
                is ResultState.Success -> {

                    if(result.data.data.isNullOrEmpty()){
                        MainActivity.startMainActivity(this)

                    }

                }
            }

        })


    }
}