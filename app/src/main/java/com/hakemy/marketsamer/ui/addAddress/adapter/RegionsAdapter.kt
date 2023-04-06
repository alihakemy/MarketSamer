package com.hakemy.marketsamer.ui.addAddress.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

import com.hakemy.marketsamer.databinding.ItemSpinnerBinding
import com.hakemy.marketsamer.ui.addAddress.models.RegionItem

class RegionsAdapter(context: Context) : BaseAdapter() {

    var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    var dataList: MutableList<RegionItem> = mutableListOf()

    fun setData(dataList: MutableList<RegionItem>?) {
        if (dataList != null) {
            this.dataList = dataList
        }
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): RegionItem {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return dataList[position].id!!.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = ItemSpinnerBinding.inflate(layoutInflater, parent, false)

        binding.itemSpinnerTvTitle.text = dataList[position].name
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = ItemSpinnerBinding.inflate(layoutInflater, parent, false)

        binding.itemSpinnerTvTitle.text = dataList[position].name
        return binding.root
    }
}