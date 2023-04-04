package com.hakemy.marketsamer.ui.home.adapters.favOffers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hakemy.marketsamer.databinding.ItemProductMainScreenBinding
import com.hakemy.marketsamer.databinding.OfferItemBinding
import com.hakemy.marketsamer.ui.home.entities.response.Product
import com.hakemy.marketsamer.ui.showProduct.ShowProductActivity

class ProductRecyclerViewAdapterMainScreen : RecyclerView.Adapter<OffersViewHolderMainScreen>() {

    val list = ArrayList<Product>()
    fun setItems(offerItems: List<Product>) {

        list.addAll(offerItems)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersViewHolderMainScreen {


            return OffersViewHolderMainScreen(
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

    override fun onBindViewHolder(holder: OffersViewHolderMainScreen, position: Int) {

        val itemHolder : OffersViewHolderMainScreen = holder as OffersViewHolderMainScreen
        itemHolder.bind(list[position])
        itemHolder.binding.root.setOnClickListener {
            ShowProductActivity.startShowProductActivity(list[position].id.toString(),it.context)
        }

    }


}