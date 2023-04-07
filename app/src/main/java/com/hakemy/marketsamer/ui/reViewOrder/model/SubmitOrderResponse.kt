package com.hakemy.marketsamer.ui.reViewOrder.model

import com.squareup.moshi.Json

data class SubmitOrderResponse(

    @Json(name = "order_data") val orderData: SubmitOrderData? = null
)
data class SubmitOrderData(

    @Json(name = "total") val total: String? = null,

    @Json(name = "total_price") val totalPrice: Double? = null,

    @Json(name = "order_created") val orderCreated: String? = null,

    @Json(name = "product_count") val productCount: Int? = null,

    @Json(name = "order_number") val orderNumber: String? = null,

    @Json(name = "price_ship") val priceShip: String? = null,

    @Json(name = "payment_method") val paymentMethod: String? = null,

    @Json(name = "status") val status: String? = null
)