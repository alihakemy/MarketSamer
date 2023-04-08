package com.hakemy.marketsamer.ui.orderDetails.Model

data class Data(
    val TotalPrice: String,
    val `data`: DataX,
    val dicountCode: String,
    val info: Info,
    val paymentMethod: List<PaymentMethod>,
    val productCount: Int,
    val products: ArrayList<Product>,
    val totaShippingCost: String,
    val total: String
)