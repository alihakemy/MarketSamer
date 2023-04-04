package com.hakemy.marketsamer.ui.register.serviceModel.response.login


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class LoginResponse(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("message")
    val message: String, // Sorry This account is not activated
    @SerializedName("status")
    val status: Boolean // false
)