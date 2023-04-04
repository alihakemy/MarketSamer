package com.hakemy.marketsamer.ui.home.adapters.bestOffers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hakemy.marketsamer.databinding.ItemProductMainScreenBinding
import com.hakemy.marketsamer.databinding.OfferItemBinding
import com.hakemy.marketsamer.ui.home.entities.response.ChooseU
import com.hakemy.marketsamer.ui.home.entities.response.Product
import com.hakemy.marketsamer.ui.showProduct.ShowProductActivity

class BestProductRecyclerViewAdapterMainScreen : RecyclerView.Adapter<BestOffersViewHolderMainScreen>() {

    val list = ArrayList<ChooseU>()
    fun setItems(offerItems: List<ChooseU>) {

        list.addAll(offerItems)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestOffersViewHolderMainScreen {


            return BestOffersViewHolderMainScreen(
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

    override fun onBindViewHolder(holder: BestOffersViewHolderMainScreen, position: Int) {

        val itemHolder : BestOffersViewHolderMainScreen = holder as BestOffersViewHolderMainScreen
        itemHolder.bind(list[position])

        itemHolder.binding.root.setOnClickListener {
            ShowProductActivity.startShowProductActivity(list[position].id.toString(),it.context)
        }

    }


}