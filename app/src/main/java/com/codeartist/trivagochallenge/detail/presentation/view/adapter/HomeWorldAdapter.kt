package com.codeartist.trivagochallenge.detail.presentation.view.adapter

import androidx.databinding.ViewDataBinding
import com.codeartist.trivagochallenge.R
import com.codeartist.trivagochallenge.databinding.ItemHomeworldBinding
import com.codeartist.trivagochallenge.detail.presentation.uimodel.PlanetModel
import com.dreampany.framework.ui.adapter.BaseAdapter
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped
class HomeWorldAdapter @Inject constructor() :
    BaseAdapter<PlanetModel, HomeWorldAdapter.ViewHolder>() {
    override fun layoutId(viewType: Int) = R.layout.item_homeworld

    override fun createViewHolder(
        bind: ViewDataBinding,
        viewType: Int
    ) = ViewHolder(bind as ItemHomeworldBinding)

    inner class ViewHolder(
        val bind: ItemHomeworldBinding
    ) : BaseAdapter.ViewHolder<PlanetModel, ViewHolder>(bind) {
        override fun bindView(input: PlanetModel, position: Int) {
            bind.homeWorld = input
            bind.executePendingBindings()
        }
    }

}

