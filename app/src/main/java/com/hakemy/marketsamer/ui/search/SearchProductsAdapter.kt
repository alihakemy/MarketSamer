package com.hakemy.marketsamer.ui.search

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.databinding.ItemProductSearchBinding
import com.hakemy.marketsamer.ui.offers.entities.response.ProductsItem
import com.hakemy.marketsamer.ui.showProduct.ShowProductActivity
import com.hakemy.marketsamer.utils.SharePreferenceManager

class SearchProductsAdapter(private val context: Context, private val listener: RecycleListener) :
    RecyclerView.Adapter<SearchProductsAdapter.ViewHolder>() {

    private var _productsList: MutableList<ProductsItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemProductSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productsItem: ProductsItem = _productsList[position]

        val binding=holder.itemBinding
        Glide.with(binding.imageView5.context).load(productsItem.imagePath).into(binding.imageView7)
        binding.appCompatTextView.text=productsItem.name.toString()
        binding.someId.text=productsItem.minorder.toString() +" "+ binding.someId.context.resources.getString(
            R.string.cup)
        binding.price.text=productsItem.discount.toString() +binding.someId2.context.getString(R.string.KWD)
        binding.someId2.text=productsItem.mainprice.toString() +binding.someId2.context.getString(R.string.KWD)
        binding.textView4.text=productsItem.prefitPrice.toString() +"%"
        binding.someId2.paintFlags = binding.someId2.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        if(productsItem.isFavourite){
            binding.imageView5.setImageResource(com.hakemy.marketsamer.R.drawable.likes)

        }else{
            binding.imageView5.setImageResource(com.hakemy.marketsamer.R.drawable.group_7565)

        }
        binding.imageView5.setOnClickListener {
            if(SharePreferenceManager.getIsVerified()){
                productsItem.isFavourite = !productsItem.isFavourite
                notifyDataSetChanged()
            }
            listener.onProductIsFavouriteClicked(position,productsItem)
        }
        binding.root.setOnClickListener {
            ShowProductActivity.startShowProductActivity(productsItem.id.toString(),it.context)
        }
    }

    fun insertItems(productsList: MutableList<ProductsItem>) {
        _productsList = productsList
        notifyItemRangeInserted(0, productsList.size)
    }

    fun isFavourite(position: Int, isFavourite: Boolean) {
        if (_productsList.isNotEmpty()) {
            _productsList[position].isFavourite = isFavourite
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = _productsList.size

    class ViewHolder(val itemBinding: ItemProductSearchBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    interface RecycleListener {
        fun onProductClicked(position: Int, product: ProductsItem)
        fun onProductIsFavouriteClicked(position: Int, product: ProductsItem)
    }
}