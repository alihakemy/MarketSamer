package com.hakemy.marketsamer.ui.orderDetails.Model

data class PaymentMethod(
    val id: Int,
    val image_path: String,
    val name: String,
    val paid: String,
    val type: String
)