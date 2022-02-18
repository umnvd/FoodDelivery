package com.umnvd.fooddelivery.screens.menu.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.umnvd.fooddelivery.R
import com.umnvd.fooddelivery.databinding.ItemBannerBinding
import com.umnvd.fooddelivery.models.Ad
import com.umnvd.fooddelivery.screens.extentions.toast

class AdsAdapter : RecyclerView.Adapter<AdsAdapter.ViewHolder>() {

    private var ads: List<Ad> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setAds(newAds: List<Ad>) {
        ads = newAds
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = ads.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBannerBinding.inflate(inflater, parent, false)
        val holder = ViewHolder(binding)
        binding.root.setOnClickListener {
            it.context.toast(ads[holder.adapterPosition].description)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ads[position])
    }

    class ViewHolder(
        private val binding: ItemBannerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(ad: Ad) = with(binding) {
            Glide.with(itemBannerImage)
                .load(ad.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.ad_placeholder)
                .error(R.drawable.ad_placeholder)
                .centerCrop()
                .into(itemBannerImage)
        }

    }

}