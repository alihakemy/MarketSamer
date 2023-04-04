package com.hakemy.marketsamer.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hakemy.marketsamer.BuildConfig
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.base.BaseFragment
import com.hakemy.marketsamer.databinding.FragmentVerificationCodeBinding
import com.hakemy.marketsamer.ui.main.MainActivity
import com.hakemy.marketsamer.ui.register.serviceModel.VerificationPhoneRequest
import com.hakemy.marketsamer.ui.register.viewModel.RegisterViewModel
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.SharePreferenceManager

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VerificationCode.newInstance] factory method to
 * create an instance of this fragment.
 */
class VerificationCode :
    BaseFragment<FragmentVerificationCodeBinding>(FragmentVerificationCodeBinding::inflate) {
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateBinding() {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]


        if (BuildConfig.DEBUG) {
            Toast.makeText(
                requireContext(),
                SharePreferenceManager.getVerificationCode(),
                Toast.LENGTH_LONG
            ).show()

        }

        binding.btnConfirm.setOnClickListener {


            when (val result = SharePreferenceManager.getUserObject()) {
                is ResultState.Error -> {

                }
                ResultState.Loading -> {

                }
                is ResultState.Success -> {
                    viewModel.verificationCode(
                        VerificationPhoneRequest(
                            phone =
                            result.data.phone.toString().replace("+965",""),
                            binding.otpCode.otp.toString()
                        )
                    )

                }
            }

        }

        viewModel.verificationCode.observe(this, Observer {

            when(val result=it){
                is ResultState.Error ->{
                    progressDialog.dismiss()
                }
                ResultState.Loading -> {
                    progressDialog.show()

                }
                is ResultState.Success -> {
                    progressDialog.dismiss()

                    result.data.data?.let {
                        progressDialog.dismiss()
                        SharePreferenceManager.storeIsVerified(true)
                        MainActivity.startMainActivity(requireActivity())
                        requireActivity().finish()
                    }


                }
            }

        })



    }

}