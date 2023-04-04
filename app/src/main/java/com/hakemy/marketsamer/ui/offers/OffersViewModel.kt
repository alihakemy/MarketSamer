package com.hakemy.marketsamer.ui.offers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakemy.marketsamer.ui.offers.entities.response.OffersResponse
import com.hakemy.marketsamer.ui.register.serviceModel.CreateNewAccountRequest
import com.hakemy.marketsamer.ui.register.serviceModel.response.createAccount.CreateNewAccountResponse
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.launch

class OffersViewModel : ViewModel() {
    private val _offerResponse = MutableLiveData<ResultState<OffersResponse>>()

    val offerResponse: MutableLiveData<ResultState<OffersResponse>>
        get() = _offerResponse

    init {
        getOffers()
    }
    private fun getOffers() {
        _offerResponse.postValue(ResultState.Loading)
        viewModelScope.launch {

            try {

                val result = RetrofitService.servicesApi().getOffers()
                _offerResponse.postValue(ResultState.Success(result))
            } catch (e: java.lang.Exception) {
                _offerResponse.postValue(ResultState.Error(e.localizedMessage))

            }

        }
    }

     fun getOffersWithCategories(id:String) {
        _offerResponse.postValue(ResultState.Loading)
        viewModelScope.launch {

            try {

                val result = RetrofitService.servicesApi().productFromCategory(id)
                _offerResponse.postValue(ResultState.Success(result))
            } catch (e: java.lang.Exception) {
                _offerResponse.postValue(ResultState.Error(e.localizedMessage))

            }

        }
    }
}