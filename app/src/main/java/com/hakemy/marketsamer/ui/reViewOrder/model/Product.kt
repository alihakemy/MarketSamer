package com.hakemy.marketsamer.ui.reViewOrder.model

data class Product(
    val arrivalFrom: String,
    val arrivalto: String,
    val feature_product: List<FeatureProduct>,
    val id: Int,
    val imagePath: String,
    val mainQuantity: String,
    val name: String,
    val price: String,
    val product_id: Int,
    val quantity: String,
    val shipping_cost: String,
    val status_product: String,
    val status_product_name: String,
    val total_price: String
)
data class FeatureProduct(
    val feature_name: String,
    val feature_price: Int,
    val feature_value: String
)