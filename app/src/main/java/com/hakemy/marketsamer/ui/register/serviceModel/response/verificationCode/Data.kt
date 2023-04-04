package com.hakemy.marketsamer.ui.register.serviceModel.response.verificationCode


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.hakemy.marketsamer.ui.register.serviceModel.response.User

@Keep
data class Data(
    @SerializedName("user")
    var user: User?=null
)