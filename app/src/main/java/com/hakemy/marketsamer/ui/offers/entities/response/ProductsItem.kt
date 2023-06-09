package com.hakemy.marketsamer.ui.offers.entities.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ProductsItem(
    @SerializedName("discount")
    val discount: String, // 3.000
    @SerializedName("feature")
    val feature: String, // arabic
    @SerializedName("id")
    val id: Int, // 45
    @SerializedName("image_path")
    val imagePath: String, // https://sharwa.masharia.co/storage/images/mainProduct/plQKhA9Ixodz6RbayjroPN7CFI6FA9clyKT3mcGD.jpg
    @SerializedName("is_favourite")
    var isFavourite: Boolean, // false
    @SerializedName("mainprice")
    val mainprice: String, // 80.000
    @SerializedName("minorder")
    val minorder: Int, // 1
    @SerializedName("name")
    val name: String, // ديكور غرف مودرن
    @SerializedName("prefitPrice")
    val prefitPrice: String // 96
    ,

    var  qty_name:String=""
)