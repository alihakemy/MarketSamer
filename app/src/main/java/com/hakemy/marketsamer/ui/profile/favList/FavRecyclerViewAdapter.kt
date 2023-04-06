package com.hakemy.marketsamer.ui.profile.favList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hakemy.marketsamer.databinding.OfferItemBinding
import com.hakemy.marketsamer.databinding.SliderItenBinding
import com.hakemy.marketsamer.ui.offers.entities.response.Banner
import com.hakemy.marketsamer.ui.offers.entities.response.ProductsItem
import com.hakemy.marketsamer.ui.offers.recycler.OffersViewHolder

class FavRecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {

    val list = ArrayList<ProductsItem>()
    fun setItems(offerItems: ArrayList<ProductsItem>) {

        list.addAll(offerItems)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        return OffersViewHolder(
            OfferItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val itemHolder: OffersViewHolder = holder as OffersViewHolder
        itemHolder.bind(list[position] as ProductsItem)


    }

}