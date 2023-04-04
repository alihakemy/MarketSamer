package com.hakemy.marketsamer.ui.showProduct.entities


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Option(
    @SerializedName("choosecount")
    val choosecount: String, // 3
    @SerializedName("id")
    val id: Int, // 12
    @SerializedName("options")
    val options: List<OptionX>,
    @SerializedName("product_id")
    val productId: Int, // 44
    @SerializedName("title")
    val title: String, // الاضافات
    @SerializedName("type")
    val type: String, // multi
    @SerializedName("updated_at")
    val updatedAt: String // 2022-08-13 04:57:41
)