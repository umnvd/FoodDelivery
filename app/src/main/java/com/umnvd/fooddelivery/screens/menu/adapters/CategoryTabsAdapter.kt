package com.umnvd.fooddelivery.screens.menu.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.umnvd.fooddelivery.R
import com.umnvd.fooddelivery.models.Category

typealias OnTabSelectedListener = (pagePosition: Int, tabScrollPosition: Int) -> Unit

class CategoryTabsAdapter : RecyclerView.Adapter<CategoryTabsAdapter.ViewHolder>() {

    private var selectedPosition: Int = 0
    private var categories: List<Category> = listOf()
    private var listener: OnTabSelectedListener? = null

    override fun getItemCount(): Int = categories.size

    override fun getItemViewType(position: Int): Int {
        return if (position == selectedPosition) {
            R.layout.tab_selected
        } else {
            R.layout.tab_unselected
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(viewType, parent, false)
        val holder = ViewHolder(view)

        view.setOnClickListener {
            listener?.invoke(holder.adapterPosition, getScrollPosition(holder.adapterPosition))
            onPageChanged(holder.adapterPosition)
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[holder.adapterPosition].name)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCategories(newCategories: List<Category>) {
        categories = newCategories
        notifyDataSetChanged()
    }

    fun onPageChanged(position: Int) {
        notifyItemChanged(selectedPosition)
        notifyItemChanged(position)
        selectedPosition = position
    }

    fun setOnTabSelectedListener(listener: OnTabSelectedListener) {
        this.listener = listener
    }

    fun removeOnTabSelectedListener() {
        listener = null
    }

    private fun getScrollPosition(position: Int): Int {
        val range = position - selectedPosition
        Log.e("", "$selectedPosition -> $position")
        val signedStep = when {
            range < 0 -> -1
            range > 0 -> 1
            else -> 0
        }

        return when (position) {
            in (1 until categories.lastIndex) -> position + signedStep
            else -> position
        }
    }

    class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {

        private val tabTextView = root.findViewById<TextView>(R.id.tabText)

        fun bind(category: String) {
            tabTextView.text = category
        }

    }
}