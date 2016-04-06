package com.cabe.lib.ui.rowview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 操作栏抽象类
 * Created by cabe on 16/3/28.
 */
public abstract class AbstractRowView extends RelativeLayout {
    protected View viewHead;
    protected LinearLayout viewHint;
    protected ImageView iconVal;
    protected TextView titleVal;
    protected ImageView point;
    protected TextView hintTips;
    protected ImageView rightImg;

    protected int DEFAULT_ICON_PADDING = 0;
    protected int DEFAULT_TITLE_SIZE = 0;
    protected int DEFAULT_TITLE_COLOR = 0xFF666666;
    protected int DEFAULT_HINT_SIZE = 0;
    protected int DEFAULT_HINT_COLOR = 0xFF999999;

    private void initDefaultConfig(Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        DEFAULT_ICON_PADDING = (int) (density * 5);
        DEFAULT_TITLE_SIZE = (int) (density * 16);
        DEFAULT_HINT_SIZE = (int) (density * 12);
    }

    public AbstractRowView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initDefaultConfig(context);
        initView(context);
        initAttr(context, attrs);
    }

    protected abstract void initView(Context context);

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LayoutRowViewNormal);
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_titleDrawable)) {
            Drawable icon = a.getDrawable(R.styleable.LayoutRowViewNormal_rv_titleDrawable);
            int padding = (int) a.getDimension(R.styleable.LayoutRowViewNormal_rv_titleDrawablePadding, DEFAULT_ICON_PADDING);
            iconVal.setImageDrawable(icon);

            RelativeLayout.LayoutParams params = (LayoutParams) iconVal.getLayoutParams();
            params.rightMargin = padding;
            iconVal.setLayoutParams(params);
        }
        titleVal.setTextSize(TypedValue.COMPLEX_UNIT_PX, a.getDimension(R.styleable.LayoutRowViewNormal_rv_titleSize, DEFAULT_TITLE_SIZE));
        titleVal.setTextColor(a.getColor(R.styleable.LayoutRowViewNormal_rv_titleColor, DEFAULT_TITLE_COLOR));
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_title)) {
            setTitle(a.getText(R.styleable.LayoutRowViewNormal_rv_title));
        } else {
            setTitle("");
        }

        hintTips.setTextSize(TypedValue.COMPLEX_UNIT_PX, a.getDimension(R.styleable.LayoutRowViewNormal_rv_hintSize, DEFAULT_HINT_SIZE));
        hintTips.setTextColor(a.getColor(R.styleable.LayoutRowViewNormal_rv_hintColor, DEFAULT_HINT_COLOR));
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_hintTips)) {
            setHint(a.getText(R.styleable.LayoutRowViewNormal_rv_hintTips));
        } else {
            setHint("");
        }

        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_pointDrawable)) {
            point.setImageDrawable(a.getDrawable(R.styleable.LayoutRowViewNormal_rv_pointDrawable));
        }
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_rightDrawable)) {
            rightImg.setImageDrawable(a.getDrawable(R.styleable.LayoutRowViewNormal_rv_rightDrawable));
        }

        showRight(a.getBoolean(R.styleable.LayoutRowViewNormal_rv_showRight, true));
        showHint(a.getBoolean(R.styleable.LayoutRowViewNormal_rv_showHint, false));
        showPoint(a.getBoolean(R.styleable.LayoutRowViewNormal_rv_showPoint, false));

        a.recycle();

        showHead(true);
    }

    public void setTitle(int titleRes) {
        titleVal.setText(titleRes);
    }

    public void setTitle(CharSequence hint) {
        titleVal.setText(hint);
    }

    public void setIcon(int iconRes) {
        if(iconRes <= 0) return;

        try {
            Drawable drawable = getContext().getResources().getDrawable(iconRes);
            titleVal.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setHint(int hintRes) {
        hintTips.setText(hintRes);
    }

    public void setHint(CharSequence hint) {
        hintTips.setText(hint);
    }

    public void showHead(boolean show) {
        viewHead.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showHint(boolean show) {
        viewHint.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showRight(boolean show) {
        rightImg.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showPoint(boolean show) {
        point.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}