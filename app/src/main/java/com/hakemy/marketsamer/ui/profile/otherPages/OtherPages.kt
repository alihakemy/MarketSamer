package com.hakemy.marketsamer.ui.profile.otherPages

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivityOtherPagesBinding
import com.hakemy.marketsamer.ui.search.SearchActivity

class OtherPages : BaseActivity() {


    companion object{
        fun startOtherPages(context: Context,title:String,content:String){
            val intent=Intent(context,OtherPages::class.java)
            intent.putExtra("title",title.toString())
            intent.putExtra("content",content.toString())

            context.startActivity(intent)

        }
    }


    lateinit var binding:ActivityOtherPagesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityOtherPagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView6.setOnClickListener {
            onBackPressed()
        }
        binding.imageView4.setOnClickListener {
            SearchActivity.startSearchActivity(this)
        }

        val content=intent.getStringExtra("content")
        binding.appCompatTextView.text=intent.getStringExtra("title")


        binding.someId.text=content

    }
}