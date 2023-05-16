package com.hakemy.marketsamer.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.base.BaseFragment
import com.hakemy.marketsamer.databinding.FragmentCreateNewAccountBinding
import com.hakemy.marketsamer.databinding.FragmentLoginBinding
import com.hakemy.marketsamer.ui.register.serviceModel.CreateNewAccountRequest
import com.hakemy.marketsamer.ui.register.viewModel.RegisterViewModel
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.SharePreferenceManager


class CreateNewAccount :
    BaseFragment<FragmentCreateNewAccountBinding>(FragmentCreateNewAccountBinding::inflate) {

    private lateinit var viewModel: RegisterViewModel
    override fun onCreateBinding() {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        binding.tvSignIn.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.btnSignUp.setOnClickListener {
            viewModel.createNewAccount(
                CreateNewAccountRequest(
                    binding.etName.text.toString(),
                    binding.email.text.toString(),
                    binding.etPassword.text.toString(),
                    binding.etPhone.text.toString()
                )
            )

        }
        viewModel.createNewAccount.observe(this, Observer {

            when (val result = it) {
                is ResultState.Error -> {
               hideProgress()
                }
                ResultState.Loading -> {
                   showProgress()
                }
                is ResultState.Success -> {
                 hideProgress()
                    result.data.data?.user?.let { it1 -> SharePreferenceManager.storeUserObject(it1)

                        SharePreferenceManager.storeToken(result.data.data.token.toString())
                        SharePreferenceManager.storeVerificationCode(result.data.data.activationCode)
                       hideProgress()
                        findNavController().navigate(R.id.verificationCode)
                    }

                }
            }

        })
        binding.imageView22.setOnClickListener {
            activity?.onBackPressed()
        }

    }

}