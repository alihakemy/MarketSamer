package com.hakemy.marketsamer.ui.onboarding.servicesModels


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class OnboardingModel(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: Boolean // true
)