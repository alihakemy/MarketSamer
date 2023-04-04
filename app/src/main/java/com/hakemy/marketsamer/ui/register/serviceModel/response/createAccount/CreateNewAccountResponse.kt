package com.hakemy.marketsamer.ui.register.serviceModel.response.createAccount


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class CreateNewAccountResponse(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("status")
    val status: Boolean // true
)