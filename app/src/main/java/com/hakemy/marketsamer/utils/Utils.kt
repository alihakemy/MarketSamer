package com.hakemy.marketsamer.utils

import android.app.Application
import android.content.Context
import android.provider.Settings
import android.widget.Toast
import es.dmoral.toasty.Toasty
import java.net.NetworkInterface
import java.util.*


fun getMacAddr(context: Context): String {
    try {
        val all = Collections.list(NetworkInterface.getNetworkInterfaces())
        for (nif in all) {
            if (!nif.getName().equals("wlan0", ignoreCase = true)) continue

            val macBytes = nif.getHardwareAddress() ?: return ""

            val res1 = StringBuilder()
            for (b in macBytes) {
                //res1.append(Integer.toHexString(b & 0xFF) + ":");
                res1.append(String.format("%02X:", b))
            }

            if (res1.length > 0) {
                res1.deleteCharAt(res1.length - 1)
            }
            return res1.toString()
        }
    } catch (ex: Exception) {
    }
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
