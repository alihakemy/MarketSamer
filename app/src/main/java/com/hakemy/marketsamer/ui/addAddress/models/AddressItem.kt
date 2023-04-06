package com.hakemy.marketsamer.ui.addAddress.models

import com.squareup.moshi.Json

data class AddressItem(

    @Json(name = "date") val date: String? = null,

    @Json(name = "user_name") val userName: String? = null,

    @Json(name = "region_id") val regionId: Int? = null,

    @Json(name = "house_number") val houseNumber: Int? = null,

    @Json(name = "floor_number") val floorNumber: Int? = null,

    @Json(name = "type") val type: String? = null,

    @Json(name = "lat") val lat: String? = null,

    @Json(name = "long") val lng: String? = null,

    @Json(name = "addresss") val address: String? = null,

    @Json(name = "information") val information: String? = null,

    @Json(name = "Apartment_number") val apartmentNumber: Int? = null,

    @Json(name = "piece_number") val pieceNumber: String? = null,

    @Json(name = "The_role") val theRole: String? = null,

    @Json(name = "user_id") val userId: Int? = null,

    @Json(name = "phone") val phone: Int? = null,

    @Json(name = "Street_number") val streetNumber: String? = null,

    @Json(name = "checked") var checked: String? = null,

    @Json(name = "id") val id: Int? = null,

    @Json(name = "email") val email: String? = null,

    @Json(name = "governments_id") val governmentsId: Int? = null
)
