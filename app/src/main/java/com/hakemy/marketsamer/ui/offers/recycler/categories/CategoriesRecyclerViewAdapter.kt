package com.hakemy.marketsamer.ui.offers.recycler.categories

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.databinding.CategoryItemBinding
import com.hakemy.marketsamer.ui.offers.entities.response.Category

class CategoriesRecyclerViewAdapter(val clickCategories :(category: Category)->Unit) : RecyclerView.Adapter<CategoryViewHolder>() {
    var category: ArrayList<Category> = ArrayList()
    fun submitList(category: List<Category>) {

        this.category.addAll(category)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return category.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        holder.bind(category[position])
        holder.binding.textView6.setOnClickListener {
           category.forEach {
               it.isSelected=false
           }
            category[position].isSelected=true
            notifyDataSetChanged()
            clickCategories(  category[position])


        }

    }


}

class CategoryViewHolder(val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(category: Category) {
        binding.textView6.text=category.name.toString()
        if(!category.isSelected){
            binding.textView6.background= ContextCompat.getDrawable(binding.textView6.context,
                R.drawable.rectangle_strock)
            binding.textView6.setTextColor(Color.parseColor("#939393"))
        }else
        {
            binding.textView6.background= ContextCompat.getDrawable(binding.textView6.context,
                R.drawable.rectangle_colord)
            binding.textView6.setTextColor(Color.parseColor("#FFFFFF"))

        }


    }

}
