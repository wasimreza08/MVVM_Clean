package com.codeartist.starwar.search.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codeartist.starwar.databinding.ItemSearchBinding
import com.codeartist.starwar.search.presentation.uimodel.CharacterModel
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class SearchListAdapter @Inject constructor() :
    ListAdapter<CharacterModel, SearchListAdapter.ViewHolder>(ListAdapterCallBack()) {

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

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var binding: ItemSearchBinding

        constructor(binding: ItemSearchBinding) : this(binding.root) {
            this.binding = binding

        }

        fun bind(item: CharacterModel, itemClickListener: OnItemClickListener) {
            binding.character = item
            binding.executePendingBindings()
            binding.fullItem.setOnClickListener() {
                itemClickListener.onItemClicked(it, item)
            }

        }
    }
}

class ListAdapterCallBack : DiffUtil.ItemCallback<CharacterModel>() {
    override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem.name.equals(newItem.name)
    }

    override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem.equals(newItem)
    }
}

interface OnItemClickListener {
    fun onItemClicked(view: View, item: CharacterModel)
}