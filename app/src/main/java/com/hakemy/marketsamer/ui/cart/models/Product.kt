package com.hakemy.marketsamer.ui.cart.models

data class CartItemProduct(
    val compId: Int,
    val feature_product: List<FeatureProduct>,
    val id: Int,
    val imagePath: String,
    val mainQuantity: String,
    val name: String,
    val price: String,
    var quantity: String,
    val shipping_cost: String
)