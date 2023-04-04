package com.hakemy.marketsamer.ui.onboarding.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.hakemy.marketsamer.ui.onboarding.servicesModels.OnboardingModel
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.launch

class OnboardingViewModel : ViewModel() {


    private val _results = MutableLiveData<ResultState<OnboardingModel>>()

    val result: MutableLiveData<ResultState<OnboardingModel>>
        get() = _results

    init {
        getOnboarding()
    }

    private fun getOnboarding() {

        viewModelScope.launch {
            try {
                val response = RetrofitService.servicesApi().onBoarding()
                _results.postValue(ResultState.Success(response))
            } catch (e: java.lang.Exception) {
                _results.postValue(e?.localizedMessage?.let { ResultState.Error(it) })
                Log.e("Resulkts", e?.localizedMessage.toString())
            }

        }


    }


}