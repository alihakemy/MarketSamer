package com.hakemy.marketsamer.ui.profile.otherPages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hakemy.marketsamer.databinding.ActivityOtherPagesBinding

class OtherPages : AppCompatActivity() {


    lateinit var binding:ActivityOtherPagesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityOtherPagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}