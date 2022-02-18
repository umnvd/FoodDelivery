package com.umnvd.fooddelivery.screens.menu.adapters

import android.annotation.SuppressLint
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.umnvd.fooddelivery.screens.menu.ProductsPageFragment

class CategoryPagesAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private var categories: List<String> = listOf()

    override fun getItemCount(): Int = categories.size

    override fun createFragment(position: Int): Fragment {
        return ProductsPageFragment().apply {
            arguments = bundleOf(
                ProductsPageFragment.CATEGORY_KEY to categories[position]
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCategories(newCategories: List<String>) {
        categories = newCategories
        notifyDataSetChanged()
    }

}