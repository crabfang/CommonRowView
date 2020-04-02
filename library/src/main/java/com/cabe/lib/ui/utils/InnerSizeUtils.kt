package com.cabe.lib.ui.utils

import android.content.Context

object InnerSizeUtils {
    private fun getDensity(context: Context): Float {
        return context.resources.displayMetrics.density
    }
    fun dp2px(context: Context, dp: Float): Int {
        return (dp * getDensity(context)).toInt()
    }
}