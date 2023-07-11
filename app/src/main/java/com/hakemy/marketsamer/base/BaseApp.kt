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
            "rdihYRkPQ1922NG2QYRtq4U69NUHxteS-Jn1p2E3B9odNxhmphbGltcL-5E97l1KqveeTnQjfJ1lTPT7DygVd6glt-gj0DhonHgZ5Qe7QFQyuBrqnYycuMR3perDo6cmwzmcEhgwem8vngjbWH8MhVn3Lehfln-d9V4yO7SEkIWaHKt9fZTNSg71kbcTV1xQv8OHTdwoWzjgCYy7FwZQLsgsdAYeQ0gM51Y-HMzwprOzVE1CAl4gHuFmK0_18n_UIBULQzzWp6fijBjFWjLh-WdQRjvgWnamfTv2hLcNQ_Zxc1VzFFdcyK7_YJxsP9t4KTJl1Ip3mEfDAnklx_uM5KBc8a256RZfGFoCrVIsKP-R4LpMSN1haIojw8ZApeabH0s5FK0kAEelmicdIIx3XB2HhcROrxVzQmwMkM4z88gOyFIauNxgeJcqvvDhCS1KAsM5U3N34TvyNuDHipdHhlpOHUgw72vgJLaa-DlmAPGv_mhYH8yzWK7dDd-MMTPGMCZ9bDuYdX8U0X0oPLgDjZAb1lGVsaCk1D8UB4OGP5-90ik78W_-6-N-OVyX8YcXxTbmVQlYhEr1Er4DqHEP3SyCphj1sT02C6N_M22NsxOeVBIUgNByUtvttntTG9VZ3Pt07g29C-B0egzCKg9LJMpmnNjOjtteSiwGl4WZTbemJj8NP-USVIzN7m1kI32Y-KWUltDTg8bjl59BN6x6_urtRlFYM1MR6xWpAqAHHafQEeERSjz6GSh2tfxxaEKLTPYzTw",
            MFCountry.KUWAIT,
            MFEnvironment.LIVE
                )

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