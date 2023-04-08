package com.hakemy.marketsamer.ui.home.moreSections

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivityMoreSectionsBinding
import com.hakemy.marketsamer.ui.home.adapters.sections.CategoryMainAdapter
import com.hakemy.marketsamer.ui.search.SearchActivity
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.launch

class MoreSectionsActivity : BaseActivity() {
    companion object{
        fun startMoreSectionsActivity(context:Context){
            val intent =Intent(context,MoreSectionsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
    lateinit var binding: ActivityMoreSectionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoreSectionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView4.setOnClickListener {
            SearchActivity.startSearchActivity(this)
        }

        binding.imageView6.setOnClickListener {
            onBackPressed()
        }
        showProgress()
        lifecycleScope.launch {

            try {

                val result = RetrofitService.servicesApi().categories()
                val categoryMainAdapter = CategoryMainAdapter(this@MoreSectionsActivity)

                categoryMainAdapter.insertItems(result.data.categories)
                binding.recyclerView2.adapter = categoryMainAdapter
                binding.recyclerView2.layoutManager =
                    GridLayoutManager(this@MoreSectionsActivity, 4)

                hideProgress()
            } catch (e: java.lang.Exception) {

                hideProgress()
            }

        }

    }
}