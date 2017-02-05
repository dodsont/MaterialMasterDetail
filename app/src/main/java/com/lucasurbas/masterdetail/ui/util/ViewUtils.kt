package com.lucasurbas.masterdetail.ui.util

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewTreeObserver

/**
 * Created by Lucas on 04/01/2017.
 */

class ViewUtils internal constructor() {
    companion object {

        /**
         * Returns whether or not the view has been laid out
         */
        internal fun isLaidOut(view: View): Boolean {
            if (Build.VERSION.SDK_INT >= 19) {
                return view.isLaidOut
            }

            return view.width > 0 && view.height > 0
        }

        /**
         * Executes the given [java.lang.Runnable] when the view is laid out
         */
        fun onLaidOut(view: View, runnable: () -> Unit) {
            if (isLaidOut(view)) {
                runnable()
                return
            }

            val observer = view.viewTreeObserver
            observer.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val trueObserver: ViewTreeObserver

                    if (observer.isAlive) {
                        trueObserver = observer
                    } else {
                        trueObserver = view.viewTreeObserver
                    }

                    if (Build.VERSION.SDK_INT >= 16) {
                        trueObserver.removeOnGlobalLayoutListener(this)
                    } else {

                        trueObserver.removeGlobalOnLayoutListener(this)
                    }
                    runnable()
                }
            })
        }

        fun convertDpToPixel(dp: Float, context: Context): Float {
            val resources = context.resources
            val metrics = resources.displayMetrics
            val px = dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
            return px
        }
    }
}
