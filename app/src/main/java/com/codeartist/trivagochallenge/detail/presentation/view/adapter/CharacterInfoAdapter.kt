package com.codeartist.trivagochallenge.detail.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeartist.trivagochallenge.databinding.ItemCharacterInfoBinding
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel
import javax.inject.Inject


class CharacterInfoAdapter @Inject constructor() :
    RecyclerView.Adapter<CharacterInfoAdapter.ViewHolder>() {

    private lateinit var character: CharacterModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCharacterInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(character)
    }

    override fun getItemCount(): Int {
        return 1
    }

    fun setInfo(item: CharacterModel?) {
        item?.let {
            character = item
            notifyDataSetChanged()
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var binding: ItemCharacterInfoBinding

        constructor(binding: ItemCharacterInfoBinding) : this(binding.root) {
            this.binding = binding
        }

        fun bind(item: CharacterModel) {
            binding.characterInfo = item
            binding.executePendingBindings()
        }
    }

}

