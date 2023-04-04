package com.hakemy.marketsamer.ui.offers.entities.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class OffersResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: Boolean // true
)