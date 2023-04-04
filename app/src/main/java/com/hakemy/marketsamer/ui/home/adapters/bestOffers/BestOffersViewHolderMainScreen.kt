package com.hakemy.marketsamer.ui.home.adapters.bestOffers

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.databinding.ItemProductMainScreenBinding
import com.hakemy.marketsamer.databinding.OfferItemBinding
import com.hakemy.marketsamer.ui.home.entities.response.ChooseU
import com.hakemy.marketsamer.ui.home.entities.response.Product


class BestOffersViewHolderMainScreen (val binding :ItemProductMainScreenBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind(dataX: ChooseU) {

        Glide.with(binding.imageView5.context).load(dataX.imagePath).into(binding.imageView7)
        binding.appCompatTextView.text=dataX.name.toString()
        binding.someId.text=dataX.minorder.toString() +" "+ binding.someId.context.resources.getString(R.string.cup)
        binding.price.text=dataX.discount.toString() +binding.someId2.context.getString(R.string.KWD)
        binding.someId2.text=dataX.mainprice.toString() +binding.someId2.context.getString(R.string.KWD)
        binding.textView4.text=dataX.prefitPrice.toString() +"%"
        binding.someId2.paintFlags = binding.someId2.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        if(dataX.isFavourite){
            binding.imageView5.setImageResource(com.hakemy.marketsamer.R.drawable.likes)

        }else{
            binding.imageView5.setImageResource(com.hakemy.marketsamer.R.drawable.group_7565)

        }
        binding.imageView5.setOnClickListener {
            binding.imageView5.setImageResource(com.hakemy.marketsamer.R.drawable.likes)
        }

    }


}
