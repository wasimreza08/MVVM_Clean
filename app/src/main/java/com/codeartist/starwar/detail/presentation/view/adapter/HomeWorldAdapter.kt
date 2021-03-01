package com.codeartist.starwar.detail.presentation.view.adapter

import androidx.databinding.ViewDataBinding
import com.codeartist.starwar.R
import com.codeartist.starwar.databinding.ItemHomeworldBinding
import com.codeartist.starwar.detail.presentation.uimodel.HomeWorldModel
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped
class HomeWorldAdapter @Inject constructor() :
    BaseAdapter<HomeWorldModel, HomeWorldAdapter.ViewHolder>() {
    override fun layoutId(viewType: Int) = R.layout.item_homeworld

    override fun createViewHolder(
        bind: ViewDataBinding,
        viewType: Int
    ) = ViewHolder(bind as ItemHomeworldBinding)

    inner class ViewHolder(
        val bind: ItemHomeworldBinding
    ) : BaseAdapter.ViewHolder<HomeWorldModel, ViewHolder>(bind) {
        override fun bindView(input: HomeWorldModel, position: Int) {
            bind.homeWorld = input
            bind.executePendingBindings()
        }
    }

}

