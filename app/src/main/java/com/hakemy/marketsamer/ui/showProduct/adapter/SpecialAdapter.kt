package com.hakemy.marketsamer.ui.showProduct.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hakemy.marketsamer.databinding.SpecialItemBinding
import com.hakemy.marketsamer.ui.showProduct.entities.Special

class SpecialAdapter ():RecyclerView.Adapter<SpecialHolder>() {

    var lists=ArrayList<Special>()
    fun  setSpecialList(list :ArrayList<Special>){
        lists=list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialHolder {

       return SpecialHolder(
           SpecialItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
       return lists.size
    }

    override fun onBindViewHolder(holder: SpecialHolder, position: Int) {
        holder.bind(lists.get(position))
    }
}

class SpecialHolder (val binding:SpecialItemBinding):RecyclerView.ViewHolder(binding.root) {

    fun bind(get: Special) {

        binding.textView12.text=get.inputKey.toString()
        binding.textView13.text=get.inputValue.toString()

    }

}
