package com.hakemy.marketsamer.ui.home.entities.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class SubCatX(
    @SerializedName("id")
    val id: Int, // 67
    @SerializedName("imagePath")
    val imagePath: String, // https://sharwa.masharia.co/storage/images/category/2022-10-03-M 1.jpg
    @SerializedName("name")
    val name: String, // مغاسل
    @SerializedName("parent_id")
    val parentId: String, // 43
    @SerializedName("sub_cat")
    val subCat: List<Any>
)