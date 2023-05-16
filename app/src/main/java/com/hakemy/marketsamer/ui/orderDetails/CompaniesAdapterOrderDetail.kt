package com.hakemy.marketsamer.ui.orderDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.databinding.OrderDetailsItemBinding

class CompaniesAdapterOrderDetail(private val context: Context,
private val cancelOrder:(product:com.hakemy.marketsamer.ui.orderDetails.Model.Product)->Unit) :
    RecyclerView.Adapter<CompaniesAdapterOrderDetail.ViewHolder>() {

    private var _companiesList: MutableList<com.hakemy.marketsamer.ui.orderDetails.Model.Product> = ArrayList()
    private var total:String =""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            OrderDetailsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product: com.hakemy.marketsamer.ui.orderDetails.Model.Product = _companiesList[position]



        with(holder.itemBinding) {
            Glide.with(context).load(product.imagePath).into(ivImages)
            tvName.text = product.name
            qty.text=product.quantity.toString()

            kotlin.runCatching {
                qty2.text=product.feature_product.first().feature_name

            }

            tvPrice.text = "${product.price} ${context.getString(R.string.d_k)}" +"لكل قطعه"
            tvQuantity.text = product.status_product_name


        }
    }

    fun insertItem(
        companiesList: MutableList<com.hakemy.marketsamer.ui.orderDetails.Model.Product>,
        total: String
    ) {

        _companiesList = companiesList
        notifyItemRangeChanged(0, _companiesList.size)
    }



    override fun getItemCount(): Int = _companiesList.size

    class ViewHolder(val itemBinding: OrderDetailsItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
}