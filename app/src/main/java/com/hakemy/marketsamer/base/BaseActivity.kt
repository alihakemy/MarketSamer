package com.hakemy.marketsamer.base

import android.app.AlertDialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.view.Window
import androidx.lifecycle.lifecycleScope
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.utils.LocaleHelper
import java.util.*


abstract class BaseActivity : LocalizationActivity() {
    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            setLanguage(Locale(BaseApp.Languages.ARABIC))
        }
        dialog = AlertDialog.Builder(this).create()
        val inflate = LayoutInflater.from(this).inflate(R.layout.progress, null)
        dialog?.setView(inflate)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog?.setCancelable(true)

        if (dialog?.window != null) {
            dialog?.window!!
                .setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        }
    }
    fun showProgress() {
        if (dialog != null && dialog?.isShowing == false) {
            dialog?.show()
        }
    }


    fun hideProgress() {
        if (dialog?.isShowing == true) {
            dialog?.cancel()
            dialog?.hide()
        }
    }
    override fun onAfterLocaleChanged() {


    }
}