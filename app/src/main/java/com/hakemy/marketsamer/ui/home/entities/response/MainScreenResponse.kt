package com.hakemy.marketsamer.ui.home.entities.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MainScreenResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: Boolean // true
)