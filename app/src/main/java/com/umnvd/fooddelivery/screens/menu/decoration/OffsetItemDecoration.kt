package com.umnvd.fooddelivery.screens.menu.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class OffsetItemDecoration(
    private val offset: Int,
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val lastIndex = parent.adapter?.itemCount?.minus(1) ?: 0
        outRect.top = offset
        outRect.bottom = offset

        when(parent.getChildAdapterPosition(view)) {
            0 -> {
                outRect.right = offset / 2
                outRect.left = offset
            }
            lastIndex -> {
                outRect.right = offset
                outRect.left = offset / 2
            }
            else -> {
                outRect.right = offset / 2
                outRect.left = offset / 2
            }
        }
    }

}