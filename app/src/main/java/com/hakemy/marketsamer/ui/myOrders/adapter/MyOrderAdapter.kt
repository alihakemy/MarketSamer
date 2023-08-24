package com.hakemy.marketsamer.ui.myOrders.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.databinding.ItemCartBinding
import com.hakemy.marketsamer.databinding.MyOrderItemBinding
import com.hakemy.marketsamer.ui.cart.adapter.CartAdapter
import com.hakemy.marketsamer.ui.myOrders.models.Wait
import com.hakemy.marketsamer.ui.orderDetails.OrderDetailsActivity


class MyOrderAdapter : RecyclerView.Adapter<MyOrderAdapterViewHolder>() {

    var list= emptyList<Wait>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOrderAdapterViewHolder {

        val itemBinding =
            MyOrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyOrderAdapterViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: MyOrderAdapterViewHolder, position: Int) {

        holder.bind(list.get(position))
    }

    fun submit(complete: List<Wait>) {
        list=complete
        notifyDataSetChanged()

    }

}
class MyOrderAdapterViewHolder (val binding : MyOrderItemBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Wait) {

        binding.tvName.text="#"+item.order_number.toString()
        binding.qty.text=item.items.toString()
        binding.qty2.text=item.date_order.toString()
        runCatching {
            Glide.with(binding.appCompatTextView4.context)
                .load(item.images.first()).into(binding.ivImages)
        }

        binding.root.setOnClickListener {
            OrderDetailsActivity.startOrderDetailsActivity(it.context,item.id.toString())

        }

    }


}
