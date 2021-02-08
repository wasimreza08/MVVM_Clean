package com.codeartist.trivagochallenge.detail.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeartist.trivagochallenge.databinding.ItemHomeworldBinding
import com.codeartist.trivagochallenge.detail.presentation.uimodel.PlanetModel
import javax.inject.Inject


class HomeWorldAdapter @Inject constructor() :
    RecyclerView.Adapter<HomeWorldAdapter.ViewHolder>() {

    private var homeWorld: PlanetModel = PlanetModel()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeworldBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(homeWorld)
    }

    override fun getItemCount(): Int {
        return 1
    }

    fun setHomeWorld(item: PlanetModel) {
        this.homeWorld = item
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var binding: ItemHomeworldBinding

        constructor(binding: ItemHomeworldBinding) : this(binding.root) {
            this.binding = binding
        }

        fun bind(item: PlanetModel) {
            binding.homeWorld = item
            binding.executePendingBindings()
        }
    }

}

