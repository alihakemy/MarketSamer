package com.hakemy.marketsamer.ui.showProduct.entities


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Image(
    @SerializedName("imagePath")
    val imagePath: String // https://sharwa.masharia.co/uploads/product/44/1660230334ألوان-دهانات-2020.jpeg
)