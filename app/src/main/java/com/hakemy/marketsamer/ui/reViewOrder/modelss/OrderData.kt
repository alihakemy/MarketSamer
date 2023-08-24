package com.hakemy.marketsamer.ui.reViewOrder.modelss

data class OrderData(
    val order_created: String,
    val order_number: String,
    val payment_method: String,
    val price_ship: String,
    val product_count: Int,
    val status: String,
    val total: String,
    val total_price: Double
)