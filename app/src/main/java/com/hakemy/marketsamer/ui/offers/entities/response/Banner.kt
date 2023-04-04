package com.hakemy.marketsamer.ui.offers.entities.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Banner(
    @SerializedName("id")
    val id: Int, // 59
    @SerializedName("imagePath")
    val imagePath: String, // https://sharwa.masharia.co/storage/images/slider/2023-03-13-hero.jpg
    @SerializedName("showNumber")
    val showNumber: Int, // 32
    @SerializedName("typeShow")
    val typeShow: String // product
)