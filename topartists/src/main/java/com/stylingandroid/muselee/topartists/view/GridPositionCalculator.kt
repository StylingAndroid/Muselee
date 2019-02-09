package com.stylingandroid.muselee.topartists.view

import androidx.recyclerview.widget.GridLayoutManager

internal class GridPositionCalculator(var itemCount: Int) : GridLayoutManager.SpanSizeLookup() {

    companion object {

        private val doubleItems: IntRange = (1..10)
        const val fullSpanSize = 6
        private const val doubleSpanCount = 2
        private const val tripleSpanCount = 3
        private const val doubleSpanSize: Int = fullSpanSize / doubleSpanCount
        private const val tripleSpanSize: Int = fullSpanSize / tripleSpanCount
    }

    override fun getSpanSize(position: Int): Int =
        when (position) {
            0 -> fullSpanSize
            in doubleItems -> doubleSpanSize
            else -> tripleSpanSize
        }

    fun getViewSize(position: Int): ViewSize =
        when (position) {
            0 -> ViewSize.FULL
            in doubleItems -> ViewSize.DOUBLE
            else -> ViewSize.TRIPLE
        }

    fun isEndItem(position: Int): Boolean =
        when (position) {
            0 -> true
            in doubleItems -> (position - doubleItems.start).rem(doubleSpanCount) != 0
            else -> (position - doubleItems.last).rem(tripleSpanCount) == 0
        }

    fun isInFinalBank(position: Int): Boolean =
            position >= itemCount - tripleSpanCount
}
