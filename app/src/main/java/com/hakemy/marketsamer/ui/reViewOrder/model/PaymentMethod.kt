package com.hakemy.marketsamer.ui.reViewOrder.model

data class PaymentMethod(
    val id: Int,
    val image_path: String,
    val name: String,
    val type: String,
    var isChecked:Boolean=false
)