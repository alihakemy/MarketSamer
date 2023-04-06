package com.hakemy.marketsamer.ui.cart.models

data class Data(
    val TotalPrice: String,
    val order_info: OrderInfo,
    val products: ArrayList<CartItemProduct>,
    val totaShippingCost: String,
    val total: String
)