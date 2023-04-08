package com.hakemy.marketsamer.ui.showProduct.entities


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Option(
    @SerializedName("choosecount")
    val choosecount: String?, // 3
    @SerializedName("id")
    val id: Int, // 12
    @SerializedName("options")
    val options: List<OptionX>,
    @SerializedName("product_id")
    val productId: Int, // 44
    @SerializedName("title")
    val title: String?, // الاضافات
    @SerializedName("type")
    val type: String?, // multi
    @SerializedName("updated_at")
    val updatedAt: String? // 2022-08-13 04:57:41
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        TODO("options"),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Option> {
        override fun createFromParcel(parcel: Parcel): Option {
            return Option(parcel)
        }

        override fun newArray(size: Int): Array<Option?> {
            return arrayOfNulls(size)
        }
    }
}