package com.hakemy.marketsamer.ui.orderDetails.responseOfCancel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.hakemy.marketsamer.databinding.CancelItemBinding
import com.hakemy.marketsamer.ui.reViewOrder.model.PaymentMethod

class CancelReponseAdapter(private val context: Context) :
    RecyclerView.Adapter<CancelReponseAdapter.ViewHolder>() {

    private var _methodsList: MutableList<Data> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            CancelItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val method: Data = _methodsList[position]

        with(holder.itemBinding) {
            tvName.text = method.name
            cbButton.isChecked = method.selected
            //
            cbButton.setOnClickListener {
                _methodsList.forEach { item ->
                    item.selected = method.name == item.name
                }
                notifyDataSetChanged()
            }
        }
    }

    fun insertItem(methodsList: MutableList<Data>) {
        _methodsList = methodsList
        notifyItemRangeChanged(0, methodsList.size)
    }


    override fun getItemCount(): Int = _methodsList.size

    class ViewHolder(val itemBinding: CancelItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
}