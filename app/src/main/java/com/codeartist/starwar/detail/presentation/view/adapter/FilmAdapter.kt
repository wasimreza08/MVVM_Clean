package com.codeartist.starwar.detail.presentation.view.adapter

import androidx.databinding.ViewDataBinding
import com.codeartist.starwar.R
import com.codeartist.starwar.databinding.ItemFilmBinding
import com.codeartist.starwar.detail.presentation.uimodel.FilmModel
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped

class FilmAdapter @Inject constructor() :
    BaseAdapter<FilmModel, FilmAdapter.ViewHolder>() {
    override fun layoutId(viewType: Int) = R.layout.item_film

    override fun createViewHolder(
        bind: ViewDataBinding,
        viewType: Int
    ) = ViewHolder(bind as ItemFilmBinding)

    inner class ViewHolder(
        val bind: ItemFilmBinding
    ) : BaseAdapter.ViewHolder<FilmModel, ViewHolder>(bind) {
        override fun bindView(input: FilmModel, position: Int) {
            bind.film = input
            bind.executePendingBindings()
        }
    }

}