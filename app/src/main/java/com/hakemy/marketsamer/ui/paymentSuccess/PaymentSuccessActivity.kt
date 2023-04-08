package com.hakemy.marketsamer.ui.paymentSuccess

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivityPaymentSuccessBinding
import com.hakemy.marketsamer.ui.main.MainActivity
import com.hakemy.marketsamer.ui.orderDetails.OrderDetailsActivity

class PaymentSuccessActivity : BaseActivity() {
    companion object{
        fun startPaymentSuccessActivity(context: Context,orderNo:String,
       totalPrice:String,paymentMethod:String,id:String){
            val intent=Intent(context,PaymentSuccessActivity::class.java)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("orderNo",orderNo)
            intent.putExtra("totalPrice",totalPrice)
            intent.putExtra("paymentMethod",paymentMethod)
            intent.putExtra("id",id)

            context.startActivity(intent)

        }
    }
    lateinit var binding:ActivityPaymentSuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPaymentSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvOrderNumber.text=intent.getStringExtra("orderNo")
        binding.tvPaymentMethod.text=intent.getStringExtra("paymentMethod")
        binding.tvTotal.text=intent.getStringExtra("totalPrice")
        binding.btnHome.setOnClickListener {
            MainActivity.startMainActivity(this)
        }

        binding.btnDetails.setOnClickListener {
            OrderDetailsActivity.startOrderDetailsActivity(this,intent.getStringExtra("id").toString())
        }

    }
}