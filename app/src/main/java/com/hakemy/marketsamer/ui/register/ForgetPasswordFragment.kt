package com.hakemy.marketsamer.ui.register

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hakemy.marketsamer.base.BaseFragment
import com.hakemy.marketsamer.databinding.FragmentResetPasswordBinding
import com.hakemy.marketsamer.ui.register.serviceModel.ResetPasswordRequest
import com.hakemy.marketsamer.ui.register.viewModel.RegisterViewModel
import com.hakemy.marketsamer.utils.ResultState


class ForgetPasswordFragment : BaseFragment<FragmentResetPasswordBinding>(
    FragmentResetPasswordBinding::inflate
) {
    private lateinit var viewModel: RegisterViewModel
    override fun onCreateBinding() {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        binding.btnChange.setOnClickListener {
            if (binding.etPassword.text.toString()
                    .equals(binding.etConfirmPassword.text.toString())
            ) {
                viewModel.resetPassword(
                    ResetPasswordRequest(
                        phone = binding.phone.text.toString(),
                        password = binding.etPassword.text.toString()
                    )
                )
            }else{
                Toast.makeText(
                    requireContext(),
                    "checkPassword",
                    Toast.LENGTH_LONG
                ).show()
            }


        }
        viewModel.resetPassword.observe(this, Observer {

            when (val result = it) {
                is ResultState.Error -> {
                    progressDialog.dismiss()
                }
                ResultState.Loading -> {
                    progressDialog.show()

                }
                is ResultState.Success -> {
                    progressDialog.dismiss()

                    result.data.data?.let {
                        progressDialog.dismiss()
                        requireActivity().onBackPressed()

                    }


                }
            }

        })
    }


}