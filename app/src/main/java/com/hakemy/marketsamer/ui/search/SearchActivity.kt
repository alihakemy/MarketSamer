package com.hakemy.marketsamer.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivitySearchBinding

class SearchActivity : BaseActivity() {
    lateinit var binding:ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}