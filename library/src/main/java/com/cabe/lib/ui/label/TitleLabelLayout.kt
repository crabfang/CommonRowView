package com.cabe.lib.ui.label

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.TextUtils.TruncateAt
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
            val titleStr = a?.getString(R.styleable.TitleLabelLayout_tl_title)
            val titleGravity = a?.getInt(R.styleable.TitleLabelLayout_tl_titleGravity, LabelGravity.Center.gravity) ?: LabelGravity.Center.gravity
            val titleMargin = a?.getDimensionPixelOffset(R.styleable.TitleLabelLayout_tl_titlePadding, 0) ?: 0
            val titleWidth = a?.getDimensionPixelOffset(R.styleable.TitleLabelLayout_tl_titleFixedWidth, LayoutParams.WRAP_CONTENT) ?: LayoutParams.WRAP_CONTENT
            val titleMaxLine = a?.getInteger(R.styleable.TitleLabelLayout_tl_titleMaxLines, 1) ?: 1
            val titleEllipsize = a?.getInt(R.styleable.TitleLabelLayout_tl_titleEllipsize, Ellipsize.End.ellipsize) ?: Ellipsize.End.ellipsize

            labelView = TextView(context)
            labelView.maxLines = titleMaxLine
            labelView.ellipsize = Ellipsize.create(titleEllipsize).getTextEllipsize()
            labelView.gravity = LabelGravity.create(titleGravity).getViewGravity()
            labelView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize.toFloat())
            labelView.setTextColor(titleColor)
            labelView.text = titleStr

            val titleLayoutGravity = a?.getInt(R.styleable.TitleLabelLayout_tl_titleLayoutGravity, LabelGravity.Center.gravity) ?: LabelGravity.Center.gravity
            val labelParams = LayoutParams(titleWidth, LayoutParams.WRAP_CONTENT)
            labelParams.gravity = LabelGravity.create(titleLayoutGravity).getViewGravity()
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

private enum class LabelGravity(gravity: Int) {
    Top(1), Center(2), Bottom(3), Left(4), Right(5);

    var gravity = 1
    init {
        this.gravity = gravity
    }

    fun getViewGravity(): Int {
        return when(gravity) {
            Top.gravity -> Gravity.TOP
            Bottom.gravity -> Gravity.BOTTOM
            Left.gravity -> Gravity.LEFT
            Right.gravity -> Gravity.RIGHT
            else -> Gravity.CENTER
        }
    }

    companion object {
        fun create(gravity: Int): LabelGravity {
            return when (gravity) {
                1 -> Top
                2 -> Center
                3 -> Bottom
                4 -> Left
                5 -> Right
                else -> Center
            }
        }
    }
}

private enum class Ellipsize(ellipsize: Int) {
    Start(1), Middle(2), End(3);

    var ellipsize = 1
    init {
        this.ellipsize = ellipsize
    }

    fun getTextEllipsize(): TruncateAt {
        return when (ellipsize) {
            Start.ellipsize -> TruncateAt.START
            Middle.ellipsize -> TruncateAt.MIDDLE
            else -> TruncateAt.END
        }
    }

    companion object {
        fun create(ellipsize: Int): Ellipsize {
            return when (ellipsize) {
                1 -> Start
                2 -> Middle
                3 -> End
                else -> Start
            }
        }
    }
}