package com.umnvd.fooddelivery.screens.menu.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umnvd.fooddelivery.databinding.ItemProductBinding
import com.umnvd.fooddelivery.models.Product
import com.umnvd.fooddelivery.screens.extentions.toast

class ProductsAdapter: RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    private var products: List<Product> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setProducts(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = products.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        val holder = ProductsViewHolder(binding)

        with(binding) {
            root.setOnClickListener {
                it.context.toast(products[holder.adapterPosition].name)
            }
            itemProductBuyButton.setOnClickListener {
                it.context.toast("Buy ${products[holder.adapterPosition].name}")
            }
        }

        return holder
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(products[position])
    }

    class ProductsViewHolder(
        private val binding: ItemProductBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            with(binding) {
                itemProductTitle.text = product.name
                itemProductDescription.text = product.description
            }
        }

    }

}