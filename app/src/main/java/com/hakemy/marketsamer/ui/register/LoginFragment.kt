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
import com.hakemy.marketsamer.databinding.FragmentLoginBinding
import com.hakemy.marketsamer.ui.main.MainActivity
import com.hakemy.marketsamer.ui.register.serviceModel.LoginRequest
import com.hakemy.marketsamer.ui.register.viewModel.RegisterViewModel
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.SharePreferenceManager

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateBinding() {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        binding.btnSignIn.setOnClickListener {

            viewModel.login(
                LoginRequest(
                    binding.etPhone.text.toString(),
                    binding.etPassword.text.toString()
                )
            )
        }

        viewModel.loginResults.observe(this, Observer {

            when (val result = it) {
                is ResultState.Error -> {
                  hideProgress()
                }
                ResultState.Loading -> {
                   showProgress()

                }
                is ResultState.Success -> {
hideProgress()
                    result.data.data?.user?.let {
                            it1 -> SharePreferenceManager.storeUserObject(it1)
                        SharePreferenceManager.storeToken(result.data.data.token)
                        SharePreferenceManager.storeIsVerified(true)

                        MainActivity.startMainActivity(requireActivity())
                        requireActivity().finish()
                    }


                }
            }

        })
        binding.tvForgetPassword.setOnClickListener {


            findNavController().navigate(R.id.resetPasswordFragment)
        }

        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.createNewAccount)

        }
    }

}