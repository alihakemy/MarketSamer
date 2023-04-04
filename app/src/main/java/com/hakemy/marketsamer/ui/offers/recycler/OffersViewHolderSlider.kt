package com.hakemy.marketsamer.ui.offers.recycler

import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.hakemy.marketsamer.databinding.OfferItemBinding
import com.hakemy.marketsamer.databinding.SliderItenBinding
import com.hakemy.marketsamer.ui.offers.entities.response.Banner

class OffersViewHolderSlider (val binding :SliderItenBinding):RecyclerView.ViewHolder(binding.root) {

    fun bind(banner: ArrayList<Banner>){

        val imageList = ArrayList<SlideModel>() // Create image list

        banner.forEach {
            imageList.add(SlideModel(it.imagePath, ScaleTypes.FIT ))

        }

        binding.imageSlider.setImageList(imageList)
    }


}
