package com.codeartist.starwar.detail.presentation.view.adapter

import androidx.databinding.ViewDataBinding
import com.codeartist.starwar.R
import com.codeartist.starwar.databinding.ItemSpeciesBinding
import com.codeartist.starwar.detail.presentation.uimodel.SpeciesModel
import javax.inject.Inject

class SpeciesAdapter @Inject constructor() :
    BaseAdapter<SpeciesModel, SpeciesAdapter.ViewHolder>() {
    override fun layoutId(viewType: Int) = R.layout.item_species

    override fun createViewHolder(
        bind: ViewDataBinding,
        viewType: Int
    ) = ViewHolder(bind as ItemSpeciesBinding)

    inner class ViewHolder(
        val bind: ItemSpeciesBinding
    ) : BaseAdapter.ViewHolder<SpeciesModel, ViewHolder>(bind) {
        override fun bindView(input: SpeciesModel, position: Int) {
            bind.species = input
            bind.executePendingBindings()
        }
    }
}