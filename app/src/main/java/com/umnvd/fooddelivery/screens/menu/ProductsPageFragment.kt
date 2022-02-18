package com.umnvd.fooddelivery.screens.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umnvd.fooddelivery.R
import com.umnvd.fooddelivery.databinding.FragmentProductsPageBinding
import com.umnvd.fooddelivery.models.Category
import com.umnvd.fooddelivery.screens.ViewModelsFactory
import com.umnvd.fooddelivery.screens.menu.adapters.ProductsAdapter

class ProductsPageFragment : Fragment(R.layout.fragment_products_page) {

    private val viewModel: MenuViewModel by viewModels { ViewModelsFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentProductsPageBinding.bind(view)
        val productsAdapter = ProductsAdapter()

        with(binding.root) {
            adapter = productsAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }

        arguments?.getParcelable<Category>(CATEGORY_KEY)?.let {
            viewModel.loadProductsByCategory(it)
            viewModel.getProductsByCategory(it)
                .observe(viewLifecycleOwner, productsAdapter::setProducts)
        }
    }

    companion object {

        const val CATEGORY_KEY = "category"

    }

}