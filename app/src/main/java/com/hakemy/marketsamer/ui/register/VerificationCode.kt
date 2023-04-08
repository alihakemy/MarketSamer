package com.hakemy.marketsamer.ui.register

import android.os.CountDownTimer
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.hakemy.marketsamer.BuildConfig
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.base.BaseFragment
import com.hakemy.marketsamer.databinding.FragmentVerificationCodeBinding
import com.hakemy.marketsamer.ui.main.MainActivity
import com.hakemy.marketsamer.ui.register.serviceModel.VerificationPhoneRequest
import com.hakemy.marketsamer.ui.register.viewModel.RegisterViewModel
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.SharePreferenceManager
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.launch


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
                            result.data.phone.toString().replace("+965", ""),
                            binding.otpCode.otp.toString()
                        )
                    )

                }
            }

        }

        viewModel.verificationCode.observe(this, Observer {

            when (val result = it) {
                is ResultState.Error -> {
                    hideProgress()
                }
                ResultState.Loading -> {
                    showProgress()

                }
                is ResultState.Success -> {
                    hideProgress()
                    result.data.data?.let {
                        hideProgress()
                        SharePreferenceManager.storeIsVerified(true)
                        MainActivity.startMainActivity(requireActivity())
                        requireActivity().finish()
                    }


                }
            }

        })

        timer()
        binding.resend.setOnClickListener {

            lifecycleScope.launch {

                val map=HashMap<String,String>()
                map.put("phone",SharePreferenceManager.getUser().phone.toString().replace("+965", ""))
                map.put("type","phone")
                kotlin.runCatching {
                    RetrofitService.servicesApi().resend(map)
                }

            }
            timer()

        }

    }

    fun timer() {
        binding.resend.text = getString(R.string.second)

        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                kotlin.runCatching {
                    binding.tvCountDown.text = (millisUntilFinished / 1000).toString()
                }

            }

            override fun onFinish() {
                kotlin.runCatching {
                    binding.resend.text = getString(R.string.send)

                }
            }
        }.start()

    }

}