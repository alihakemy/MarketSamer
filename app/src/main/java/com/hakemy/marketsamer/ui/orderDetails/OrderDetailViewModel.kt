package com.hakemy.marketsamer.ui.orderDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakemy.marketsamer.ui.orderDetails.Model.OrderDetailsResponse
import com.hakemy.marketsamer.ui.reViewOrder.model.ReviewOrderResponse
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.launch

class OrderDetailViewModel : ViewModel() {

    private val _orderDetailsResponse =
        MutableLiveData<ResultState<OrderDetailsResponse>>()
    val orderDetailsResponse: MutableLiveData<ResultState<OrderDetailsResponse>>
        get() = _orderDetailsResponse


    fun getOrderDetails(orderId: String) {
        _orderDetailsResponse.postValue(ResultState.Loading)
        viewModelScope.launch {
            try {
                val result = RetrofitService.servicesApi().orderDetails(orderId)
                _orderDetailsResponse.postValue(ResultState.Success(result))
            } catch (e: Exception) {
                _orderDetailsResponse.postValue(ResultState.Error(e.localizedMessage))
            }
        }
    }


}