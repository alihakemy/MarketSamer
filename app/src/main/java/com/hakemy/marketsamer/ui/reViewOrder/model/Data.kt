package com.hakemy.marketsamer.ui.reViewOrder.model

data class Data(
    val TotalPrice: String,
    val `data`: DataX,
    val dicountCode: String,
    val paymentMethod: ArrayList<PaymentMethod>,
    val productCount: Int,
    val products: ArrayList<Product>,
    val totaShippingCost: String,
    val total: String
)