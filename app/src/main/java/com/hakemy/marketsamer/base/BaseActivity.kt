package com.hakemy.marketsamer.base

import android.app.ProgressDialog
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.hakemy.marketsamer.utils.LocaleHelper
import java.util.*


abstract class BaseActivity : LocalizationActivity() {
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            setLanguage(Locale(BaseApp.Languages.ARABIC))
        }
        progressDialog= ProgressDialog(this)
        progressDialog.setMessage("loading")
    }
    override fun onAfterLocaleChanged() {


    }
}