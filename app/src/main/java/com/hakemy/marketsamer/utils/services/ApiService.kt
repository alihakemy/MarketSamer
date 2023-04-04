package com.hakemy.marketsamer.utils.services

import com.hakemy.marketsamer.ui.home.entities.response.MainScreenResponse
import com.hakemy.marketsamer.ui.offers.entities.response.OffersResponse
import com.hakemy.marketsamer.ui.onboarding.servicesModels.OnboardingModel
import com.hakemy.marketsamer.ui.register.VerificationCode
import com.hakemy.marketsamer.ui.register.serviceModel.CreateNewAccountRequest
import com.hakemy.marketsamer.ui.register.serviceModel.LoginRequest
import com.hakemy.marketsamer.ui.register.serviceModel.ResetPasswordRequest
import com.hakemy.marketsamer.ui.register.serviceModel.VerificationPhoneRequest
import com.hakemy.marketsamer.ui.register.serviceModel.response.createAccount.CreateNewAccountResponse
import com.hakemy.marketsamer.ui.register.serviceModel.response.login.LoginResponse
import com.hakemy.marketsamer.ui.register.serviceModel.response.resetPassword.ResetPasswordResponse
import com.hakemy.marketsamer.ui.showProduct.entities.ProductDetailsResponse
import retrofit2.http.*

interface ApiService {


    @GET("landing_board")
    suspend fun onBoarding():OnboardingModel

    @POST("register")
    suspend fun register(@Body createNewAccountRequest: CreateNewAccountRequest): CreateNewAccountResponse

    @POST("verificationPhone")
    suspend fun verificationPhone(@Body verificationCode: VerificationPhoneRequest):com.hakemy.marketsamer.ui.register.serviceModel.response.verificationCode.VerificationCode

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("forgetPassword")
    suspend fun forgetPassword(@Body resetPasswordRequest: ResetPasswordRequest): ResetPasswordResponse

    @GET("offers")
    suspend fun getOffers(): OffersResponse

    @GET("productFromCategory/{id}")
    suspend fun productFromCategory(@Path("id") id: String): OffersResponse



    @GET("mainPage")
    suspend fun mainPage(): MainScreenResponse



    @GET("product/{productId}")
    suspend fun productDetails(@Path("productId") id: String): ProductDetailsResponse
}