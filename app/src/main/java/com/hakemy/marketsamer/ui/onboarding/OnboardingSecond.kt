package com.hakemy.marketsamer.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.base.BaseFragment
import com.hakemy.marketsamer.databinding.FragmentOnboardingSecondBinding
import com.hakemy.marketsamer.ui.main.MainActivity
import com.hakemy.marketsamer.ui.onboarding.viewModel.OnboardingViewModel
import com.hakemy.marketsamer.ui.register.RegisterActivity
import com.hakemy.marketsamer.utils.ResultState

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OnboardingSecond.newInstance] factory method to
 * create an instance of this fragment.
 */
class OnboardingSecond : BaseFragment<FragmentOnboardingSecondBinding>(FragmentOnboardingSecondBinding::inflate) {
    lateinit var viewModel: OnboardingViewModel

    override fun onCreateBinding() {
        viewModel= ViewModelProvider(requireActivity())[OnboardingViewModel::class.java]


        viewModel.result.observe(this, Observer {

            when(val result= it){
                is ResultState.Error -> {

                }
                ResultState.Loading -> {

                }
                is ResultState.Success -> {
                    kotlin.runCatching {
                        Glide.with(this).load(result.data.data[1].image).into(binding.imageView)
                        binding.textView.text=result.data.data[1].title.toString()
                        binding.textView2.text=result.data.data[1].content.toString()
                    }

                }
            }

        })
        binding.textView5.setOnClickListener {
            findNavController().navigate(R.id.onboardingThird)
        }
        binding.textView3.setOnClickListener {
            MainActivity.startMainActivity(requireActivity())
        }


    }
}