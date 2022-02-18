package com.umnvd.fooddelivery.screens.menu.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class OffsetItemDecoration(
    private val outerOffset: Int,
    private val innerOffset: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val lastIndex = parent.adapter?.itemCount?.minus(1) ?: 0
        outRect.top = outerOffset
        outRect.bottom = outerOffset

        when (parent.getChildAdapterPosition(view)) {
            0 -> {
                outRect.right = innerOffset / 2
                outRect.left = outerOffset
            }
            lastIndex -> {
                outRect.right = outerOffset
                outRect.left = innerOffset / 2
            }
            else -> {
                outRect.right = innerOffset / 2
                outRect.left = innerOffset / 2
            }
        }
    }

}