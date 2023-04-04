package com.hakemy.marketsamer.ui.offers.entities.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Category(
    @SerializedName("id")
    val id: Int, // 17
    @SerializedName("imagePath")
    val imagePath: String, // https://sharwa.masharia.co/storage/images/category/2022-10-03-f1.jpg
    @SerializedName("name")
    val name: String ,
    var isSelected: Boolean = false
)