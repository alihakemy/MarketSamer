package com.hakemy.marketsamer.ui.reViewOrder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.ui.reViewOrder.adapter.PaymentMethodsAdapter
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivityReViewOrderBinding
import com.hakemy.marketsamer.ui.addAddress.fetchText
import com.hakemy.marketsamer.ui.paymentSuccess.PaymentSuccessActivity
import com.hakemy.marketsamer.ui.reViewOrder.adapter.CompaniesAdapter
import com.hakemy.marketsamer.ui.register.RegisterActivity
import com.hakemy.marketsamer.ui.register.serviceModel.response.User
import com.hakemy.marketsamer.utils.ResultState
import com.hakemy.marketsamer.utils.SharePreferenceManager
import com.hakemy.marketsamer.utils.ToastType
import com.hakemy.marketsamer.utils.showToast
import com.myfatoorah.sdk.entity.executepayment.MFExecutePaymentRequest
import com.myfatoorah.sdk.entity.executepayment.Supplier
import com.myfatoorah.sdk.entity.paymentstatus.MFGetPaymentStatusResponse
import com.myfatoorah.sdk.enums.MFAPILanguage
import com.myfatoorah.sdk.enums.MFCurrencyISO
import com.myfatoorah.sdk.views.MFResult
import com.myfatoorah.sdk.views.MFSDK
import es.dmoral.toasty.Toasty

class ReViewOrderActivity : BaseActivity() {

    companion object {

        fun startReViewOrderActivity(context: Context, orderId: String?) {
            if (SharePreferenceManager.getIsVerified().not()) {
                RegisterActivity.startRegisterActivity(context)

                return
            }
            val intent = Intent(context, ReViewOrderActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("orderId", orderId)
            context.startActivity(intent)

        }

    }

    lateinit var binding: ActivityReViewOrderBinding
    private lateinit var user: User

    private lateinit var paymentMethodsAdapter: PaymentMethodsAdapter

    private var totalInVoiceValue = 0.0

    private var totalPrice = ""
    private var deliveryPrice = ""
    val viewModel by viewModels<ReviewOrderViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReViewOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView6.setOnClickListener {
            onBackPressed()
        }
        paymentMethodsAdapter = PaymentMethodsAdapter(this)

        user = SharePreferenceManager.getUser()

        Log.e("orderId", intent.getStringExtra("orderId").toString())

        with(binding) {
            tvApplyCoupon.setOnClickListener {
                viewModel.coupon(
                    intent.getStringExtra("orderId").toString(),
                    etCoupon.fetchText()
                )


            }
            btnSave.setOnClickListener { submitOrder() }
        }
        viewModel._couponResponse.observe(this, Observer {
            when(val result =it){
                is ResultState.Error -> {
                    hideProgress()
                    showToast(result.exceptionMessage.toString())

                }
                ResultState.Loading -> {
                    showProgress()

                }
                is ResultState.Success -> {
                    hideProgress()
                    viewModel.confirmCart(intent.getStringExtra("orderId").toString())
                    showToast(result.data.message.toString(), ToastType.SUCCESS)

                }
            }
        })
        viewModel.confirmCart(intent.getStringExtra("orderId").toString())

        viewModel.submitOrderResponse.observe(this, Observer {

            when (val result = it) {
                is ResultState.Error -> {
                    hideProgress()
                }
                ResultState.Loading -> {
                    showProgress()
                }
                is ResultState.Success -> {

                    PaymentSuccessActivity.startPaymentSuccessActivity(
                        this,
                        orderNo = result.data?.data?.order_data?.order_number.toString(),
                        totalPrice = result.data?.data?.order_data?.total_price.toString(),
                        paymentMethod = result.data?.data?.order_data?.payment_method.toString(),
                        id=intent.getStringExtra("orderId").toString()
                    )
                }
            }

        })

        viewModel.confirmResponse.observe(this, Observer { result ->

            when (val it = result) {
                is ResultState.Error -> {
                    hideProgress()
                }
                ResultState.Loading -> {
                    showProgress()

                }
                is ResultState.Success -> {
                    hideProgress()
                    // products

                    binding.tvName.text = it.data.data?.data?.content.toString()

                    binding.tvDetails.text = it.data.data?.data?.phone.toString()

                    binding.tvNumber.text = it.data.data?.data?.content.toString() +
                            it.data.data?.data?.phone.toString()


                    val companiesAdapter = CompaniesAdapter(this)
                    it.data?.data?.products?.let { it1 -> companiesAdapter.insertItem(it1) }
                    binding.rvCompaniesOrders.adapter = companiesAdapter


                    // payment methods
                    it.data?.data?.paymentMethod?.let { it1 -> paymentMethodsAdapter.insertItem(it1) }
                    binding.rvPaymentMethods.adapter = paymentMethodsAdapter




                    kotlin.runCatching {
                        val t =
                            it.data?.data?.TotalPrice
                        // prices
                        binding.tvTotal.text = "${t} ${getString(R.string.d_k)}"

                    }
                    binding.tvDelivery.text =
                        "${it.data?.data?.totaShippingCost} ${getString(R.string.d_k)}"
                    binding.tvOverall.text = "${it.data?.data?.total} ${getString(R.string.d_k)}"
                    //
                    totalPrice = it.data?.data?.total.toString()
                    deliveryPrice = it.data?.data?.totaShippingCost.toString()
                    kotlin.runCatching {
                        totalInVoiceValue =
                            it.data?.data?.total?.toDouble()!!

                    }

                    if(it.data?.data?.dicountCode==null){
                        binding.lnCouponPrice.visibility= View.GONE
                    }else{
                        binding.tvCouponPrice.text = "${it.data?.data?.dicountCode} % "

                    }

                }
            }
        })
    }

    private fun submitOrder() {
        if(paymentMethodsAdapter.getSelectedMethod().equals("")){
            Toasty.error(this,getString(R.string.select_payment_method)).show()

            return
        }
        when (paymentMethodsAdapter.getSelectedMethod()) {
            "1" -> viewModel.submitOrder(
                orderId = intent.getStringExtra("orderId").toString(),
                paymentMethod = paymentMethodsAdapter.getSelectedMethod(),
                totalPrice = totalPrice.filterNot { it == ',' },
                paymentStatus = paymentMethodsAdapter.getSelectedMethod(),
                totalShippingCost = deliveryPrice.filterNot { it == ',' })

            "2" -> executePayment()

            else -> {
                viewModel.submitOrder(
                    orderId = intent.getStringExtra("orderId").toString(),
                    paymentMethod = paymentMethodsAdapter.getSelectedMethod(),
                    totalPrice = totalPrice.filterNot { it == ',' },
                    paymentStatus = paymentMethodsAdapter.getSelectedMethod(),
                    totalShippingCost = deliveryPrice.filterNot { it == ',' })
            }
        }
    }

    private fun executePayment() {
        showProgress()
        val Supplier=ArrayList<Supplier>()
        Supplier.add(Supplier(27,null,totalPrice.toDoubleOrNull()))
        val request = MFExecutePaymentRequest(
            1,
            user.name,
            MFCurrencyISO.KUWAIT_KWD,
            "965",
            user.phone?.replace("+965", ""),
            "samar@gmail.com",
            totalInVoiceValue,
            null,
            null,
            "en",
            null,
            null,
            null,
            null,
            null,
            null,
            Supplier,
        )
        MFSDK.executePayment(
            this,
            request,
            MFAPILanguage.EN,
        ) { _: String, result: MFResult<MFGetPaymentStatusResponse> ->
            when (result) {
                is MFResult.Success -> {
                    hideProgress()
                    //
                    viewModel.submitOrder(
                        orderId = intent.getStringExtra("orderId").toString(),
                        paymentMethod = paymentMethodsAdapter.getSelectedMethod(),
                        totalPrice = totalPrice.filterNot { it == ',' },
                        paymentStatus = paymentMethodsAdapter.getSelectedMethod(),
                        totalShippingCost = deliveryPrice.filterNot { it == ',' }
                    )
                }
                is MFResult.Fail -> {
                    hideProgress()
                    showToast(result.error.message)
                }
                else -> {}
            }
        }
    }
}