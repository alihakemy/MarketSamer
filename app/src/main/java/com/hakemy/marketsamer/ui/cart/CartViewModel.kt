package com.hakemy.marketsamer.ui.cart

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakemy.marketsamer.ui.cart.models.CartResponse
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.getMacAddr
import com.hakemy.marketsamer.utils.services.BaseResponse
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

    private val _cartResponse =
        MutableLiveData<ResultState<CartResponse>>()
    val cartResponse: MutableLiveData<ResultState<CartResponse>>
        get() = _cartResponse


    fun getCart(macAddress: String) {
        _cartResponse.postValue(ResultState.Loading)
        viewModelScope.launch {
            try {
                val map=HashMap<String,String>()
                map.put("mac_address",macAddress)
                val result = RetrofitService.servicesApi().getCart(map)
                _cartResponse.postValue(ResultState.Success(result))

            } catch (e: java.lang.Exception) {
                _cartResponse.postValue(ResultState.Error(e.localizedMessage))

            }

        }
    }

    fun editCart(toString: String, s: String,context:Context,macAddress: String) {
        viewModelScope.launch {
            try {

                val result = RetrofitService.servicesApi().editCart(toString,s)
                val map=HashMap<String,String>()
                map.put("mac_address",macAddress)
                _cartResponse.postValue(ResultState.Success( RetrofitService.servicesApi().getCart(map)))

            } catch (e: java.lang.Exception) {
            }

        }

    }

    fun deleteCart(orderId: String, toString: String,context:Context) {
        viewModelScope.launch {
            try {

                val result = RetrofitService.servicesApi().deleteCart(orderId,toString)
                getCart(getMacAddr(context))

            } catch (e: java.lang.Exception) {
            }

        }
    }


}