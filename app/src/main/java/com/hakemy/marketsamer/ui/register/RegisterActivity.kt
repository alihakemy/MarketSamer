package com.hakemy.marketsamer.ui.register

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.RegisterActivityBinding

class RegisterActivity :BaseActivity() {
    companion object{

        fun startRegisterActivity(context: Context){
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)

        }
    }
    lateinit var binding : RegisterActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= RegisterActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}