package com.codeartist.trivagochallenge.search.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codeartist.trivagochallenge.databinding.ItemSearchBinding
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharaterModel
import javax.inject.Inject


class SearchListAdapter @Inject constructor() :
    ListAdapter<CharaterModel, SearchListAdapter.ViewHolder>(ListAdapterCallBack()) {

    private lateinit var mItemClickListener: OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSearchBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), mItemClickListener)
    }


    fun addClickListener(itemClickListener: OnItemClickListener) {
        mItemClickListener = itemClickListener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var binding: ItemSearchBinding

        constructor(binding: ItemSearchBinding) : this(binding.root) {
            this.binding = binding

        }

        fun bind(item: CharaterModel, itemClickListener: OnItemClickListener) {
            binding.character = item
            binding.executePendingBindings()
            binding.fullItem.setOnClickListener() {
                itemClickListener.onItemClicked(it, item)
            }

        }
    }
}

class ListAdapterCallBack : DiffUtil.ItemCallback<CharaterModel>() {
    override fun areItemsTheSame(oldItem: CharaterModel, newItem: CharaterModel): Boolean {
        return oldItem.name.equals(newItem.name)
    }

    override fun areContentsTheSame(oldItem: CharaterModel, newItem: CharaterModel): Boolean {
        return oldItem.equals(newItem)
    }
}

interface OnItemClickListener {
    fun onItemClicked(view: View, item: CharaterModel)
}