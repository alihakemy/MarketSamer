package com.hakemy.marketsamer.ui.profile.notification

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hakemy.marketsamer.databinding.ItemNotificationBinding
import com.hakemy.marketsamer.ui.profile.notification.model.NotificationItem

class NotificationAdapter(private val context: Context) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    private var _notificationsList: MutableList<NotificationItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification: NotificationItem = _notificationsList[position]

        with(holder.itemBinding) {
            Glide.with(context).load(notification.image).into(ivImage)
            tvMessage.text = notification.title
            tvStatus.text = notification.message
        }
    }

    fun insertItem(notificationsList: MutableList<NotificationItem>) {
        _notificationsList = notificationsList
        notifyItemRangeInserted(0, notificationsList.size)
    }

    override fun getItemCount(): Int = _notificationsList.size

    class ViewHolder(val itemBinding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
}