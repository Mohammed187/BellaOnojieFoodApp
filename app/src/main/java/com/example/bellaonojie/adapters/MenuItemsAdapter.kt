package com.example.bellaonojie.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bellaonojie.models.Item
import com.example.bellaonojie.databinding.MenuItemBinding

class MenuItemsAdapter(val clickListener: MenuItemListener) :
        ListAdapter<Item, MenuItemsAdapter.MenuItemsViewHolder>(MenuCallback) {

    class MenuItemsViewHolder(private var binding: MenuItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(itemModel: Item, clickListener: MenuItemListener) {
            binding.item = itemModel
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemsViewHolder {
        val view = MenuItemBinding.inflate(LayoutInflater.from(parent.context))
        return MenuItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuItemsViewHolder, position: Int) {
        val itemModel = getItem(position)
        holder.bind(itemModel, clickListener)
    }

}

object MenuCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }
}

class MenuItemListener(val clickListener: (itemId: Item) -> Unit) {
    fun onClick(Item: Item) = clickListener(Item)
}
