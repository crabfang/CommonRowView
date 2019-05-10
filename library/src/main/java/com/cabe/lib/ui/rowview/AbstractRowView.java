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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 操作栏抽象类
 * Created by cabe on 16/3/28.
 */
public abstract class AbstractRowView extends RelativeLayout {
    protected View viewTitle;
    protected RelativeLayout viewHint;
    protected RelativeLayout viewOption;
    protected ImageView rvIcon;
    protected TextView rvTitle;
    protected ImageView rvFlag;
    protected TextView rvHint;
    protected ImageView rvOption;
    protected View rvDivider;

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
        rvDivider = findViewById(R.id.layout_custom_row_view_divider);
        initAttr(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AbstractRowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        initDefaultConfig(context);
        initView(context);
        rvDivider = findViewById(R.id.layout_custom_row_view_divider);
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
    protected abstract int getTitleDefaultGravity();
    protected abstract int getHintDefaultGravity();

    protected void initAttr(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LayoutRowViewNormal, defStyleAttr, defStyleRes);

        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_titleDrawable)) {
            setIcon(a.getDrawable(R.styleable.LayoutRowViewNormal_rv_titleDrawable));
        } else {
            setIcon(0);
        }
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_titleDrawablePadding)) {
            setIconPadding((int) a.getDimension(R.styleable.LayoutRowViewNormal_rv_titleDrawablePadding, DEFAULT_ICON_PADDING));
            rvIcon.setVisibility(View.VISIBLE);
        }

        int defaultWidth = LayoutParams.WRAP_CONTENT;
        int iconWidth = (int) a.getDimension(R.styleable.LayoutRowViewNormal_rv_titleDrawableWidth, defaultWidth);
        int iconHeight = (int) a.getDimension(R.styleable.LayoutRowViewNormal_rv_titleDrawableHeight, defaultWidth);
        setIconSize(iconWidth, iconHeight);

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
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_hintMaxLines)) {
            int maxLines = a.getInt(R.styleable.LayoutRowViewNormal_rv_hintMaxLines, 1);
            setHintMaxLines(maxLines);
        }
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_hintEllipsize)) {
            int val = a.getInt(R.styleable.LayoutRowViewNormal_rv_hintEllipsize, 3);
            Ellipsize ellipsize = Ellipsize.create(val);
            setHintEllipsize(ellipsize);
        }

        int optionPadding = (int) a.getDimension(R.styleable.LayoutRowViewNormal_rv_optionPadding, DEFAULT_OPTION_PADDING);
        setOptionPadding(optionPadding);

        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_pointDrawable)) {
            setFlagDrawable(a.getDrawable(R.styleable.LayoutRowViewNormal_rv_pointDrawable));
        }
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_rightDrawable)) {
            setOptionDrawable(a.getDrawable(R.styleable.LayoutRowViewNormal_rv_rightDrawable));
        }

        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_hintMargin)) {
            int hintMargin = (int) a.getDimension(R.styleable.LayoutRowViewNormal_rv_hintMargin, 0);
            setHintMargin(hintMargin);
        }

        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_hintGravity)) {
            int attrVal = a.getInt(R.styleable.LayoutRowViewNormal_rv_hintGravity, getHintDefaultGravity());
            setHintGravity(Gravity.create(attrVal));
        }

        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_hintLayout)) {
            int resId = a.getResourceId(R.styleable.LayoutRowViewNormal_rv_hintLayout, 0);
            if(resId > 0) {
                replaceHint(resId);
            }
        }

        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_dividerPosition)) {
            int attrVal = a.getInt(R.styleable.LayoutRowViewNormal_rv_dividerPosition, DividerPosition.Bottom.getVal());
            setDividerPosition(DividerPosition.create(attrVal));
        }
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_dividerSize)) {
            int dividerSize = a.getDimensionPixelOffset(R.styleable.LayoutRowViewNormal_rv_dividerSize, 0);
            if(rvDivider != null) {
                LayoutParams params = (LayoutParams) rvDivider.getLayoutParams();
                params.height = dividerSize;
                rvDivider.setLayoutParams(params);
            }
        }
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_dividerColor)) {
            int dividerColor = a.getColor(R.styleable.LayoutRowViewNormal_rv_dividerColor, 0xFF333333);
            if(rvDivider != null) {
                rvDivider.setBackgroundColor(dividerColor);
            }
        }
        int dividerMarginLeft = -1;
        int dividerMarginRight = -1;
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_dividerMarginLeft)) {
            dividerMarginLeft = a.getDimensionPixelOffset(R.styleable.LayoutRowViewNormal_rv_dividerMarginLeft, -1);
        }
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_dividerMarginRight)) {
            dividerMarginRight = a.getDimensionPixelOffset(R.styleable.LayoutRowViewNormal_rv_dividerMarginRight, -1);
        }
        setDividerMargin(dividerMarginLeft, dividerMarginRight);

        showOption(a.getBoolean(R.styleable.LayoutRowViewNormal_rv_showRight, true));
        showHint(a.getBoolean(R.styleable.LayoutRowViewNormal_rv_showHint, false));
        showPoint(a.getBoolean(R.styleable.LayoutRowViewNormal_rv_showPoint, false));
        showDivider(a.getBoolean(R.styleable.LayoutRowViewNormal_rv_showDivider, false));

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

        showHint(hintRes > 0);
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

    public void setHintGravity(Gravity gravity) {
        if(viewHint == null) return;

        int gravityVal = android.view.Gravity.LEFT;
        switch(gravity) {
            case Left:
                gravityVal = android.view.Gravity.LEFT;
                break;
            case Center:
                gravityVal = android.view.Gravity.CENTER_HORIZONTAL;
                break;
            case Right:
                gravityVal = android.view.Gravity.RIGHT;
                break;
        }
        viewHint.setGravity(gravityVal);
    }

    public void setIconPadding(int padding) {
        if(rvIcon == null) return;

        RelativeLayout.LayoutParams params = (LayoutParams) rvIcon.getLayoutParams();
        params.rightMargin = padding;
        rvIcon.setLayoutParams(params);
    }

    public void setIconSize(int width, int height) {
        if(rvIcon == null) return;

        RelativeLayout.LayoutParams params = (LayoutParams) rvIcon.getLayoutParams();
        params.width = width;
        params.height = height;
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

    public void setHintMaxLines(int maxLines) {
        if(rvHint == null || maxLines <= 0) return;

        rvHint.setSingleLine(false);
        rvHint.setMaxLines(maxLines);
    }

    public void setHintEllipsize(Ellipsize ellipsize) {
        if(rvHint == null || ellipsize == null) return;

        TextUtils.TruncateAt truncateAt;
        switch (ellipsize) {
            case Start:
                truncateAt = TextUtils.TruncateAt.START;
                break;
            case Middle:
                truncateAt = TextUtils.TruncateAt.MIDDLE;
                break;
            default:
                truncateAt = TextUtils.TruncateAt.END;
                break;
        }
        rvHint.setEllipsize(truncateAt);
    }

    public abstract void setHintMargin(int margin);

    public void setDividerPosition(DividerPosition position) {
        if(rvDivider == null) return;

        RelativeLayout.LayoutParams params = (LayoutParams) rvDivider.getLayoutParams();
        if(position == DividerPosition.Top) {
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        } else {
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        }
        rvDivider.setLayoutParams(params);
    }

    public void setDividerMargin(int marginLeft, int marginRight) {
        if(rvDivider == null) return;

        RelativeLayout.LayoutParams params = (LayoutParams) rvDivider.getLayoutParams();
        if(marginLeft != -1) {
            params.leftMargin = marginLeft;
        }
        if(marginRight != -1) {
            params.rightMargin = marginRight;
        }
        rvDivider.setLayoutParams(params);
    }

    public void showHead(boolean show) {
        if(viewTitle == null) return;

        viewTitle.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showHint(boolean show) {
        if(viewHint == null) return;

        viewHint.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showOption(boolean show) {
        if(viewOption == null) return;

        viewOption.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showPoint(boolean show) {
        if(rvFlag == null) return;

        rvFlag.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showDivider(boolean show) {
        if(rvDivider == null) return;

        rvDivider.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void replaceHint(int resId) {
        if(viewHint != null && resId > 0) {
            View viewInner = LayoutInflater.from(getContext()).inflate(resId, viewHint, false);
            replaceHint(viewInner);
        }
    }

    public void replaceHint(View viewInner) {
        if(viewHint != null && viewInner != null) {
            viewHint.removeAllViews();
            viewHint.addView(viewInner);
        }
    }

    public String getTitle() {
        return rvTitle == null ? "" : rvTitle.getText().toString();
    }

    public String getHint() {
        return rvHint == null ? "" : rvHint.getText().toString();
    }

    public View getViewTitle() {
        return viewTitle;
    }

    public View getViewHint() {
        return viewHint;
    }

    public RelativeLayout getViewOption() {
        return viewOption;
    }

    public TextView getTextTitle() {
        return rvTitle;
    }

    public TextView getTextHint() {
        return rvHint;
    }

    public ImageView getImageIcon() {
        return rvIcon;
    }

    public ImageView getImageOP() {
        return rvOption;
    }

    public View getDivider() {
        return rvDivider;
    }

    public enum Gravity {
        Left(1), Center(2), Right(3);

        int val = 1;
        Gravity(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public static Gravity create(int val) {
            Gravity gravity;
            switch(val) {
                default:
                case 1:
                    gravity = Left;
                    break;
                case 2:
                    gravity = Center;
                    break;
                case 3:
                    gravity = Right;
                    break;
            }
            return gravity;
        }
    }

    public enum DividerPosition {
        Top(1), Bottom(2);

        int val = 2;
        DividerPosition(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public static DividerPosition create(int val) {
            DividerPosition position;
            switch (val) {
                case 1:
                    position = Top;
                    break;
                default:
                case 2:
                    position = Bottom;
                    break;
            }
            return position;
        }
    }

    private enum Ellipsize {
        Start(1), Middle(2), End(3);

        int val = 1;
        Ellipsize(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public static Ellipsize create(int val) {
            Ellipsize ellipsize;
            switch(val) {
                default:
                case 1:
                    ellipsize = Start;
                    break;
                case 2:
                    ellipsize = Middle;
                    break;
                case 3:
                    ellipsize = End;
                    break;
            }
            return ellipsize;
        }
    }
}