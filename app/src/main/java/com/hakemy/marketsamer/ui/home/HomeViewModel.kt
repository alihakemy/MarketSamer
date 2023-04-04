package com.hakemy.marketsamer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakemy.marketsamer.ui.home.entities.response.MainScreenResponse
import com.hakemy.marketsamer.ui.offers.entities.response.OffersResponse
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _getMainScreen = MutableLiveData<ResultState<MainScreenResponse>>()

    val getMainScreen: MutableLiveData<ResultState<MainScreenResponse>>
        get() = _getMainScreen


    init {
        getMainScreen()
    }

    private fun getMainScreen() {

        _getMainScreen.postValue(ResultState.Loading)
        viewModelScope.launch {

            try {

                val result = RetrofitService.servicesApi().mainPage()
                _getMainScreen.postValue(ResultState.Success(result))
            } catch (e: java.lang.Exception) {
                _getMainScreen.postValue(ResultState.Error(e.localizedMessage))

            }

        }

    }

}