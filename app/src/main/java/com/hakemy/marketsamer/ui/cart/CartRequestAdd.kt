package com.hakemy.marketsamer.ui.cart


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class CartRequestAdd(
    @SerializedName("company_id")
    val companyId: Int, // 6237
    @SerializedName("mac_address")
    val macAddress: String, // 0003200
    @SerializedName("order_feature")
    val orderFeature: List<OrderFeature>,
    @SerializedName("price")
    val price: Int, // 2
    @SerializedName("product_id")
    val productId: Int, // 8
    @SerializedName("quantity")
    val quantity: Int, // 1
    @SerializedName("shipping_cost")
    val shippingCost: Int // 33
)