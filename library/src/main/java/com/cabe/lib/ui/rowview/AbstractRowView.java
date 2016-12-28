package com.cabe.lib.ui.rowview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 操作栏抽象类
 * Created by cabe on 16/3/28.
 */
public abstract class AbstractRowView extends RelativeLayout {
    protected View viewHead;
    protected RelativeLayout viewOption;
    protected ImageView rvIcon;
    protected TextView rvTitle;
    protected ImageView rvFlag;
    protected TextView rvHint;
    protected ImageView rvOption;

    protected int DEFAULT_ICON_PADDING = 0;
    protected int DEFAULT_OPTION_PADDING = 0;
    protected int DEFAULT_TITLE_SIZE = 0;
    protected int DEFAULT_TITLE_COLOR = 0xFF666666;
    protected int DEFAULT_HINT_SIZE = 0;
    protected int DEFAULT_HINT_COLOR = 0xFF999999;

    public AbstractRowView(Context context) {
        this(context, null);
    }

    public AbstractRowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbstractRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initDefaultConfig(context);
        initView(context);
        initAttr(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AbstractRowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        initDefaultConfig(context);
        initView(context);
        initAttr(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initDefaultConfig(Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        DEFAULT_ICON_PADDING = (int) (density * 5);
        DEFAULT_OPTION_PADDING = (int) (density * 10);
        DEFAULT_TITLE_SIZE = (int) (density * 16);
        DEFAULT_HINT_SIZE = (int) (density * 12);
    }

    protected abstract void initView(Context context);

    protected void initAttr(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LayoutRowViewNormal, defStyleAttr, defStyleRes);

        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_titleDrawable)) {
            setIcon(a.getDrawable(R.styleable.LayoutRowViewNormal_rv_titleDrawable));
            setIconPadding((int) a.getDimension(R.styleable.LayoutRowViewNormal_rv_titleDrawablePadding, DEFAULT_ICON_PADDING));
        } else {
            setIconPadding(0);
        }

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

        int optionPadding = (int) a.getDimension(R.styleable.LayoutRowViewNormal_rv_optionPadding, DEFAULT_OPTION_PADDING);
        setOptionPadding(optionPadding);

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
        if(rvIcon == null) return;

        rvIcon.setVisibility(iconRes <= 0 ? View.GONE : View.VISIBLE);
        if(iconRes > 0) {
            rvIcon.setImageResource(iconRes);
        }
    }

    public void setIcon(Drawable icon) {
        if(rvIcon == null) return;

        rvIcon.setImageDrawable(icon);
        rvIcon.setVisibility(icon == null ? View.GONE : View.VISIBLE);
    }

    public void setIcon(Bitmap bmp) {
        if(rvIcon == null) return;

        rvIcon.setImageBitmap(bmp);
        rvIcon.setVisibility(bmp == null ? View.GONE : View.VISIBLE);
    }

    public void setHint(int hintRes) {
        if(rvHint == null) return;

        rvHint.setVisibility(hintRes <= 0 ? View.GONE : View.VISIBLE);
        if(hintRes > 0) {
            rvHint.setText(hintRes);
        }
    }

    public void setHint(CharSequence hint) {
        if(rvHint == null) return;

        rvHint.setText(hint);
        rvHint.setVisibility(TextUtils.isEmpty(hint) ? View.GONE : View.VISIBLE);
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

    public void setOptionPadding(int padding) {
        if(viewOption != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewOption.getLayoutParams();
            params.leftMargin = padding;
            viewOption.setLayoutParams(params);
        }
    }

    public void setFlagDrawable(Drawable drawable) {
        if(rvFlag == null) return;

        rvFlag.setImageDrawable(drawable);
    }

    public void setOptionDrawable(Drawable drawable) {
        if(rvOption == null) return;

        rvOption.setImageDrawable(drawable);
    }

    public void setOptionDrawableRes(int resId) {
        if(rvOption == null || resId <= 0) return;

        rvOption.setImageResource(resId);
    }

    public void showHead(boolean show) {
        if(viewHead == null) return;

        viewHead.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showHint(boolean show) {
        if(rvHint == null) return;

        rvHint.setVisibility(show ? View.VISIBLE : View.GONE);
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

    public RelativeLayout getOpView() {
        return viewOption;
    }

    public ImageView getOpImage() {
        return rvOption;
    }
}