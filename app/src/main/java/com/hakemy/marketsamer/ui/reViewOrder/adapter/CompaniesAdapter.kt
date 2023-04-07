package com.hakemy.marketsamer.ui.reViewOrder.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.databinding.ItemCompanyOrderBinding
import com.hakemy.marketsamer.ui.cart.models.CartItemProduct
import com.hakemy.marketsamer.ui.reViewOrder.model.Product

import com.myfatoorah.sdk.entity.executepayment.Supplier


class CompaniesAdapter(private val context: Context) :
    RecyclerView.Adapter<CompaniesAdapter.ViewHolder>() {

    private var _companiesList: MutableList<Product> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemCompanyOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product: Product = _companiesList[position]



        with(holder.itemBinding) {
            Glide.with(context).load(product.imagePath).into(ivImages)
            tvName.text = product.name
            qty.text=product.quantity.toString()

            kotlin.runCatching {
                qty2.text=product.feature_product.first().feature_name

            }

            tvPrice.text = "${product.price} ${context.getString(R.string.d_k)}"
            tvQuantity.text = product.quantity
            // actions

        }
    }

    fun insertItem(companiesList: MutableList<Product>) {
        _companiesList = companiesList
        notifyItemRangeChanged(0, _companiesList.size)
    }



    override fun getItemCount(): Int = _companiesList.size

    class ViewHolder(val itemBinding: ItemCompanyOrderBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
}