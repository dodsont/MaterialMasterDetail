package com.lucasurbas.masterdetail.ui.widget

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View
import com.lucasurbas.masterdetail.R

/**
 * Created by Lucas on 03/01/2017.
 */

class BoundedCardView : CardView {

    private var boundedWidth: Int = 0
    private var boundedHeight: Int = 0

    constructor(context: Context) : super(context) {
        boundedWidth = 0
        boundedHeight = 0
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.BoundedView)
        boundedWidth = a.getDimensionPixelSize(R.styleable.BoundedView_bounded_width, 0)
        boundedHeight = a.getDimensionPixelSize(R.styleable.BoundedView_bounded_height, 0)
        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthMeasureSpec = widthMeasureSpec
        var heightMeasureSpec = heightMeasureSpec
        // Adjust width as necessary
        val measuredWidth = View.MeasureSpec.getSize(widthMeasureSpec)
        if (boundedWidth > 0 && boundedWidth < measuredWidth) {
            val measureMode = View.MeasureSpec.getMode(widthMeasureSpec)
            widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(boundedWidth, measureMode)
        }
        // Adjust height as necessary
        val measuredHeight = View.MeasureSpec.getSize(heightMeasureSpec)
        if (boundedHeight > 0 && boundedHeight < measuredHeight) {
            val measureMode = View.MeasureSpec.getMode(heightMeasureSpec)
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(boundedHeight, measureMode)
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}
