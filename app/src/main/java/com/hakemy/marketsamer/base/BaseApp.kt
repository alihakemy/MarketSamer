package com.hakemy.marketsamer.base

import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.akexorcist.localizationactivity.ui.LocalizationApplication
import com.hakemy.marketsamer.base.BaseApp.Languages.ARABIC
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