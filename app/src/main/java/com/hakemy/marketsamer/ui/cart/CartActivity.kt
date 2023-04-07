package com.hakemy.marketsamer.ui.cart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.awesomedialog.AwesomeDialog
import com.example.awesomedialog.onPositive
import com.example.awesomedialog.title
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivityCartBinding
import com.hakemy.marketsamer.ui.cart.adapter.CartAdapter
import com.hakemy.marketsamer.ui.cart.models.CartItemProduct
import com.hakemy.marketsamer.ui.chooseAddresse.ChooseAddressActivity
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.getMacAddr
import kotlinx.coroutines.launch

class CartActivity : BaseActivity(), CartAdapter.RecycleListener {
    companion object {

        fun startCartActivity(context: Context) {
            val intent = Intent(context, CartActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

        }

    }

    lateinit var binding: ActivityCartBinding
    lateinit var viewModel: CartViewModel
    private lateinit var cartAdapter: CartAdapter
    private var orderId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CartViewModel::class.java]




        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.getCart(getMacAddr(this@CartActivity))

            }

        }
        cartAdapter = CartAdapter(this, this)

        viewModel.cartResponse.observe(this, Observer {
            cartAdapter.clear()
            binding.tvTotal.text = ""
            when (val result = it) {
                is ResultState.Error -> {
                    hideProgress()
                }
                ResultState.Loading -> {
                    showProgress()
                }
                is ResultState.Success -> {
                    hideProgress()


                    kotlin.runCatching {
                        cartAdapter.insertItems(result.data.data.products)
                        binding.rvCart.adapter = cartAdapter
                        orderId = result.data.data?.order_info?.id.toString()

                        binding.tvTotal.text =
                            "${result.data.data?.total} ${getString(R.string.d_k)}"

                        binding.btnConfirm.setOnClickListener {
                            if (cartAdapter.itemCount > 0) {
                                ChooseAddressActivity.startChooseAddressActivity(this,result.data.data?.total.toString(),orderId)
                            }
                        }
                    }.onFailure {
                        AwesomeDialog.build(this)
                            .title(getString(R.string.emptyCart))
                            .onPositive(getString(R.string.gotoShopping)) {
                                onBackPressed()
                            }

                    }

                }
            }

        })


    }

    override fun onAddClicked(position: Int, product: CartItemProduct) {
        viewModel.editCart(product.id.toString(), "plus", this)

    }

    override fun onMinusClicked(position: Int, product: CartItemProduct) {
        viewModel.editCart(product.id.toString(), "minus", this)

    }

    override fun onDeleteClicked(position: Int, product: CartItemProduct) {
        viewModel.deleteCart(orderId, product.id.toString(), this)

    }
}