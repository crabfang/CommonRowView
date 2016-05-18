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
    protected ImageView rvIcon;
    protected TextView rvTitle;
    protected ImageView rvFlag;
    protected TextView rvHint;
    protected ImageView rvOption;

    protected int DEFAULT_ICON_PADDING = 0;
    protected int DEFAULT_INNER_MARGIN = 0;
    protected int DEFAULT_TITLE_SIZE = 0;
    protected int DEFAULT_TITLE_COLOR = 0xFF666666;
    protected int DEFAULT_HINT_SIZE = 0;
    protected int DEFAULT_HINT_COLOR = 0xFF999999;

    private void initDefaultConfig(Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        DEFAULT_ICON_PADDING = (int) (density * 5);
        DEFAULT_INNER_MARGIN = (int) (density * 2);
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
    protected abstract void setInnerMargin(int margin);

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LayoutRowViewNormal);

        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_titleDrawable)) {
            setIcon(a.getDrawable(R.styleable.LayoutRowViewNormal_rv_titleDrawable));
        }

        setIconPadding((int) a.getDimension(R.styleable.LayoutRowViewNormal_rv_titleDrawablePadding, DEFAULT_ICON_PADDING));

        setTitleSize(TypedValue.COMPLEX_UNIT_PX, a.getDimension(R.styleable.LayoutRowViewNormal_rv_titleSize, DEFAULT_TITLE_SIZE));
        setTitleColor(a.getColor(R.styleable.LayoutRowViewNormal_rv_titleColor, DEFAULT_TITLE_COLOR));
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_title)) {
            setTitle(a.getText(R.styleable.LayoutRowViewNormal_rv_title));
        } else {
            setTitle("");
        }

        setHint(0);
        setHintSize(TypedValue.COMPLEX_UNIT_PX, a.getDimension(R.styleable.LayoutRowViewNormal_rv_hintSize, DEFAULT_HINT_SIZE));
        setHintColor(a.getColor(R.styleable.LayoutRowViewNormal_rv_hintColor, DEFAULT_HINT_COLOR));
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_hintTips)) {
            setHint(a.getText(R.styleable.LayoutRowViewNormal_rv_hintTips));
        } else {
            setHint("");
        }

        int margin = (int) a.getDimension(R.styleable.LayoutRowViewNormal_rv_innerMargin, DEFAULT_INNER_MARGIN);
        setInnerMargin(margin);

        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_pointDrawable)) {
            setFlagDrawable(a.getDrawable(R.styleable.LayoutRowViewNormal_rv_pointDrawable));
        }
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_rightDrawable)) {
            setOptionDrawable(a.getDrawable(R.styleable.LayoutRowViewNormal_rv_rightDrawable));
        }

        showOption(a.getBoolean(R.styleable.LayoutRowViewNormal_rv_showRight, true));
        showHint(a.getBoolean(R.styleable.LayoutRowViewNormal_rv_showHint, false));
        showPoint(a.getBoolean(R.styleable.LayoutRowViewNormal_rv_showPoint, false));

        a.recycle();

        showHead(true);
    }

    public void setTitle(int titleRes) {
        if(rvTitle == null) return;

        rvTitle.setText(titleRes);
    }

    public void setTitle(CharSequence hint) {
        if(rvTitle == null) return;

        rvTitle.setText(hint);
    }

    public void setIcon(int iconRes) {
        if(rvIcon == null || iconRes <= 0) return;

        rvIcon.setImageResource(iconRes);
    }

    public void setIcon(Drawable icon) {
        if(rvIcon == null) return;

        rvIcon.setImageDrawable(icon);
    }

    public void setHint(int hintRes) {
        if(rvHint == null || hintRes <= 0) return;

        rvHint.setText(hintRes);
    }

    public void setHint(CharSequence hint) {
        if(rvHint == null) return;

        rvHint.setText(hint);
    }

    public void setTitleSize(int unit, float size) {
        if(rvTitle == null) return;

        rvTitle.setTextSize(unit, size);
    }

    public void setTitleColor(int color) {
        if(rvTitle == null) return;

        rvTitle.setTextColor(color);
    }

    public void setHintSize(int unit, float size) {
        if(rvHint == null) return;

        rvHint.setTextSize(unit, size);
    }

    public void setHintColor(int color) {
        if(rvHint == null) return;

        rvHint.setTextColor(color);
    }

    public void setIconPadding(int padding) {
        if(rvIcon == null) return;

        RelativeLayout.LayoutParams params = (LayoutParams) rvIcon.getLayoutParams();
        params.rightMargin = padding;
        rvIcon.setLayoutParams(params);
    }

    public void setFlagDrawable(Drawable drawable) {
        if(rvFlag == null) return;

        rvFlag.setImageDrawable(drawable);
    }

    public void setOptionDrawable(Drawable drawable) {
        if(rvOption == null) return;

        rvOption.setImageDrawable(drawable);
    }

    public void showHead(boolean show) {
        if(viewHead == null) return;

        viewHead.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showHint(boolean show) {
        if(rvHint == null) return;

        viewHint.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showOption(boolean show) {
        if(rvOption == null) return;

        rvOption.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showPoint(boolean show) {
        if(rvFlag == null) return;

        rvFlag.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public String getTitle() {
        return rvTitle == null ? "" : rvTitle.getText().toString();
    }

    public String getHint() {
        return rvHint == null ? "" : rvHint.getText().toString();
    }

    public ImageView getIconView() {
        return rvIcon;
    }

    public TextView getTitleView() {
        return rvTitle;
    }

    public TextView getHintView() {
        return rvHint;
    }
}