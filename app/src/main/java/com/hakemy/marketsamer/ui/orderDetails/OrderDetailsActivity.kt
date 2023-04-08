package com.hakemy.marketsamer.ui.orderDetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivityOrderDetailsBinding
import com.hakemy.marketsamer.ui.main.MainActivity
import com.hakemy.marketsamer.ui.orderDetails.responseOfCancel.CancelReponseAdapter
import com.hakemy.marketsamer.ui.reViewOrder.adapter.CompaniesAdapter
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.launch

class OrderDetailsActivity : BaseActivity() {

    companion object{
        fun startOrderDetailsActivity(context: Context,orderId:String){
            val intent=Intent(context,OrderDetailsActivity::class.java)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("id",orderId.toString())
            context.startActivity(intent)
        }
    }

    lateinit var binding:ActivityOrderDetailsBinding
    lateinit var viewModel: OrderDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityOrderDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel=ViewModelProvider(this)[OrderDetailViewModel::class.java]
        var orderNo =intent.getStringExtra("id").toString()


        viewModel.getOrderDetails(orderNo)

        binding.imageView6.setOnClickListener {
            onBackPressed()
        }

        lifecycleScope.launch {
            try {
                val result=RetrofitService.servicesApi().cancelOrderReasons(orderNo)

                val adapter =CancelReponseAdapter(this@OrderDetailsActivity)
                adapter.insertItem(result.data)
                binding.bottom.recyclerView3.layoutManager=LinearLayoutManager(this@OrderDetailsActivity)

                binding.bottom.recyclerView3.adapter=adapter
                binding.bottom.confirm.setOnClickListener {
                    lifecycleScope.launch {
                        kotlin.runCatching {
                            val map =HashMap<String,String>()
                            map.put("product_order_id",orderNo)

                            map.put("reason","not needed")
                            val result=RetrofitService.servicesApi().cancelOrderReasons(map)


                        }
                        MainActivity.startMainActivity(this@OrderDetailsActivity)
                    }

                }
            }catch (e:Exception){

            }
        }

        viewModel.orderDetailsResponse.observe(this, Observer {

            when(val result=it){
                is ResultState.Error -> {
                    hideProgress()
                }
                ResultState.Loading -> {
                    showProgress()
                }
                is ResultState.Success -> {
                    hideProgress()
                    binding.orderNo.text=result.data.data.info.order_number
                    binding.count.text=result.data.data.products.size.toString()
                    binding.tvDate.text=result.data.data.info.date_order.toString()
                    binding.tvTotalPrice.text = "${result.data.data.info.total_price} ${getString(R.string.d_k)}"

                    binding.tvName.text = result.data.data?.data?.content.toString()

                    binding.tvDetails.text = result.data.data?.data?.phone.toString()

                    binding.tvNumber.text = result.data.data?.data?.content.toString() +
                            result.data.data?.data?.phone.toString()
                    val companiesAdapter = CompaniesAdapterOrderDetail(this){
                        binding.bottom.root.visibility=View.VISIBLE
                    }
                    binding.bottom.imageView19.setOnClickListener {
                        binding.bottom.root.visibility=View.GONE

                    }
                    result.data?.data?.products?.let { it1 -> companiesAdapter.insertItem(it1) }
                    binding.rvCompaniesOrders.adapter = companiesAdapter


                    kotlin.runCatching {
                        binding.paymentName.text=result.data.data.paymentMethod.first().name.toString()

                        Glide.with(this@OrderDetailsActivity).load(
                            result.data.data.paymentMethod.first().image_path
                        ).into(binding.ivIcon)
                    }
                    binding.tvTotal.text = "${result.data?.data?.total} ${getString(R.string.d_k)}"
                    binding.tvDelivery.text =
                        "${result.data?.data?.totaShippingCost} ${getString(R.string.d_k)}"
                    binding.tvOverall.text = "${result.data?.data?.total} ${getString(R.string.d_k)}"




                }
            }

        })

    }


}