package com.hakemy.marketsamer.ui.home

import androidx.lifecycle.*
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.base.BaseFragment
import com.hakemy.marketsamer.databinding.FragmentHomeBinding
import com.hakemy.marketsamer.ui.home.adapters.bestOffers.BestProductRecyclerViewAdapterMainScreen
import com.hakemy.marketsamer.ui.home.adapters.favOffers.ProductRecyclerViewAdapterMainScreen
import com.hakemy.marketsamer.ui.home.adapters.sections.CategoryMainAdapter
import com.hakemy.marketsamer.ui.home.entities.response.Category
import com.hakemy.marketsamer.ui.home.entities.response.ChooseU
import com.hakemy.marketsamer.ui.home.entities.response.Product
import com.hakemy.marketsamer.ui.home.moreProducts.MoreProducts
import com.hakemy.marketsamer.ui.search.SearchActivity
import com.hakemy.marketsamer.utils.ResultState
import kotlinx.coroutines.launch

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var categoryMainAdapter: CategoryMainAdapter
    private lateinit var productRecyclerViewAdapterMainScreen: ProductRecyclerViewAdapterMainScreen

    private lateinit var bestProductRecyclerViewAdapterMainScreen: BestProductRecyclerViewAdapterMainScreen

    override fun onCreateBinding() {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                homeViewModel.getMainScreen()
            }
        }
        homeViewModel.getMainScreen.observe(this, Observer {

            when (val result = it) {
                is ResultState.Error -> {
                    hideProgress()

                }
                ResultState.Loading -> {
                    showProgress()
                }
                is ResultState.Success -> {
                    hideProgress()
                    val imageList = ArrayList<SlideModel>()
                    result.data.data.slider.forEach {
                        imageList.add(SlideModel(it.imagePath, ScaleTypes.FIT))

                    }
                    binding.imageSlider.setImageList(imageList)


                    val imageList2 = ArrayList<SlideModel>()
                    result.data.data.banner.forEach {
                        imageList2.add(SlideModel(it.imagePath, ScaleTypes.FIT))

                    }
                    binding.slider2.setImageList(imageList2)

                    setCategories(result.data.data.categories)

                    setProductRecyclerViewAdapterMainScreen(result.data.data.products)
                    setBestProductRecyclerViewAdapterMainScreen(result.data.data.chooseUs)
                }
            }


        })

        binding.iToolbar.layoutToolbar.setOnClickListener {

            SearchActivity.startSearchActivity(requireContext())

        }
        binding.appCompatTextView6.setOnClickListener {
            MoreProducts.startMoreProducts(requireContext(), getString(R.string.best_offers),"17")
        }
        binding.ivCompaniesMore.setOnClickListener {
            MoreProducts.startMoreProducts(requireContext(), getString(R.string.fav_offers),"18")
        }
    }

    private fun setCategories(Category: List<Category>) {
        with(binding) {
            categoryMainAdapter = CategoryMainAdapter(requireContext())

            categoryMainAdapter.insertItems(Category)
            rvCategories.adapter = categoryMainAdapter

        }
    }

    private fun setProductRecyclerViewAdapterMainScreen(Product: List<Product>) {
        with(binding) {
            productRecyclerViewAdapterMainScreen = ProductRecyclerViewAdapterMainScreen()

            productRecyclerViewAdapterMainScreen.setItems(Product)
            rvFavProduct.adapter = productRecyclerViewAdapterMainScreen

        }
    }

    private fun setBestProductRecyclerViewAdapterMainScreen(Product: List<ChooseU>) {
        with(binding) {
            bestProductRecyclerViewAdapterMainScreen = BestProductRecyclerViewAdapterMainScreen()

            bestProductRecyclerViewAdapterMainScreen.setItems(Product)
            rvOurChoice.adapter = bestProductRecyclerViewAdapterMainScreen

        }
    }

}