package com.hakemy.marketsamer.ui.showProduct.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hakemy.marketsamer.databinding.ItemProductMainScreenBinding
import com.hakemy.marketsamer.ui.home.entities.response.ChooseU
import com.hakemy.marketsamer.ui.showProduct.ShowProductActivity
import com.hakemy.marketsamer.ui.showProduct.entities.ProductsRelation

class BestProductRecyclerViewAdapter : RecyclerView.Adapter<BestOffersViewHolder>() {

    val list = ArrayList<ProductsRelation>()
    fun setItems(offerItems: List<ProductsRelation>) {

        list.addAll(offerItems)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestOffersViewHolder {


            return BestOffersViewHolder(
                ItemProductMainScreenBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )


    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BestOffersViewHolder, position: Int) {

        val itemHolder : BestOffersViewHolder = holder as BestOffersViewHolder
        itemHolder.bind(list[position])

        itemHolder.binding.root.setOnClickListener {
            ShowProductActivity.startShowProductActivity(list[position].id.toString(),it.context)
        }

    }


}