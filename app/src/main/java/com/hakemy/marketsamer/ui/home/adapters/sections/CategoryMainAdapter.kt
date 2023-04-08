package com.hakemy.marketsamer.ui.home.adapters.sections

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hakemy.marketsamer.databinding.CategoryItemMainScreenBinding
import com.hakemy.marketsamer.ui.home.entities.response.Category
import com.hakemy.marketsamer.ui.home.moreProducts.MoreProducts

class CategoryMainAdapter(private val context: Context) :
    RecyclerView.Adapter<CategoryMainAdapter.ViewHolder>() {

    private var _categoryList: List<Category> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            CategoryItemMainScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category: Category = _categoryList[position]


            Glide.with(context).load(category.imagePath).into(  holder.itemBinding.ivImage)
        holder.itemBinding.tvName.text = category.name

            // listener
        holder.itemBinding.lnItemCompany.setOnClickListener {
            MoreProducts.startMoreProducts(it.context,category.name.toString(),category.id.toString())

        }

    }

    fun insertItems(companiesList: List<Category>) {
        _categoryList = companiesList
        notifyItemRangeInserted(0, _categoryList.size)
    }

    override fun getItemCount(): Int = _categoryList.size

    class ViewHolder(val itemBinding: CategoryItemMainScreenBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

}