package com.hakemy.marketsamer.ui.home.moreProducts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivityMoreProductsBinding
import com.hakemy.marketsamer.ui.offers.OffersViewModel
import com.hakemy.marketsamer.ui.offers.recycler.OffersRecyclerViewAdapter
import com.hakemy.marketsamer.ui.offers.recycler.categories.CategoriesRecyclerViewAdapter
import com.hakemy.marketsamer.ui.search.SearchActivity
import com.hakemy.marketsamer.utils.ResultState

class MoreProducts : BaseActivity() {
    companion object{
        fun startMoreProducts(context: Context,title:String,id:String)
        {
            val intent=Intent(context,MoreProducts::class.java)
            intent.putExtra("title",title.toString())
            intent.putExtra("id",id.toString())

            context.startActivity(intent)
        }
    }
    lateinit var binding: ActivityMoreProductsBinding
    private lateinit var viewModel: OffersViewModel

    private lateinit var adapter: OffersRecyclerViewAdapter
    private lateinit var categoriesAdapter: CategoriesRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoreProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[OffersViewModel::class.java]

        viewModel.getOffersWithCategories(intent.getStringExtra("id").toString())
        binding.imageView4.setOnClickListener {
            SearchActivity.startSearchActivity(this)
        }

        binding.imageView6.setOnClickListener {
            onBackPressed()
        }
        binding.appCompatTextView.text=intent.getStringExtra("title")

        categoriesAdapter = CategoriesRecyclerViewAdapter() {
            viewModel.getOffersWithCategories(it.id.toString())

        }
        binding.recyclerView.layoutManager = LinearLayoutManager(
          this,
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.recyclerView.adapter = categoriesAdapter


        adapter = OffersRecyclerViewAdapter()
        val manager = GridLayoutManager(this, 2)
        binding.recyclerView2.layoutManager = manager
        binding.recyclerView2.adapter = adapter

        viewModel.offerResponse.observe(this, Observer {

            when (val result = it) {
                is ResultState.Error -> {

                    hideProgress()
                }
                ResultState.Loading -> {
                    showProgress()
                }
                is ResultState.Success -> {
                    hideProgress()

                    adapter.list.clear()

                    kotlin.runCatching {


                    }
                    if (!result.data.data.categories.isNullOrEmpty()) {

                        categoriesAdapter.submitList(category = result.data.data.categories)
                    }
                    if (!result.data.data.banner.isNullOrEmpty()) {
                        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                            override fun getSpanSize(position: Int): Int {
                                return if (position == 0) 2 else 1
                            }
                        }
                        adapter.setItemsBanner(result.data.data.banner as ArrayList<Any>)

                    }else{
                        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                            override fun getSpanSize(position: Int): Int {
                                return 1
                            }
                        }
                    }


                    adapter.setItems(result.data.data.products.productItem as ArrayList<Any>)


                }
            }

        })

        binding.imageView6.setOnClickListener {
            onBackPressed()
        }

        binding.imageView4.setOnClickListener {
            SearchActivity.startSearchActivity(this)
        }

    }
}