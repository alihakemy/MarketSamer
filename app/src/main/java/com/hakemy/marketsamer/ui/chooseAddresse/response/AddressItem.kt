package com.hakemy.marketsamer.ui.chooseAddresse.response

import android.os.Parcel
import android.os.Parcelable
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
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(userName)
        parcel.writeValue(regionId)
        parcel.writeValue(houseNumber)
        parcel.writeValue(floorNumber)
        parcel.writeString(type)
        parcel.writeString(lat)
        parcel.writeString(lng)
        parcel.writeString(address)
        parcel.writeString(information)
        parcel.writeValue(apartmentNumber)
        parcel.writeString(pieceNumber)
        parcel.writeString(theRole)
        parcel.writeValue(userId)
        parcel.writeValue(phone)
        parcel.writeString(streetNumber)
        parcel.writeString(checked)
        parcel.writeValue(id)
        parcel.writeString(email)
        parcel.writeValue(governmentsId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AddressItem> {
        override fun createFromParcel(parcel: Parcel): AddressItem {
            return AddressItem(parcel)
        }

        override fun newArray(size: Int): Array<AddressItem?> {
            return arrayOfNulls(size)
        }
    }
}
