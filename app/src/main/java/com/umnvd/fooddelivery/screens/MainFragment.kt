package com.umnvd.fooddelivery.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.umnvd.fooddelivery.R
import com.umnvd.fooddelivery.databinding.FragmentMainBinding

class MainFragment: Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view)
        val fragmentMainContainer = childFragmentManager
            .findFragmentById(R.id.fragmentMainContainer)
        val navController = (fragmentMainContainer as NavHostFragment).navController
        binding.bottomNavigation.setupWithNavController(navController)
    }

}