package com.hakemy.marketsamer.ui.cart


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class CartRequestAdd(
    @SerializedName("company_id")
    val companyId: String, // 6237
    @SerializedName("mac_address")
    val macAddress: String, // 0003200

    @SerializedName("price")
    val price: String, // 2
    @SerializedName("product_id")
    val productId: String, // 8
    @SerializedName("quantity")
    val quantity: String
)