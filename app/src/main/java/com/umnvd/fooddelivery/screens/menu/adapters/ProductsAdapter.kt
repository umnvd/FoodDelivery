package com.umnvd.fooddelivery.screens.menu.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.umnvd.fooddelivery.R
import com.umnvd.fooddelivery.databinding.ItemProductBinding
import com.umnvd.fooddelivery.models.Product
import com.umnvd.fooddelivery.screens.extentions.toast

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private var products: List<Product> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setProducts(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = products.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        val holder = ViewHolder(binding)

        with(binding) {
            root.setOnClickListener {
                it.context.toast(products[holder.bindingAdapterPosition].name)
            }
            itemProductBuyButton.setOnClickListener {
                it.context.toast("Buy ${products[holder.bindingAdapterPosition].name}")
            }
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    class ViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            with(binding) {
                itemProductTitle.text = product.name
                itemProductDescription.text = product.description
                itemProductBuyButton.text = root.context.getString(R.string.price_rub, product.price)

                Glide.with(itemProductImage)
                    .load(product.imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .placeholder(R.drawable.product_placeholder)
                    .error(R.drawable.product_placeholder)
                    .centerCrop()
                    .into(itemProductImage)
            }
        }

    }

}