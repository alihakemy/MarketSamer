package com.hakemy.marketsamer.ui.myOrders

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakemy.marketsamer.ui.myOrders.models.MyOrderResponse
import com.hakemy.marketsamer.ui.offers.entities.response.OffersResponse
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.launch

class MyOrderViewModel :ViewModel() {

    private val _myOrderViewModel= MutableLiveData<ResultState<MyOrderResponse>>()

    val myOrderViewModel: MutableLiveData<ResultState<MyOrderResponse>>
        get() = _myOrderViewModel


    fun getMyOrder(){
        _myOrderViewModel.postValue(ResultState.Loading)

        viewModelScope.launch {

            try {
                val result=RetrofitService.servicesApi().userOrder()
                _myOrderViewModel.postValue(ResultState.Success(result))
            }catch (e:Exception){
                _myOrderViewModel.postValue(ResultState.Error(e.localizedMessage))

            }

        }
    }

}