package com.hakemy.marketsamer.ui.chooseAddresse

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hakemy.marketsamer.R
import com.hakemy.marketsamer.databinding.ItemSavedAddressBinding
import com.hakemy.marketsamer.ui.chooseAddresse.response.AddressItem

class SavedAddressesAdapter(private val context: Context, private val listener: RecycleListener) :
    RecyclerView.Adapter<SavedAddressesAdapter.ViewHolder>() {

    private var _addressList: MutableList<AddressItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemSavedAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val address: AddressItem = _addressList[position]

        with(holder.itemBinding) {
            tvName.text = address.date
            tvDetails.text = address.information
            tvNumber.text =
                "${context.getString(R.string.phone_number)} : +965${address.phone.toString()}"
            clItemAddress.isActivated = address.checked.toBoolean()

            // listeners
            clItemAddress.setOnClickListener {
                    listener.onAddressClicked(position, address)
            }
            ivDelete.setOnClickListener { listener.onDeleteClicked(position, address) }
            tvEdit.setOnClickListener { listener.onEditClicked(position, address) }
        }


    }

    fun insertItem(addressList: MutableList<AddressItem>) {
        _addressList = addressList
        notifyItemRangeInserted(0, _addressList.size)
    }

    fun deleteItem(position: Int) {
        _addressList.removeAt(position)
        notifyDataSetChanged()
    }

    fun getSelectedAddressId(): String {
        var selectedId = ""
        _addressList.forEach {
            if (it.checked == "true")
                selectedId = it.id.toString()
        }
        return selectedId
    }

    override fun getItemCount(): Int = _addressList.size

    class ViewHolder(val itemBinding: ItemSavedAddressBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    interface RecycleListener {
        fun onAddressClicked(position: Int, address: AddressItem)
        fun onDeleteClicked(position: Int, address: AddressItem)
        fun onEditClicked(position: Int, address: AddressItem)
    }
}