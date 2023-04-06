package com.hakemy.marketsamer.ui.addAddress

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakemy.marketsamer.ui.addAddress.models.GovernmentItem
import com.hakemy.marketsamer.ui.chooseAddresse.response.AddressItem
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.services.BaseResponse
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.launch

class AddAddressViewModel :ViewModel() {

    private val _addResponse =
        MutableLiveData<ResultState<BaseResponse<AddressItem>>>()
    val addResponse: MutableLiveData<ResultState<BaseResponse<AddressItem>>>
        get() = _addResponse

    private val _governmentsResponse =
        MutableLiveData<ResultState<BaseResponse<MutableList<GovernmentItem>>>>()
    val governmentsResponse: MutableLiveData<ResultState<BaseResponse<MutableList<GovernmentItem>>>>
        get() = _governmentsResponse



    init {
        getGovernments()
    }

    private fun getGovernments() {

        viewModelScope.launch {

            try {
                _governmentsResponse.postValue(ResultState.Success(RetrofitService.servicesApi().governments()))
            }catch (e:java.lang.Exception){
                _governmentsResponse.postValue(ResultState.Error(e.localizedMessage))
            }
        }
    }

    fun add(
        lat: String,
        lng: String,
        type: String,
        address: String,
        governmentsId: String,
        regionId: String,
        pieceNumber: String,
        StreetNumber: String,
        houseNumber: String,
        floorNumber: String,
        ApartmentNumber: String? = null,
        information: String? = null,
        phone: String
    ) {
        viewModelScope.launch {
            _addResponse.postValue(ResultState.Loading)

            try {
                val result=   RetrofitService.servicesApi().addAddress(
                    lat,
                    lng,
                    type,
                    address,
                    governmentsId,
                    regionId,
                    pieceNumber,
                    StreetNumber,
                    houseNumber,
                    floorNumber,
                    ApartmentNumber,
                    information,
                    phone
                )
                _addResponse.postValue(ResultState.Success(result))

            }catch (e:java.lang.Exception){
                _addResponse.postValue(ResultState.Error(e.localizedMessage))
            }



        }
    }
    private val _update =
        MutableLiveData<ResultState<BaseResponse<AddressItem>>>()
    val update: MutableLiveData<ResultState<BaseResponse<AddressItem>>>
        get() = _update


    fun update(id:String,
        lat: String,
        lng: String,
        type: String,
        address: String,
        governmentsId: String,
        regionId: String,
        pieceNumber: String,
        StreetNumber: String,
        houseNumber: String,
        floorNumber: String,
        ApartmentNumber: String? = null,
        information: String? = null,
        phone: String
    ) {
        viewModelScope.launch {
            _addResponse.postValue(ResultState.Loading)

            try {
                val result=   RetrofitService.servicesApi().updateAddress(id,
                    lat.toString(),
                    lng,
                    type,
                    address,
                    governmentsId,
                    regionId,
                    pieceNumber,
                    StreetNumber,
                    houseNumber,
                    floorNumber,
                    ApartmentNumber,
                    information,
                    phone
                )
                _update.postValue(ResultState.Success(result))

            }catch (e:java.lang.Exception){
                _update.postValue(ResultState.Error(e.localizedMessage))
            }



        }
    }


}