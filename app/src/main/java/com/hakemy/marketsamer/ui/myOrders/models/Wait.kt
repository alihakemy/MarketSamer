package com.hakemy.marketsamer.ui.myOrders.models

data class Wait(
    val date_order: String,
    val id: Int,
    val images: List<Image>,
    val items: String,
    val order_number: String,
    val status: String,
    val total_price: String
)