package com.hakemy.marketsamer.ui.onboarding.servicesModels


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Data(
    @SerializedName("content")
    val content: String, // <p>وفرنالكم جميع احتياجاتكم من&nbsp;</p><p>مواد البناء في مكان واحد</p>
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("image")
    val image: String, // https://sharwa.masharia.co/storage/images/landingboard/aYOQUGQRaUUnYbQ3NTesfs4HiAYyZWtkpm9BnlKf.png
    @SerializedName("title")
    val title: String // كل مواد البناء التى تحتاجها
)