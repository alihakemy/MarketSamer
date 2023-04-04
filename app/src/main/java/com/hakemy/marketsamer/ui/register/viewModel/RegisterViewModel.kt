package com.hakemy.marketsamer.ui.register.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakemy.marketsamer.ui.register.serviceModel.CreateNewAccountRequest
import com.hakemy.marketsamer.ui.register.serviceModel.LoginRequest
import com.hakemy.marketsamer.ui.register.serviceModel.ResetPasswordRequest
import com.hakemy.marketsamer.ui.register.serviceModel.VerificationPhoneRequest
import com.hakemy.marketsamer.ui.register.serviceModel.response.createAccount.CreateNewAccountResponse
import com.hakemy.marketsamer.ui.register.serviceModel.response.login.LoginResponse
import com.hakemy.marketsamer.ui.register.serviceModel.response.resetPassword.ResetPasswordResponse
import com.hakemy.marketsamer.ui.register.serviceModel.response.verificationCode.VerificationCode
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {


    private val _createNewAccount = MutableLiveData<ResultState<CreateNewAccountResponse>>()

    val createNewAccount: MutableLiveData<ResultState<CreateNewAccountResponse>>
        get() = _createNewAccount


    private val _loginResults = MutableLiveData<ResultState<LoginResponse>>()
    val loginResults: MutableLiveData<ResultState<LoginResponse>>
        get() = _loginResults



    private val _verificationCode = MutableLiveData<ResultState<com.hakemy.marketsamer.ui.register.serviceModel.response.verificationCode.VerificationCode>>()
    val verificationCode: MutableLiveData<ResultState<com.hakemy.marketsamer.ui.register.serviceModel.response.verificationCode.VerificationCode>>
        get() = _verificationCode

    private val _resetPassword = MutableLiveData<ResultState<ResetPasswordResponse>>()
    val resetPassword: MutableLiveData<ResultState<ResetPasswordResponse>>
        get() = _resetPassword
    fun createNewAccount(createNewAccountRequest: CreateNewAccountRequest) {
        _createNewAccount.postValue(ResultState.Loading)
        viewModelScope.launch {

            try {

                val result = RetrofitService.servicesApi().register(createNewAccountRequest)
                _createNewAccount.postValue(ResultState.Success(result))
            } catch (e: java.lang.Exception) {
                _createNewAccount.postValue(ResultState.Error(e.localizedMessage))

            }

        }
    }



    fun login(loginRequest: LoginRequest){

        viewModelScope.launch {
            _loginResults.postValue(ResultState.Loading)
            try {

                val result = RetrofitService.servicesApi().login(loginRequest)

                _loginResults.postValue(ResultState.Success(result))
            } catch (e: java.lang.Exception) {
                _loginResults.postValue(ResultState.Error(e.localizedMessage))

            }
        }
    }

    fun verificationCode(verificationPhoneRequest: VerificationPhoneRequest){
        viewModelScope.launch {
            _verificationCode.postValue(ResultState.Loading)
            viewModelScope.launch {

                try {

                    val result = RetrofitService.servicesApi().verificationPhone(verificationPhoneRequest)
                    _verificationCode.postValue(ResultState.Success(result))
                } catch (e: java.lang.Exception) {
                    _verificationCode.postValue(ResultState.Error(e.localizedMessage))

                }

            }
        }
    }


    fun resetPassword(resetPasswordRequest: ResetPasswordRequest){
        viewModelScope.launch {
            _resetPassword.postValue(ResultState.Loading)
            viewModelScope.launch {

                try {

                    val result = RetrofitService.servicesApi().forgetPassword(resetPasswordRequest)
                    _resetPassword.postValue(ResultState.Success(result))
                } catch (e: java.lang.Exception) {
                    _resetPassword.postValue(ResultState.Error(e.localizedMessage))

                }

            }
        }
    }

}