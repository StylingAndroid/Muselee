package com.stylingandroid.muselee.topartists.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

internal class TopArtistsItemDecoraton(
    @RecyclerView.Orientation val orientation: Int,
    private val itemSpacing: Int,
    private val calculator: GridPositionCalculator
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (orientation == RecyclerView.HORIZONTAL) {
            getHorizontalOffsets(outRect, position)
        } else {
            getVerticalOffsets(outRect, position)
        }
    }

    private fun getHorizontalOffsets(outRect: Rect, position: Int) {
        outRect.bottom = if (calculator.isEndItem(position)) 0 else itemSpacing
        outRect.right = if (calculator.isInFinalBank(position)) 0 else itemSpacing
    }

    private fun getVerticalOffsets(outRect: Rect, position: Int) {
        outRect.right = if (calculator.isEndItem(position)) 0 else itemSpacing
        outRect.bottom = if (calculator.isInFinalBank(position)) 0 else itemSpacing
    }
}
