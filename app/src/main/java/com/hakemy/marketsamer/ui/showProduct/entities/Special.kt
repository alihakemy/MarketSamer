package com.hakemy.marketsamer.ui.showProduct.entities


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Special(
    @SerializedName("id")
    val id: Int, // 2
    @SerializedName("input_key")
    val inputKey: String?, // asds
    @SerializedName("input_value")
    val inputValue: String? // adadas
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(inputKey)
        parcel.writeString(inputValue)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Special> {
        override fun createFromParcel(parcel: Parcel): Special {
            return Special(parcel)
        }

        override fun newArray(size: Int): Array<Special?> {
            return arrayOfNulls(size)
        }
    }
}