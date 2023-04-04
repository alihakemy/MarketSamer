package com.hakemy.marketsamer.ui.home.entities.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class SubCat(
    @SerializedName("id")
    val id: Int, // 31
    @SerializedName("imagePath")
    val imagePath: String, // https://sharwa.masharia.co/storage/images/category/2022-10-03-is1.jpg
    @SerializedName("name")
    val name: String, // عازل ماء
    @SerializedName("parent_id")
    val parentId: String, // 17
    @SerializedName("sub_cat")
    val subCat: List<SubCatX>
)