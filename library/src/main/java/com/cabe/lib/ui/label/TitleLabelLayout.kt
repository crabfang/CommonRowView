package com.cabe.lib.ui.label

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.cabe.lib.ui.rowview.R
import com.cabe.lib.ui.utils.InnerSizeUtils

class TitleLabelLayout: LinearLayout {
    private var labelIcon: ImageView? = null
    private lateinit var labelView: TextView
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        initView(context, attrs, defStyleAttr, 0)
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        initView(context, attrs, defStyleAttr, defStyleRes)
    }

    private val labelDefaultSize = InnerSizeUtils.dp2px(context, 12f)
    private val labelDefaultColor = Color.parseColor("#333333")
    private fun initView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        orientation = HORIZONTAL
        if(context != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.TitleLabelLayout, defStyleAttr, defStyleRes)

            val titleSize = a?.getDimensionPixelSize(R.styleable.TitleLabelLayout_tl_titleSize, labelDefaultSize) ?: labelDefaultSize
            val titleColor = a?.getColor(R.styleable.TitleLabelLayout_tl_titleColor, labelDefaultColor) ?: labelDefaultColor
            val titleGravity = a?.getInt(R.styleable.TitleLabelLayout_tl_titleGravity, LabelGravity.Center.gravity) ?: LabelGravity.Center.gravity
            val titleStr = a?.getString(R.styleable.TitleLabelLayout_tl_title)
            val titleMargin = a?.getDimensionPixelOffset(R.styleable.TitleLabelLayout_tl_titlePadding, 0) ?: 0

            labelView = TextView(context)
            labelView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize.toFloat())
            labelView.setTextColor(titleColor)
            labelView.text = titleStr

            val labelParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            labelParams.gravity = LabelGravity.create(titleGravity).getViewGravity()
            labelParams.rightMargin = titleMargin
            addView(labelView, 0, labelParams)

            val icon = a?.getDrawable(R.styleable.TitleLabelLayout_tl_titleDrawable)
            if(icon != null) {
                labelIcon = ImageView(context)
                labelIcon?.setImageDrawable(icon)

                val defaultIconWidth = LayoutParams.WRAP_CONTENT
                val iconWidth = a.getDimensionPixelOffset(R.styleable.TitleLabelLayout_tl_titleDrawableWidth, defaultIconWidth)
                val iconHeight = a.getDimensionPixelOffset(R.styleable.TitleLabelLayout_tl_titleDrawableHeight, defaultIconWidth)
                val iconGravity = a.getInt(R.styleable.TitleLabelLayout_tl_titleDrawableGravity, LabelGravity.Center.gravity)
                val iconParams = LayoutParams(iconWidth, iconHeight)
                iconParams.gravity = LabelGravity.create(iconGravity).getViewGravity()
                iconParams.rightMargin = a.getDimensionPixelOffset(R.styleable.TitleLabelLayout_tl_titleDrawablePadding, 0)
                addView(labelIcon, 0, iconParams)
            }

            a?.recycle()
        }
    }

    fun getTitleView(): TextView {
        return labelView
    }

    fun getIconView(): ImageView? {
        return labelIcon
    }
}

enum class LabelGravity(gravity: Int) {
    Top(1), Center(2), Bottom(3);

    var gravity = 1
    init {
        this.gravity = gravity
    }

    fun getViewGravity(): Int {
        return when(gravity) {
            Top.gravity -> Gravity.TOP
            Bottom.gravity -> Gravity.BOTTOM
            else -> Gravity.CENTER
        }
    }

    companion object {
        fun create(gravity: Int): LabelGravity {
            return when (gravity) {
                1 -> Top
                2 -> Center
                3 -> Bottom
                else -> Center
            }
        }
    }
}