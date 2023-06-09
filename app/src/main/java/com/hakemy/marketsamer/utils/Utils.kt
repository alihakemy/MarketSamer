package com.hakemy.marketsamer.utils

import android.app.Application
import android.content.Context
import android.provider.Settings
import android.widget.Toast
import es.dmoral.toasty.Toasty
import java.net.NetworkInterface
import java.util.*


fun getMacAddr(context: Context): String {

    val android_id = Settings.Secure.getString(
        context.getContentResolver(),
        Settings.Secure.ANDROID_ID
    );
    return android_id
}

fun Context.showToast(
    message: String?,
    toastType: ToastType = ToastType.ERROR,
    withIcon: Boolean = true
) {
    if (message.isNullOrEmpty()) return
    when (toastType) {
        ToastType.SUCCESS -> {
            Toasty.success(this, message, Toast.LENGTH_SHORT, withIcon).show()
        }
        ToastType.ERROR -> {
            Toasty.error(this, message, Toast.LENGTH_SHORT, withIcon).show()
        }
        ToastType.INFO -> {
            Toasty.info(this, message, Toast.LENGTH_SHORT, withIcon).show()
        }
        ToastType.WARNING -> {
            Toasty.warning(this, message, Toast.LENGTH_SHORT, withIcon).show()
        }
    }
}

enum class ToastType {
    SUCCESS, ERROR, WARNING, INFO
}
