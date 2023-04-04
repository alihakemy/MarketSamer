package com.hakemy.marketsamer.ui.showProduct

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakemy.marketsamer.ui.home.entities.response.MainScreenResponse
import com.hakemy.marketsamer.ui.showProduct.entities.ProductDetailsResponse
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.launch

class ShowProductViewModel :ViewModel() {

    private val _productDetails = MutableLiveData<ResultState<ProductDetailsResponse>>()

    val productDetails: MutableLiveData<ResultState<ProductDetailsResponse>>
        get() = _productDetails




     fun getProductDetails(productId:String) {

        _productDetails.postValue(ResultState.Loading)
        viewModelScope.launch {

            try {

                val result = RetrofitService.servicesApi().productDetails(productId)
                _productDetails.postValue(ResultState.Success(result))
            } catch (e: java.lang.Exception) {
                _productDetails.postValue(ResultState.Error(e.localizedMessage))

            }

        }

    }
}