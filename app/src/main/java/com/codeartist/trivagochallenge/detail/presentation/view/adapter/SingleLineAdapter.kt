package com.codeartist.trivagochallenge.detail.presentation.view.adapter

import androidx.databinding.ViewDataBinding
import com.codeartist.trivagochallenge.R
import com.codeartist.trivagochallenge.databinding.ItemSingleLineBinding
import com.dreampany.framework.ui.adapter.BaseAdapter
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped
class SingleLineAdapter @Inject constructor() :
    BaseAdapter<String, SingleLineAdapter.ViewHolder>() {
    override fun layoutId(viewType: Int) = R.layout.item_single_line

    override fun createViewHolder(
        bind: ViewDataBinding,
        viewType: Int
    ) = ViewHolder(bind as ItemSingleLineBinding)

    inner class ViewHolder(
        val bind: ItemSingleLineBinding
    ) : BaseAdapter.ViewHolder<String, ViewHolder>(bind) {
        override fun bindView(input: String, position: Int) {
            bind.text = input
            bind.executePendingBindings()
        }
    }

}
