package com.hakemy.marketsamer.ui.showProduct.entities


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Data(
    @SerializedName("products")
    val products: Products,
    @SerializedName("productsRelation")
    val productsRelation: List<ProductsRelation>
)