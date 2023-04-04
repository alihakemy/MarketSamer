package com.hakemy.marketsamer.ui.showProduct.entities


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ProductDetailsResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: Boolean // true
)