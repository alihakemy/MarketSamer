package com.hakemy.marketsamer.ui.offers.entities.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Products(
    @SerializedName("current_page")
    val currentPage: Int, // 1
    @SerializedName("data")
    val productItem: ArrayList<ProductsItem>,
    @SerializedName("first_page_url")
    val firstPageUrl: String, // https://sharwa.masharia.co/api/offers?page=1
    @SerializedName("from")
    val from: Int, // 1
    @SerializedName("next_page_url")
    val nextPageUrl: Any, // null
    @SerializedName("path")
    val path: String, // https://sharwa.masharia.co/api/offers
    @SerializedName("per_page")
    val perPage: Int, // 20
    @SerializedName("prev_page_url")
    val prevPageUrl: Any, // null
    @SerializedName("to")
    val to: Int // 2
)