package com.hakemy.marketsamer.ui.home.entities.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Product(
    @SerializedName("discount")
    val discount: String, // 2.000
    @SerializedName("feature")
    val feature: String, // عربي
    @SerializedName("id")
    val id: Int, // 55
    @SerializedName("image_path")
    val imagePath: String, // https://sharwa.masharia.co/storage/images/mainProduct/xCBbk8cPLcistUdNDi5BkmZi6fAtUfbe0T75hnk2.png
    @SerializedName("is_favourite")
    var isFavourite: Boolean, // false
    @SerializedName("mainprice")
    val mainprice: String, // 50.000
    @SerializedName("minorder")
    val minorder: Int, // 2
    @SerializedName("name")
    val name: String, // منتج رقم ٣
    @SerializedName("prefitPrice")
    val prefitPrice: String // 96
    ,

    var  qty_name:String=""
)