package com.codeartist.trivagochallenge.detail.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeartist.trivagochallenge.databinding.ItemHeaderBinding
import javax.inject.Inject


class HeaderAdapter @Inject constructor() :
    RecyclerView.Adapter<HeaderAdapter.ViewHolder>() {

    private var headerString: String = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHeaderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(headerString)
    }

    override fun getItemCount(): Int {
        return 1
    }

    fun setHeader(header: String) {
        headerString = header
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var binding: ItemHeaderBinding

        constructor(binding: ItemHeaderBinding) : this(binding.root) {
            this.binding = binding
        }

        fun bind(text: String) {
            binding.headerString = text
            binding.executePendingBindings()
        }
    }

}

