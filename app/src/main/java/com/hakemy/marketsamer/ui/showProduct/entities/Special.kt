package com.hakemy.marketsamer.ui.showProduct.entities


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Special(
    @SerializedName("id")
    val id: Int, // 2
    @SerializedName("input_key")
    val inputKey: String, // asds
    @SerializedName("input_value")
    val inputValue: String // adadas
)