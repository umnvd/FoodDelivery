package com.umnvd.fooddelivery.screens.menu.views

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.tabs.TabLayout

class CustomTabLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TabLayout(context, attrs, defStyleAttr) {

    init {
        addOnTabSelectedListener(object : OnTabSelectedListener {

            override fun onTabSelected(tab: Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabUnselected(tab: Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: Tab?) {}

        })
    }

}