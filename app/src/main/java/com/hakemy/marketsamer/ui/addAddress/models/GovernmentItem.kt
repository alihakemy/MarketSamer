package com.hakemy.marketsamer.ui.addAddress.models

import com.squareup.moshi.Json

data class GovernmentItem(

    @Json(name = "address_latitude") val addressLatitude: String? = null,

    @Json(name = "regions") val regions: MutableList<RegionItem>? = null,

    @Json(name = "address_address") val addressAddress: String? = null,

    @Json(name = "updated_at") val updatedAt: String? = null,

    @Json(name = "name") val name: String? = null,

    @Json(name = "address_longitude") val addressLongitude: String? = null,

    @Json(name = "created_at") val createdAt: String? = null,

    @Json(name = "id") val id: Int? = null,

    @Json(name = "country_id") val countryId: String? = null
)
data class RegionItem(

    @Json(name = "address_latitude") val addressLatitude: String? = null,

    @Json(name = "address_address") val addressAddress: String? = null,

    @Json(name = "updated_at") val updatedAt: String? = null,

    @Json(name = "name") val name: String? = null,

    @Json(name = "address_longitude") val addressLongitude: String? = null,

    @Json(name = "created_at") val createdAt: String? = null,

    @Json(name = "id") val id: Int? = null,

    @Json(name = "country_id") val countryId: String? = null,

    @Json(name = "status") val status: String? = null,

    @Json(name = "governments_id") val governmentsId: String? = null
)