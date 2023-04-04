package com.hakemy.marketsamer.ui.home.entities.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Slider(
    @SerializedName("id")
    val id: Int, // 54
    @SerializedName("imagePath")
    val imagePath: String, // https://sharwa.masharia.co/storage/images/slider/6DKMDru0WNnkQHyLfFvAHrI8LcTdzGVcdb3bTeUJ.jpg
    @SerializedName("showNumber")
    val showNumber: Int, // 0
    @SerializedName("typeShow")
    val typeShow: Any // null
)