package com.umnvd.fooddelivery.screens.menu

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.umnvd.fooddelivery.R
import com.umnvd.fooddelivery.databinding.FragmentMenuBinding
import com.umnvd.fooddelivery.screens.ViewModelsFactory
import com.umnvd.fooddelivery.screens.extentions.toast
import com.umnvd.fooddelivery.screens.menu.adapters.*
import com.umnvd.fooddelivery.screens.menu.decoration.OffsetItemDecoration

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val viewModel: MenuViewModel by viewModels { ViewModelsFactory() }
    private lateinit var binding: FragmentMenuBinding
    private val categoryTabsAdapter = CategoryTabsAdapter()

    private val onPageChangeCallback: ViewPager2.OnPageChangeCallback =
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                categoryTabsAdapter.onPageChanged(position)
                binding.menuTabsRecycler.scrollToPosition(position)
            }
        }

    private val onTabSelectedListener: OnTabSelectedListener =
        { pagePosition, tabScrollPosition ->
            binding.menuPager.setCurrentItem(pagePosition, true)
            binding.menuTabsRecycler.scrollToPosition(tabScrollPosition)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMenuBinding.bind(view)

        setUpAds()
        setUpToolbar()
        setUpMenu()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.menuPager.unregisterOnPageChangeCallback(onPageChangeCallback)
        categoryTabsAdapter.removeOnTabSelectedListener()
    }

    private fun setUpAds() {
        val adsAdapter = AdsAdapter()

        with(binding.menuBannersRecycler) {
            adapter = adsAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), RecyclerView.HORIZONTAL, false
            )
            val offset = context.resources.getDimensionPixelSize(R.dimen.margin_large)
            addItemDecoration(
                OffsetItemDecoration(offset, offset)
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

    private fun setUpMenu() {
        val categoryPagesAdapter = CategoryPagesAdapter(this)

        with(binding) {
            menuPager.adapter = categoryPagesAdapter
            menuPager.registerOnPageChangeCallback(onPageChangeCallback)

            menuTabsRecycler.adapter = categoryTabsAdapter.apply {
                setOnTabSelectedListener(onTabSelectedListener)
            }
            menuTabsRecycler.layoutManager = LinearLayoutManager(
                requireContext(), RecyclerView.HORIZONTAL, false
            )
            val outerOffset = requireContext().resources.getDimensionPixelSize(R.dimen.margin_large)
            val innerOffset = requireContext().resources.getDimensionPixelSize(R.dimen.margin_small)
            menuTabsRecycler.addItemDecoration(
                OffsetItemDecoration(outerOffset, innerOffset)
            )
            menuTabsRecycler.setHasFixedSize(true)
        }

        viewModel.categories.observe(viewLifecycleOwner) {
            categoryPagesAdapter.setCategories(it)
            categoryTabsAdapter.setCategories(it)

            binding.menuPager.currentItem = 0
            binding.menuTabsRecycler.scrollToPosition(0)
        }
    }

}