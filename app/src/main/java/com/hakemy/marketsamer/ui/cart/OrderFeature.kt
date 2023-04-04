package com.hakemy.marketsamer.ui.cart


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class OrderFeature(
    @SerializedName("count")
    val count: Int, // 4
    @SerializedName("feature_id")
    val featureId: Int, // 4
    @SerializedName("option_id")
    val optionId: Int, // 12
    @SerializedName("price")
    val price: Int, // 4
    @SerializedName("title")
    val title: String // asda
)