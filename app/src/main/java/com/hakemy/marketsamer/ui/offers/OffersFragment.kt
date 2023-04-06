package com.hakemy.marketsamer.ui.offers

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hakemy.marketsamer.base.BaseFragment
import com.hakemy.marketsamer.databinding.FragmentDashboardBinding
import com.hakemy.marketsamer.ui.offers.recycler.OffersRecyclerViewAdapter
import com.hakemy.marketsamer.ui.offers.recycler.categories.CategoriesRecyclerViewAdapter
import com.hakemy.marketsamer.ui.search.SearchActivity
import com.hakemy.marketsamer.utils.ResultState

class OffersFragment :
    BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {
    private lateinit var viewModel: OffersViewModel

    private lateinit var adapter: OffersRecyclerViewAdapter
    private lateinit var categoriesAdapter: CategoriesRecyclerViewAdapter
    override fun onCreateBinding() {
        viewModel = ViewModelProvider(this)[OffersViewModel::class.java]


        categoriesAdapter = CategoriesRecyclerViewAdapter() {
            viewModel.getOffersWithCategories(it.id.toString())

        }
        binding.recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.recyclerView.adapter = categoriesAdapter


        adapter = OffersRecyclerViewAdapter()
        val manager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView2.layoutManager = manager
        binding.recyclerView2.adapter = adapter

        viewModel.offerResponse.observe(this, Observer {

            when (val result = it) {
                is ResultState.Error -> {

                    progressDialog.dismiss()
                }
                ResultState.Loading -> {
                    progressDialog.show()
                }
                is ResultState.Success -> {
                    progressDialog.dismiss()


                    adapter.list.clear()

                    kotlin.runCatching {


                    }
                    if (!result.data.data.categories.isNullOrEmpty()) {

                        categoriesAdapter.submitList(category = result.data.data.categories)
                    }
                    if (!result.data.data.banner.isNullOrEmpty()) {
                        manager.spanSizeLookup = object : SpanSizeLookup() {
                            override fun getSpanSize(position: Int): Int {
                                return if (position == 0) 2 else 1
                            }
                        }
                        adapter.setItemsBanner(result.data.data.banner as ArrayList<Any>)

                    }else{
                        manager.spanSizeLookup = object : SpanSizeLookup() {
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
            requireActivity().onBackPressed()
        }

        binding.imageView4.setOnClickListener {
            SearchActivity.startSearchActivity(requireContext())
        }


    }

}