package com.hakemy.marketsamer.ui.offers.recycler

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.databinding.OfferItemBinding
import com.hakemy.marketsamer.ui.offers.entities.response.ProductsItem
import com.hakemy.marketsamer.ui.register.RegisterActivity
import com.hakemy.marketsamer.ui.showProduct.ShowProductActivity
import com.hakemy.marketsamer.utils.SharePreferenceManager
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OffersViewHolder (val binding :OfferItemBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind(productsItem: ProductsItem) {

        Glide.with(binding.imageView5.context).load(productsItem.imagePath).into(binding.imageView7)
        binding.appCompatTextView.text=productsItem.name.toString()
        binding.someId.text=productsItem.qty_name.toString() +" "+ binding.someId.context.resources.getString(R.string.cup)
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
            if(SharePreferenceManager.getIsVerified().not()){
                RegisterActivity.startRegisterActivity(it.context)

                return@setOnClickListener
            }
            if(productsItem.isFavourite){
                productsItem.isFavourite=false
                binding.imageView5.setImageResource(com.hakemy.marketsamer.R.drawable.group_7565)

            }else{
                productsItem.isFavourite=true
                binding.imageView5.setImageResource(com.hakemy.marketsamer.R.drawable.likes)

            }
            CoroutineScope(Dispatchers.IO).launch {
                kotlin.runCatching { RetrofitService.servicesApi().isFavorite(productsItem.id.toString()) }
            }
        }
        binding.root.setOnClickListener {
            ShowProductActivity.startShowProductActivity(productsItem.id.toString(),it.context)
        }

    }


}
