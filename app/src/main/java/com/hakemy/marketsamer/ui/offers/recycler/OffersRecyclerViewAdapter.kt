package com.hakemy.marketsamer.ui.offers.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hakemy.marketsamer.databinding.OfferItemBinding
import com.hakemy.marketsamer.databinding.SliderItenBinding
import com.hakemy.marketsamer.ui.offers.entities.response.Banner
import com.hakemy.marketsamer.ui.offers.entities.response.DataX

class OffersRecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {

    val list = ArrayList<Any>()
    fun setItems(offerItems: ArrayList<Any>) {

        list.addAll(offerItems)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {

        return if(list[position] is DataX){
            1
        }else{
            0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(viewType==1){

            return OffersViewHolder(
                OfferItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }else{
            return OffersViewHolderSlider(
                SliderItenBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(list[position] is DataX){
            val itemHolder :OffersViewHolder = holder as OffersViewHolder
            itemHolder.bind(list[position] as DataX)


        }else{
            val bannerHolder :OffersViewHolderSlider = holder as OffersViewHolderSlider
            bannerHolder.bind(list[position] as ArrayList<Banner>)

        }

    }

    fun setItemsBanner(banner: ArrayList<Any>) {
        list.add(banner)
        notifyDataSetChanged()
    }
}