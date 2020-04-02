package com.cabe.lib.ui.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.PixelFormat
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

object DrawableUtils {
    fun zoomDrawable(drawable: Drawable, w: Int, h: Int): Drawable? {
        val width = drawable.intrinsicWidth
        val height = drawable.intrinsicHeight
        val oldBmp = drawableToBitmap(drawable)
        val matrix = Matrix()
        val scaleWidth = w.toFloat() / width
        val scaleHeight = h.toFloat() / height
        matrix.postScale(scaleWidth, scaleHeight)
        val newBmp = Bitmap.createBitmap(oldBmp, 0, 0, width, height, matrix, true)
        oldBmp.recycle()
        return BitmapDrawable(null, newBmp)
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        val width = drawable.intrinsicWidth
        val height = drawable.intrinsicHeight
        val config = if (drawable.opacity != PixelFormat.OPAQUE) Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565
        val bitmap = Bitmap.createBitmap(width, height, config)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, width, height)
        drawable.draw(canvas)
        return bitmap
    }
}