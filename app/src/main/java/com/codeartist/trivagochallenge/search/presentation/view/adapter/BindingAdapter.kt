package com.codeartist.trivagochallenge.search.presentation.view.adapter

import android.view.View
import androidx.databinding.BindingAdapter
object BindingAdapters{
    @JvmStatic
    @BindingAdapter("android:visibility")
    fun setVisibility(view: View, value: Boolean) {
        view.setVisibility(if (value) View.VISIBLE else View.GONE)
    }
}

