package com.hakemy.marketsamer.ui.showProduct.entities


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ProductsRelation(
    @SerializedName("discount")
    val discount: String, // 3.000
    @SerializedName("feature")
    val feature: String, // عربي
    @SerializedName("id")
    val id: Int, // 45
    @SerializedName("image_path")
    val imagePath: String, // https://sharwa.masharia.co/storage/images/mainProduct/plQKhA9Ixodz6RbayjroPN7CFI6FA9clyKT3mcGD.jpg
    @SerializedName("is_favourite")
    val isFavourite: Boolean, // false
    @SerializedName("mainprice")
    val mainprice: String, // 80.000
    @SerializedName("minorder")
    val minorder: Int, // 1
    @SerializedName("name")
    val name: String, // ديكور غرف مودرن
    @SerializedName("prefitPrice")
    val prefitPrice: String // 96
)