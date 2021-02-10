package com.codeartist.trivagochallenge.detail.presentation.view.adapter

import androidx.databinding.ViewDataBinding
import com.codeartist.trivagochallenge.R
import com.codeartist.trivagochallenge.databinding.ItemCharacterInfoBinding
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel
import com.dreampany.framework.ui.adapter.BaseAdapter
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CharacterInfoAdapter @Inject constructor() :
    BaseAdapter<CharacterModel, CharacterInfoAdapter.ViewHolder>() {
    override fun layoutId(viewType: Int) = R.layout.item_character_info

    override fun createViewHolder(
        bind: ViewDataBinding,
        viewType: Int
    ) = ViewHolder(bind as ItemCharacterInfoBinding)

    inner class ViewHolder(
        val bind: ItemCharacterInfoBinding
    ) : BaseAdapter.ViewHolder<CharacterModel, ViewHolder>(bind) {
        override fun bindView(input: CharacterModel, position: Int) {
            bind.characterInfo = input
            bind.executePendingBindings()
        }
    }

}