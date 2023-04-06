package com.hakemy.marketsamer.ui.search

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivitySearchBinding
import com.hakemy.marketsamer.ui.offers.entities.response.ProductsItem
import com.hakemy.marketsamer.ui.register.RegisterActivity
import com.hakemy.marketsamer.ui.showProduct.ShowProductActivity
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.SharePreferenceManager
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.launch

class SearchActivity : BaseActivity(), SearchProductsAdapter.RecycleListener {

    companion object{

        fun startSearchActivity(context: Context){
            val intent= Intent(context,SearchActivity::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

        }

    }
    lateinit var binding: ActivitySearchBinding
    lateinit var viewModel: SearchViewModel
    private lateinit var searchProductsAdapter: SearchProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchProductsAdapter = SearchProductsAdapter(this, this)

        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        with(binding) {
            etSearch.doAfterTextChanged { if (it?.length!! >= 3) search() }
            ivBack.setOnClickListener { onBackPressed() }
        }

        viewModel.searchResponse.observe(this, Observer {

            when (val result = it) {
                is ResultState.Error -> {

                    hideProgress()
                }
                ResultState.Loading -> {
                    showProgress()
                }
                is ResultState.Success -> {
                    hideProgress()
                    with(binding) {
                        result.data.data?.productItem?.let { it1 ->
                            searchProductsAdapter.insertItems(
                                it1
                            )
                        }
                        rvProducts.adapter = searchProductsAdapter
                    }
                }
            }

        })

    }

    private fun search() = viewModel.search(binding.etSearch.text.toString())
    override fun onProductClicked(position: Int, product: ProductsItem) {

        ShowProductActivity.startShowProductActivity(productId = product.id.toString(),this)

    }

    override fun onProductIsFavouriteClicked(position: Int, product: ProductsItem) {
        if(SharePreferenceManager.getIsVerified()){

            lifecycleScope.launch {
                kotlin.runCatching { RetrofitService.servicesApi().isFavorite(product.id.toString()) }

            }

        }else{
            RegisterActivity.startRegisterActivity(this)

        }
    }

}