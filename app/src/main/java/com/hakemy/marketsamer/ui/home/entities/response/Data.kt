package com.hakemy.marketsamer.ui.home.entities.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Data(
    @SerializedName("banner")
    val banner: List<Banner>,
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("choose_us")
    val chooseUs: List<ChooseU>,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("slider")
    val slider: List<Slider>
)