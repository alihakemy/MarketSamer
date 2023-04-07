package com.hakemy.marketsamer.base

import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.akexorcist.localizationactivity.ui.LocalizationApplication
import com.hakemy.marketsamer.base.BaseApp.Languages.ARABIC
import com.myfatoorah.sdk.enums.MFCountry
import com.myfatoorah.sdk.enums.MFEnvironment
import com.myfatoorah.sdk.views.MFSDK
import okhttp3.OkHttpClient
import java.util.*

class BaseApp : LocalizationApplication() {


    object Languages {
        const val ARABIC = "ar"
        const val ENGLISH = "en"
    }

    private lateinit var notificationManager: NotificationManager

    companion object {
        const val CHANNEL_ID = "base_channel_id"
        const val CHANNEL_NAME = "base_channel"
        var context:Context ?=null

    }


    override fun getDefaultLanguage(base: Context): Locale {
        return Locale(ARABIC)
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext

        ThemeHelper.applyTheme(ThemeHelper.ThemeMode.LIGHT)
        MFSDK.init(
            "rLtt6JWvbUHDDhsZnfpAhpYk4dxYDQkbcPTyGaKp2TYqQgG7FGZ5Th_WD53Oq8Ebz6A53njUoo1w3pjU1D4vs_ZMqFiz_j0urb_BH9Oq9VZoKFoJEDAbRZepGcQanImyYrry7Kt6MnMdgfG5jn4HngWoRdKduNNyP4kzcp3mRv7x00ahkm9LAK7ZRieg7k1PDAnBIOG3EyVSJ5kK4WLMvYr7sCwHbHcu4A5WwelxYK0GMJy37bNAarSJDFQsJ2ZvJjvMDmfWwDVFEVe_5tOomfVNt6bOg9mexbGjMrnHBnKnZR1vQbBtQieDlQepzTZMuQrSuKn-t5XZM7V6fCW7oP-uXGX-sMOajeX65JOf6XVpk29DP6ro8WTAflCDANC193yof8-f5_EYY-3hXhJj7RBXmizDpneEQDSaSz5sFk0sV5qPcARJ9zGG73vuGFyenjPPmtDtXtpx35A-BVcOSBYVIWe9kndG3nclfefjKEuZ3m4jL9Gg1h2JBvmXSMYiZtp9MR5I6pvbvylU_PP5xJFSjVTIz7IQSjcVGO41npnwIxRXNRxFOdIUHn0tjQ-7LwvEcTXyPsHXcMD8WtgBh-wxR8aKX7WPSsT1O8d8reb2aR7K3rkV3K82K_0OgawImEpwSvp9MNKynEAJQS6ZHe_J_l77652xwPNxMRTMASk1ZsJL",
            MFCountry.KUWAIT,
            MFEnvironment.TEST
        );

    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

    }


}

object ThemeHelper {
    fun applyTheme(theme: ThemeMode) {
        when (theme) {
            ThemeMode.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            ThemeMode.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            ThemeMode.BATTERY_SAVER -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
            ThemeMode.DEFAULT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    enum class ThemeMode { LIGHT, DARK, BATTERY_SAVER, DEFAULT }
}