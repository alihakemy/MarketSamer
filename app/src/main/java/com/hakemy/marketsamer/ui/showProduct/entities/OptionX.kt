package com.hakemy.marketsamer.ui.showProduct.entities


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class OptionX(
    @SerializedName("count")
    val count: String,
    @SerializedName("feature_id")
    val featureId: String, // 12
    @SerializedName("id")
    val id: Int, // 28
    @SerializedName("price")
    val price: String, // 3.00
    @SerializedName("title")
    val title: String, // صغير
    @SerializedName("updated_at")
    val updatedAt: String // 2022-08-13 04:57:52
)