package com.example.storagemanagerapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storagemanagerapp.data.StorageItem
import com.example.storagemanagerapp.databinding.ItemStorageBinding

class StorageAdapter(private val listener: StorageItemClickListener) :
    RecyclerView.Adapter<StorageAdapter.StorageViewHolder>() {

    private val items = mutableListOf<StorageItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StorageViewHolder(
        ItemStorageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
    override fun onBindViewHolder(holder: StorageViewHolder, position: Int) {
        val storageItem = items[position]

        holder.binding.tvName.text = storageItem.name
        holder.binding.tvDescription.text = storageItem.description
        holder.binding.tvAmount.text = storageItem.amount.toString()
        holder.binding.tvSize.text = storageItem.size.toString() + " m³"

        holder.binding.ibRemove.setOnClickListener {
            listener.onItemDeleted(storageItem)
        }
    }
    fun addItem(item: StorageItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun update(storageItems: List<StorageItem>) {
        items.clear()
        items.addAll(storageItems)
        notifyDataSetChanged()
    }

    fun removeItem(item: StorageItem) {
        items.remove(item)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    interface StorageItemClickListener {
        fun onItemChanged(item: StorageItem)
        fun onItemDeleted(item: StorageItem)
    }

    inner class StorageViewHolder(val binding: ItemStorageBinding) : RecyclerView.ViewHolder(binding.root)
}