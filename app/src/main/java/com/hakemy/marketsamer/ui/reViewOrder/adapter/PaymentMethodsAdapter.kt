package com.hakemy.marketsamer.ui.reViewOrder.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.hakemy.marketsamer.databinding.ItemPaymentMethodBinding
import com.hakemy.marketsamer.ui.reViewOrder.model.PaymentMethod

class PaymentMethodsAdapter(private val context: Context) :
    RecyclerView.Adapter<PaymentMethodsAdapter.ViewHolder>() {

    private var _methodsList: MutableList<PaymentMethod> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemPaymentMethodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val method: PaymentMethod = _methodsList[position]

        with(holder.itemBinding) {
            Glide.with(context).load(method.image_path).into(ivIcon)
            tvName.text = method.name
            cbButton.isChecked = method.isChecked
            //
            cbButton.setOnClickListener {
                _methodsList.forEach { item ->
                    item.isChecked = method.id == item.id
                }
                notifyDataSetChanged()
            }
        }
    }

    fun insertItem(methodsList: MutableList<PaymentMethod>) {
        _methodsList = methodsList
        notifyItemRangeChanged(0, methodsList.size)
    }

    fun getSelectedMethod(): String {
        var selected = ""
        _methodsList.forEach {
            if (it.isChecked)
                selected = it.id.toString()
        }
        return selected
    }

    override fun getItemCount(): Int = _methodsList.size

    class ViewHolder(val itemBinding: ItemPaymentMethodBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
}