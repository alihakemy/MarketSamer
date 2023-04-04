package com.hakemy.marketsamer.ui.home.entities.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Banner(
    @SerializedName("id")
    val id: Int, // 56
    @SerializedName("imagePath")
    val imagePath: String, // https://sharwa.masharia.co/storage/images/slider/g71jZaEGZ0J0o7NfnU3ARltQz9mi6m6zJjZ08mch.jpg
    @SerializedName("showNumber")
    val showNumber: Int, // 43
    @SerializedName("typeShow")
    val typeShow: String // product
)