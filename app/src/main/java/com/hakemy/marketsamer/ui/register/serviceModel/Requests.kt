package com.hakemy.marketsamer.ui.register.serviceModel

data class CreateNewAccountRequest(
    val name: String,
    val email: String, val password: String, val phone: String,
    val role: String = "clients",
)

data class VerificationPhoneRequest(val phone:String,val code:String)
data class ResetPasswordRequest(val phone:String,val password:String)

data class LoginRequest(val email: String, val password:String)