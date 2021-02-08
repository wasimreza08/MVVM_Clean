package com.codeartist.trivagochallenge.detail.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeartist.trivagochallenge.databinding.ItemFilmBinding
import com.codeartist.trivagochallenge.detail.presentation.uimodel.FilmModel
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class FilmAdapter @Inject constructor() :
    RecyclerView.Adapter<FilmAdapter.ViewHolder>() {
    val films: MutableList<FilmModel> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFilmBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(films.get(position))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var binding: ItemFilmBinding

        constructor(binding: ItemFilmBinding) : this(binding.root) {
            this.binding = binding

        }

        fun bind(item: FilmModel) {
            binding.film = item
            binding.executePendingBindings()
        }
    }

    fun setFilms(list : MutableList<FilmModel>){
        films.clear()
        films.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return films.size
    }
}
