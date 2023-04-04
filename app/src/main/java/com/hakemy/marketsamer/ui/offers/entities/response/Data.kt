package com.hakemy.marketsamer.ui.offers.entities.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Data(
    @SerializedName("banner")
    val banner: List<Banner>,
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("products")
    val products: Products
)