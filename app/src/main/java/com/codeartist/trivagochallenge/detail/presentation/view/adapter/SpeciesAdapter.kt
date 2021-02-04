package com.codeartist.trivagochallenge.detail.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeartist.trivagochallenge.databinding.ItemSpeciesBinding
import com.codeartist.trivagochallenge.detail.presentation.uimodel.SpeciesModel
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class SpeciesAdapter @Inject constructor() :
    RecyclerView.Adapter<SpeciesAdapter.ViewHolder>() {
    val species: MutableList<SpeciesModel> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSpeciesBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(species.get(position))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var binding: ItemSpeciesBinding

        constructor(binding: ItemSpeciesBinding) : this(binding.root) {
            this.binding = binding

        }

        fun bind(item: SpeciesModel) {
            binding.species = item
            binding.executePendingBindings()
        }
    }

    fun setSpeciesInfo(list : MutableList<SpeciesModel>){
        species.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return species.size
    }
}
