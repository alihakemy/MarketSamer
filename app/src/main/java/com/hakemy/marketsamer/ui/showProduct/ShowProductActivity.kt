package com.hakemy.marketsamer.ui.showProduct

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashraf007.expandableselectionview.adapter.BasicStringAdapter
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
import com.hakemy.marketsamer.ui.showProduct.adapter.SpecialAdapter
import com.hakemy.marketsamer.ui.showProduct.entities.ProductDetailsResponse
import com.hakemy.marketsamer.ui.showProduct.entities.ProductsRelation
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.getMacAddr
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

        binding.imageView10.setOnClickListener {
            onBackPressed()
        }

        viewModel.getProductDetails(intent.getStringExtra("productId").toString())
//        viewModel.getProductDetails("44")


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
                    renederSpecial(result)
                    selection(result)

                    binding.imageView17.setOnClickListener {
                        kotlin.runCatching {
                            val results =binding.textView8.text.toString().toInt().plus(1)
                            binding.textView8.text=results.toString()

                        }
                    }
                    binding.imageView14.setOnClickListener {
                        if( binding.textView8.text.toString().toInt()>1){
                            kotlin.runCatching {
                                val results = binding.textView8.text.toString().toInt().minus(1)
                                binding.textView8.text=results.toString()
                            }
                        }

                    }
                    val imageList2 = ArrayList<SlideModel>()
                    result.data.data.products.images?.forEach {
                        imageList2.add(SlideModel(it.toString(), ScaleTypes.FIT))

                    }
                    imageList2.add(SlideModel(result.data.data.products.imagePath, ScaleTypes.FIT))

                    binding.slider2.setImageList(imageList2)
                    setBestProductRecyclerViewAdapterMainScreen(result.data.data.productsRelation)

                    binding.productName.text = result.data.data.products.name.toString()
                    binding.someId.text = result.data.data.products.qty_name.toString()


                    binding.currentPri.text =
                        result.data.data.products.discount.toString() + getString(R.string.KWD)

                    binding.listPrice.text = result.data.data.products.mainprice.toString()+ getString(R.string.KWD)
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
                        CoroutineScope(Dispatchers.IO).launch {
                            kotlin.runCatching { RetrofitService.servicesApi().isFavorite(result.data.data.products.id.toString()) }
                        }
                    }
                    binding.imageView16.setOnClickListener{
                        SpecialActivity.startSpecialActivity(this,intent.getStringExtra("productId").toString())
                    }

                    binding.constraintLayout.setOnClickListener {

                        lifecycleScope.launch {
                            kotlin.runCatching {

                                val cartItem = result.data.data.products.prefitPrice?.let { it1 ->
                                    result.data.data.products.shippingCostFreeAfter?.let { it2 ->
                                        CartRequestAdd(
                                            productId = result.data.data.products.id.toString(),
                                            quantity = binding.textView8.text.toString(),
                                            price = it1,
                                            companyId = result.data.data.products.tagerId.toString(),
                                            shippingCost = it2,

                                            macAddress = getMacAddr(this@ShowProductActivity)
                                        )
                                    }
                                }
                                if (cartItem != null) {
                                    RetrofitService.servicesApi().addToCart(cartItem)
                                }

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

        binding.imageView15.setOnClickListener {
            shareApp(this)
        }

    }

    fun shareApp(context: Context) {
        val appPackageName: String = context.getPackageName()
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Check out the App at: https://play.google.com/store/apps/details?id=$appPackageName"
        )
        sendIntent.type = "text/plain"
        context.startActivity(sendIntent)
    }


    private fun selection(result: ResultState.Success<ProductDetailsResponse>){
        kotlin.runCatching {
            val sizes =ArrayList<String>()


            result.data.data.products.options?.first()?.options?.forEach {
                sizes.add(it.title)
            }
// Provide a list of strings and an optional hint
            val expandableAdapter = BasicStringAdapter(sizes, getString(R.string.selectSize))
            binding.singleSelectionView.setAdapter(expandableAdapter)
            binding.singleSelectionView.selectionListener = { index: Int? ->
                kotlin.runCatching {
                    binding.currentPri.text= index?.let {
                        result.data.data.products.options?.first()?.options?.get(
                            it
                        )?.price.toString()
                    }
                }

            }

        }

    }
    private fun renederSpecial(result: ResultState.Success<ProductDetailsResponse>) {
        val adapt=SpecialAdapter()

        binding.recyclerView4.layoutManager=LinearLayoutManager(this)
        binding.recyclerView4.adapter=adapt
        adapt.setSpecialList(result.data.data.products.special)

    }
    private fun setBestProductRecyclerViewAdapterMainScreen(Product: List<ProductsRelation>) {
        with(binding) {
            bestProductRecyclerViewAdapter = BestProductRecyclerViewAdapter()

            bestProductRecyclerViewAdapter.setItems(Product)
            rvFavProduct.adapter = bestProductRecyclerViewAdapter

        }
    }
}