package com.hakemy.marketsamer.ui.cart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.databinding.ItemCartBinding
import com.hakemy.marketsamer.ui.cart.models.CartItemProduct

class CartAdapter(private val context: Context, private val listener: RecycleListener) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private var _cartItemProductList: MutableList<CartItemProduct> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product: CartItemProduct = _cartItemProductList[position]


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
            btnIncQuantity.setOnClickListener {

                listener.onAddClicked(position, product)

            }
            btnDecQuantity.setOnClickListener { listener.onMinusClicked(position, product) }
            ivTrash.setOnClickListener { listener.onDeleteClicked(position, product) }
        }
    }

    fun insertItems(cartItemProductList: ArrayList<CartItemProduct>) {
        _cartItemProductList.addAll(cartItemProductList)
        notifyItemRangeInserted(_cartItemProductList.size - 1, _cartItemProductList.size)
    }

    fun clear() {
        _cartItemProductList.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = _cartItemProductList.size

    class ViewHolder(val itemBinding: ItemCartBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    interface RecycleListener {
        fun onAddClicked(position: Int, product: CartItemProduct)
        fun onMinusClicked(position: Int, product: CartItemProduct)
        fun onDeleteClicked(position: Int, product: CartItemProduct)
    }
}