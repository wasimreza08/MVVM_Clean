package com.codeartist.trivagochallenge.detail.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeartist.trivagochallenge.databinding.ItemSingleLineBinding
import javax.inject.Inject


class SingleLineAdapter @Inject constructor() :
    RecyclerView.Adapter<SingleLineAdapter.ViewHolder>() {

    private var textString: String = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSingleLineBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(textString)
    }

    override fun getItemCount(): Int {
        return 1
    }

    fun setText(header: String) {
        textString = header
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var binding: ItemSingleLineBinding

        constructor(binding: ItemSingleLineBinding) : this(binding.root) {
            this.binding = binding
        }

        fun bind(text: String) {
            binding.text = text
            binding.executePendingBindings()
        }
    }

}

