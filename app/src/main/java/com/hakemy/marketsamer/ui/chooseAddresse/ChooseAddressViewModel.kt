package com.hakemy.marketsamer.ui.chooseAddresse

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakemy.marketsamer.ui.chooseAddresse.response.AddressItem
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.services.BaseResponse
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ChooseAddressViewModel :ViewModel() {
    private val _addressesResponse =
        MutableLiveData<ResultState<BaseResponse<MutableList<AddressItem>>>>()
    val addressesResponse: MutableLiveData<ResultState<BaseResponse<MutableList<AddressItem>>>>
        get() = _addressesResponse

    fun addresses() {
        _addressesResponse.value = ResultState.Loading
        viewModelScope.launch {
            try {
                _addressesResponse.postValue( ResultState.Success(RetrofitService.servicesApi().savedAddresses()))
            }catch (e:java.lang.Exception){
                _addressesResponse.postValue(   ResultState.Error(e.localizedMessage))
            }

        }
    }

    fun delete(it: Int) {
        viewModelScope.launch {

            kotlin.runCatching {
                RetrofitService.servicesApi().deleteAddress(it)
                addresses()
            }
        }

    }

    fun choose(toString: String) {
        viewModelScope.launch {

            kotlin.runCatching {
                RetrofitService.servicesApi().chooseAddress(toString)
                addresses()
            }
        }

    }


}