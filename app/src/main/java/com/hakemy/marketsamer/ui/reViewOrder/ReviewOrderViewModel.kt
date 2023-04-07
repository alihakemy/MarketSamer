package com.hakemy.marketsamer.ui.reViewOrder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakemy.marketsamer.ui.reViewOrder.model.ReviewOrderResponse
import com.hakemy.marketsamer.ui.reViewOrder.submitOrderModel.SubmitOrderResponse
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.services.BaseResponse
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ReviewOrderViewModel :ViewModel() {


    private val _confirmResponse =
        MutableLiveData<ResultState<ReviewOrderResponse>>()
    val confirmResponse: MutableLiveData<ResultState<ReviewOrderResponse>>
        get() = _confirmResponse

    private val _couponResponse = MutableLiveData<ResultState<BaseResponse<Int>>>()
    val couponResponse: MutableLiveData<ResultState<BaseResponse<Int>>>
        get() = _couponResponse

    private val _submitOrderResponse =
        MutableLiveData<ResultState<SubmitOrderResponse>>()
    val submitOrderResponse: MutableLiveData<ResultState<SubmitOrderResponse>>
        get() = _submitOrderResponse

    fun confirmCart(orderId: String) {
        _confirmResponse.value =ResultState.Loading
        viewModelScope.launch {
            try {

                val result =RetrofitService.servicesApi().confirmCart(orderId)
                _confirmResponse.postValue(ResultState.Success(result))
            }catch (e:Exception){
                _confirmResponse.postValue(ResultState.Error(e.localizedMessage))

            }

        }
    }

    fun coupon(
        orderId: String,
        code: String
    ) {
        _couponResponse.value =ResultState.Loading
        viewModelScope.launch {
            try {

                val result =RetrofitService.servicesApi().coupon(orderId,code)
                _couponResponse.postValue(ResultState.Success(result))
            }catch (e:Exception){
                _couponResponse.postValue(ResultState.Error(e.localizedMessage))

            }

        }

    }

    fun submitOrder(
        orderId: String,
        paymentMethod: String,
        totalPrice: String,
        paymentStatus: String,
        totalShippingCost: String
    ) {
        _submitOrderResponse.value =ResultState.Loading
        viewModelScope.launch {
            try {

                val result =RetrofitService.servicesApi().submitOrder(orderId,paymentMethod,
                totalPrice,paymentStatus,totalShippingCost)
                _submitOrderResponse.postValue(ResultState.Success(result))
            }catch (e:Exception){
                _submitOrderResponse.postValue(ResultState.Error(e.localizedMessage))

            }

        }

    }
}