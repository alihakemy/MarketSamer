package com.hakemy.marketsamer.ui.register.serviceModel.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class User(
    @SerializedName("email")
    val email: String?= "", // alisami@gmail.com
    @SerializedName("id")
    val id: Int, // 6302
    @SerializedName("imagePath")
    val imagePath: String, // https://sharwa.masharia.co/storage/images/user
    @SerializedName("name")
    val name: String ?="", // samer
    @SerializedName("phone")
    val phone: String, // +96555123454678
    @SerializedName("rate")
    val rate: Int, // 0
    @SerializedName("Roles")
    val roles: String, // clients
    @SerializedName("status")
    val status: Int // 0
)