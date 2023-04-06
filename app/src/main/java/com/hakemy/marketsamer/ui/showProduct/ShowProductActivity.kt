package com.hakemy.marketsamer.ui.showProduct

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.awesomedialog.AwesomeDialog
import com.example.awesomedialog.onPositive
import com.example.awesomedialog.title
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivityShowProductBinding
import com.hakemy.marketsamer.ui.cart.CartActivity
import com.hakemy.marketsamer.ui.cart.CartRequestAdd
import com.hakemy.marketsamer.ui.showProduct.adapter.BestProductRecyclerViewAdapter
import com.hakemy.marketsamer.ui.showProduct.entities.ProductsRelation
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.getMacAddr
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.launch


class ShowProductActivity : BaseActivity() {
    companion object {

        fun startShowProductActivity(productId: String, context: Context) {
            val intent = Intent(context, ShowProductActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("productId", productId)
            context.startActivity(intent)

        }

    }

    private lateinit var viewModel: ShowProductViewModel
    private lateinit var bestProductRecyclerViewAdapter: BestProductRecyclerViewAdapter
    private var companyId = -1

    lateinit var binding: ActivityShowProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =
            ViewModelProvider(this)[ShowProductViewModel::class.java]


        viewModel.getProductDetails(intent.getStringExtra("productId").toString())


        viewModel.productDetails.observe(this, Observer {

            when (val result = it) {
                is ResultState.Error -> {
                    hideProgress()
                }
                ResultState.Loading -> {
                    showProgress()
                }
                is ResultState.Success -> {
                    hideProgress()
                    val imageList2 = ArrayList<SlideModel>()
                    result.data.data.products.images.forEach {
                        imageList2.add(SlideModel(it.toString(), ScaleTypes.FIT))

                    }
                    imageList2.add(SlideModel(result.data.data.products.imagePath, ScaleTypes.FIT))

                    binding.slider2.setImageList(imageList2)
                    setBestProductRecyclerViewAdapterMainScreen(result.data.data.productsRelation)

                    binding.productName.text = result.data.data.products.name.toString()
                    binding.someId.text = result.data.data.products.minorder.toString() + " قطعه "

                    binding.currentPri.text =
                        result.data.data.products.prefitPrice.toString() + "د.ك"

                    binding.listPrice.text = result.data.data.products.mainprice.toString() + "د.ك"
                    binding.listPrice.paintFlags =
                        binding.listPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                    binding.details.text = result.data.data.products.content.toString()
                    binding.freedeliver.text =
                        "  قم بطلب  ${result.data.data.products.shippingCostFreeAfter}  او اكثر لتصبح الشحنه مؤهله للشحن المجانى "


                    binding.imageView13.setOnClickListener {
                        if (result.data.data.products.isFavourite.not()) {
                            result.data.data.products.isFavourite = true
                            binding.imageView13.setImageResource(com.hakemy.marketsamer.R.drawable.likes)

                        } else {
                            result.data.data.products.isFavourite = false

                            binding.imageView13.setImageResource(com.hakemy.marketsamer.R.drawable.group_7565)

                        }
                    }

                    binding.constraintLayout.setOnClickListener {

                        lifecycleScope.launch {
                            kotlin.runCatching {

                                val cartItem = CartRequestAdd(
                                    productId = result.data.data.products.id.toString(),
                                    quantity = binding.textView8.text.toString(),
                                    price = result.data.data.products.prefitPrice,
                                    companyId = result.data.data.products.tagerId.toString(),
                                    shippingCost = result.data.data.products.shippingCostFreeAfter,

                                    macAddress = getMacAddr(this@ShowProductActivity)
                                )
                                RetrofitService.servicesApi().addToCart(cartItem)

                            }

                        }

                        AwesomeDialog.build(this)
                            .title(getString(R.string.Congratulations))
                            .onPositive(getString(R.string.gotoCart)) {
                                CartActivity.startCartActivity(this)
                            }

                    }


                }
            }

        })


    }


    private fun setBestProductRecyclerViewAdapterMainScreen(Product: List<ProductsRelation>) {
        with(binding) {
            bestProductRecyclerViewAdapter = BestProductRecyclerViewAdapter()

            bestProductRecyclerViewAdapter.setItems(Product)
            rvFavProduct.adapter = bestProductRecyclerViewAdapter

        }
    }
}