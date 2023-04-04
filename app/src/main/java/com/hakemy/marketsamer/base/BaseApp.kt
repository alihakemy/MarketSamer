package com.hakemy.marketsamer.base

import android.app.NotificationManager
import android.content.Context
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



    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

    }


}