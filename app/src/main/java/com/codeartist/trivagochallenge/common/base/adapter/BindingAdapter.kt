package com.codeartist.trivagochallenge.search.presentation.view.adapter

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.codeartist.trivagochallenge.common.utils.Constants

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("android:visibility")
    fun setVisibility(view: View, value: Boolean) {
        view.visibility = if (value) View.VISIBLE else View.GONE
    }
    @JvmStatic
    @BindingAdapter("header_text")
    fun TextView.setHeaderString(header: String?) {
        this.text =
            if (header.isNullOrEmpty()) Constants.EMPTY_STRING else header
    }
}

