package com.umnvd.fooddelivery.screens.menu

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.umnvd.fooddelivery.R
import com.umnvd.fooddelivery.databinding.FragmentMenuBinding
import com.umnvd.fooddelivery.screens.extentions.toast
import com.umnvd.fooddelivery.screens.menu.adapters.AdsAdapter
import com.umnvd.fooddelivery.screens.menu.adapters.CategoryPagesAdapter
import com.umnvd.fooddelivery.screens.menu.adapters.CitiesAdapter
import com.umnvd.fooddelivery.screens.menu.decoration.OffsetItemDecoration

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val viewModel: MenuViewModel by viewModels()
    private lateinit var binding: FragmentMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMenuBinding.bind(view)

        setUpAds()
        setUpToolbar()
        setUpProductsPager()

    }

    private fun setUpAds() {
        val adsAdapter = AdsAdapter()

        with(binding.menuBannersRecycler) {
            adapter = adsAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), RecyclerView.HORIZONTAL, false
            )
            addItemDecoration(
                OffsetItemDecoration(
                    context.resources.getDimensionPixelSize(R.dimen.margin_m)
                )
            )
            setHasFixedSize(true)
        }

        viewModel.ads.observe(viewLifecycleOwner, adsAdapter::setAds)
    }

    private fun setUpToolbar() {
        val citiesAdapter = CitiesAdapter(requireContext())
        val onCitySelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?,
                position: Int, id: Long
            ) {
                citiesAdapter.getItem(position)?.let(viewModel::setCurrentCity)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        with(binding) {
            menuCitiesSpinner.adapter = citiesAdapter
            menuCitiesSpinner.onItemSelectedListener = onCitySelectedListener

            menuScanQrButton.setOnClickListener {
                it.context.toast("QR")
            }
        }

        viewModel.cities.observe(viewLifecycleOwner, citiesAdapter::setCities)
    }

    private fun setUpProductsPager() {
        val categoryPagesAdapter = CategoryPagesAdapter(this)

        with(binding) {
            menuPager.adapter = categoryPagesAdapter
            TabLayoutMediator(
                menuTabs,
                menuPager,
                true
            ) { tab, position ->
                tab.setCustomView(R.layout.tab_category)
                tab.text = categoryPagesAdapter.getCategoryName(position)
            }.attach()
        }

//        viewModel.categories.observe(viewLifecycleOwner, categoryPagesAdapter::setCategories)
        viewModel.categories.observe(viewLifecycleOwner) {
            categoryPagesAdapter.setCategories(it)
            binding.menuPager.setCurrentItem(2, false)
        }
    }

}