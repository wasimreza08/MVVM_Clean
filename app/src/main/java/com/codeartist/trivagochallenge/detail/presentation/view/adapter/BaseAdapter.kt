package com.dreampany.framework.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

abstract class BaseAdapter<T, VH : BaseAdapter.ViewHolder<T, VH>> :
    RecyclerView.Adapter<VH>() {

    protected val items: MutableList<T>

    init {
        items = Collections.synchronizedList(ArrayList())
    }

    protected fun viewType(item: T): Int = 0

    @LayoutRes
    protected abstract fun layoutId(viewType: Int): Int

    protected abstract fun createViewHolder(bind: ViewDataBinding, viewType: Int): VH

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position) ?: return 0
        return viewType(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutId = layoutId(viewType)
        return createViewHolder(layoutId.bindInflater(parent), viewType)
    }

    fun Int.bindInflater(parent: ViewGroup, attachToRoot: Boolean = false): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            this,
            parent,
            attachToRoot
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.run {
            holder.bindView(this, position)
        }
    }
    open fun getItem(position: Int): T? =
        if (!isValidPosition(position)) null else items[position]

    open fun getPosition(item: T): Int = items.indexOf(item)

    open fun addAll(items: List<T>) {
        for (item in items) {
            add(item, true)
        }
    }

    open fun addAll(items: List<T>, notify: Boolean) {
        for (item in items) {
            add(item, notify.not())
        }
        if (notify) notifyDataSetChanged()
    }


    open fun add(position: Int, item: T, notify: Boolean = false) {
        items.add(position, item)
        if (notify)
            notifyItemInserted(position)
    }

    open fun add(item: T, notify: Boolean = false) {
        val position = getPosition(item)
        if (position == -1) {
            items.add(item)
            if (notify)
                notifyItemInserted(itemCount - 1)
        } else {
            items[position] = item
            if (notify)
                notifyItemChanged(position)
        }
    }

    open fun add(position: Int, item: T) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    open fun removeAt(position: Int): T {
        val item = items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items.size)
        return item
    }

    open fun clearAll() {
        items.clear()
        notifyDataSetChanged()
    }

    private fun isValidPosition(position: Int): Boolean = position >= 0 && position < itemCount

    abstract class ViewHolder<T, VH : RecyclerView.ViewHolder>
    protected constructor(bind: ViewDataBinding) : RecyclerView.ViewHolder(bind.root) {
        protected val context: Context get() = itemView.context

        abstract fun bindView(input: T, position: Int)
    }
}