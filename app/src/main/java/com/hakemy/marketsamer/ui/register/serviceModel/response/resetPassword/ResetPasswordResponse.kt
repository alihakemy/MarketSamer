package com.hakemy.marketsamer.ui.register.serviceModel.response.resetPassword


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ResetPasswordResponse(
    @SerializedName("data")
    val `data`: String, // 6727
    @SerializedName("message")
    val message: String, // success
    @SerializedName("status")
    val status: Boolean // true
)