package com.hakemy.marketsamer.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakemy.marketsamer.ui.offers.entities.response.Products
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.services.BaseResponse
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SearchViewModel:ViewModel() {

    private val _searchResponse =
        MutableLiveData<ResultState<BaseResponse<Products>>>()
    val searchResponse: MutableLiveData<ResultState<BaseResponse<Products>>>
        get() = _searchResponse


    fun search(search: String) {
        _searchResponse.value=ResultState.Loading
        viewModelScope.launch {
            try {
                _searchResponse.postValue(ResultState.Success(RetrofitService.servicesApi().search(search)))
            }catch (e:java.lang.Exception){
                _searchResponse.postValue(ResultState.Error(e.localizedMessage))

            }
        }

    }

    fun isFavourite(id: String) {

    }

}