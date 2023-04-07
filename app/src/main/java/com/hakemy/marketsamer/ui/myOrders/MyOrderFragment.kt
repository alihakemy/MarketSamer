package com.hakemy.marketsamer.ui.myOrders

import android.graphics.Color
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.base.BaseFragment
import com.hakemy.marketsamer.databinding.FragmentMyOrderBinding
import com.hakemy.marketsamer.ui.myOrders.adapter.MyOrderAdapter
import com.hakemy.marketsamer.ui.myOrders.models.Data
import com.hakemy.marketsamer.ui.search.SearchActivity
import com.hakemy.marketsamer.utils.ResultState


class MyOrderFragment : BaseFragment<FragmentMyOrderBinding>(FragmentMyOrderBinding::inflate) {
    private lateinit var viewModel: MyOrderViewModel

    override fun onCreateBinding() {
        viewModel = ViewModelProvider(this)[MyOrderViewModel::class.java]

        binding.imageView6.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.imageView4.setOnClickListener {
            SearchActivity.startSearchActivity(requireContext())
        }


        viewModel.getMyOrder()

        viewModel.myOrderViewModel.observe(this, Observer {

            when(val result=it){
                is ResultState.Error -> {
                    hideProgress()

                }
                ResultState.Loading -> {
                    showProgress()

                }
                is ResultState.Success -> {
                    hideProgress()

                    kotlin.runCatching {
                        selectCurrentOrder(result.data.data)
                        binding.myOrder.setOnClickListener {
                            selectCurrentOrder(result.data.data)
                        }
                        binding.oldOrder.setOnClickListener {
                            selectOldOrder(result.data.data)
                        }
                    }

                }
            }

        })

    }

   private fun selectCurrentOrder(data: Data) {
        binding.oldOrder.setBackgroundDrawable(resources.getDrawable(R.drawable.white_order))
        binding.myOrder.setBackgroundDrawable(resources.getDrawable(R.drawable.blue_order))
        binding.myOrder.setTextColor(Color.parseColor("#FFFFFFFF"))
        binding.oldOrder.setTextColor(Color.parseColor("#1F2327"))

       kotlin.runCatching {
           val adapter=MyOrderAdapter()
           binding.recyclerView2.layoutManager=LinearLayoutManager(requireContext())
           binding.recyclerView2.adapter=adapter

           adapter.submit(data.wait)
       }

   }

    private  fun selectOldOrder(data: Data) {
        binding.oldOrder.setBackgroundDrawable(resources.getDrawable(R.drawable.blue_order))
        binding.myOrder.setBackgroundDrawable(resources.getDrawable(R.drawable.white_order))
        binding.oldOrder.setTextColor(Color.parseColor("#FFFFFFFF"))
        binding.myOrder.setTextColor(Color.parseColor("#1F2327"))
        kotlin.runCatching {
            val adapter=MyOrderAdapter()
            binding.recyclerView2.layoutManager=LinearLayoutManager(requireContext())
            binding.recyclerView2.adapter=adapter

            adapter.submit(data.complete)
        }


    }
}