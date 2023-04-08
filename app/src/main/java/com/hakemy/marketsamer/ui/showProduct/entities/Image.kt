package com.hakemy.marketsamer.ui.showProduct.entities


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Image(
    @SerializedName("imagePath")
    val imagePath: String? // https://sharwa.masharia.co/uploads/product/44/1660230334ألوان-دهانات-2020.jpeg
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imagePath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Image> {
        override fun createFromParcel(parcel: Parcel): Image {
            return Image(parcel)
        }

        override fun newArray(size: Int): Array<Image?> {
            return arrayOfNulls(size)
        }
    }
}