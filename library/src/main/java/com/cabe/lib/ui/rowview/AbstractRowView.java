package com.cabe.lib.ui.rowview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 操作栏抽象类
 * Created by cabe on 16/3/28.
 */
public abstract class AbstractRowView extends ConstraintLayout {
    public final static int TEXT_STYLE_NORMAL = 1;
    public final static int TEXT_STYLE_BOLD = 2;
    public final static int TEXT_STYLE_ITALIC = 3;
    public final static int TEXT_STYLE_BOLD_ITALIC = 4;
    protected View viewTitle;
    protected RelativeLayout viewLabel;
    protected RelativeLayout viewOption;
    protected ImageView rvIcon;
    protected TextView rvTitle;
    protected ImageView rvFlag;
    protected TextView rvLabel;
    protected ImageView rvOption;

    protected int DEFAULT_ICON_PADDING = 0;
    protected int DEFAULT_OPTION_PADDING = 0;
    protected int DEFAULT_TITLE_SIZE = 0;
    protected int DEFAULT_TITLE_COLOR = 0xFF666666;
    protected int DEFAULT_LABEL_SIZE = 0;
    protected int DEFAULT_LABEL_COLOR = 0xFF999999;
    protected int DEFAULT_TITLE_LINE = 1;
    protected int DEFAULT_LABEL_LINE = 2;

    private boolean showDivider = false;
    private DividerPosition dividerPosition;
    private int dividerColor = 0xFF333333;
    private int dividerSize = 1;
    private int dividerMarginLeft = 0;
    private int dividerMarginRight = 0;

    public AbstractRowView(Context context) {
        this(context, null);
    }

    public AbstractRowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbstractRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initDefaultConfig();
        initView(context);
        initAttr(context, attrs, defStyleAttr);
    }

    private int getSP(int val) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (density * val);
    }

    protected void initDefaultConfig() {
        DEFAULT_ICON_PADDING = getSP(5);
        DEFAULT_OPTION_PADDING = getSP(10);
        DEFAULT_TITLE_SIZE = getSP(16);
        DEFAULT_LABEL_SIZE = getSP(12);
    }

    protected abstract void initView(Context context);
    protected abstract int getTitleDefaultGravity();
    protected abstract int getLabelDefaultGravity();

    protected void initAttr(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LayoutRowViewNormal, defStyleAttr, 0);

        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_titleDrawable)) {
            setIcon(a.getDrawable(R.styleable.LayoutRowViewNormal_rv_titleDrawable));
        } else {
            setIcon(0);
        }
        setIconPadding((int) a.getDimension(R.styleable.LayoutRowViewNormal_rv_titleDrawablePadding, DEFAULT_ICON_PADDING));
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_titleDrawablePadding)) {
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

        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_titleGravity)) {
            int attrVal = a.getInt(R.styleable.LayoutRowViewNormal_rv_titleGravity, getTitleDefaultGravity());
            setTitleGravity(Gravity.create(attrVal));
        }

        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_titleFixedWidth)) {
            int fixedWidth = (int) a.getDimension(R.styleable.LayoutRowViewNormal_rv_titleFixedWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            setTitleWidth(fixedWidth);
        } else {
            setTitleWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        setTitleMaxLines(a.getInt(R.styleable.LayoutRowViewNormal_rv_titleMaxLines, DEFAULT_TITLE_LINE));
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_titleEllipsize)) {
            int val = a.getInt(R.styleable.LayoutRowViewNormal_rv_titleEllipsize, 3);
            Ellipsize ellipsize = Ellipsize.create(val);
            setTitleEllipsize(ellipsize);
        }
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_titleStyle)) {
            int val = a.getInt(R.styleable.LayoutRowViewNormal_rv_titleStyle, TEXT_STYLE_NORMAL);
            setTitleStyle(val);
        }

        setLabel(0);
        setLabelSize(TypedValue.COMPLEX_UNIT_PX, a.getDimension(R.styleable.LayoutRowViewNormal_rv_labelSize, DEFAULT_LABEL_SIZE));
        setLabelColor(a.getColor(R.styleable.LayoutRowViewNormal_rv_labelColor, DEFAULT_LABEL_COLOR));
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_label)) {
            setLabel(a.getText(R.styleable.LayoutRowViewNormal_rv_label));
        } else {
            setLabel("");
        }
        setLabelMaxLines(a.getInt(R.styleable.LayoutRowViewNormal_rv_labelMaxLines, DEFAULT_LABEL_LINE));
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_labelEllipsize)) {
            int val = a.getInt(R.styleable.LayoutRowViewNormal_rv_labelEllipsize, 3);
            Ellipsize ellipsize = Ellipsize.create(val);
            setLabelEllipsize(ellipsize);
        }
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_labelStyle)) {
            int val = a.getInt(R.styleable.LayoutRowViewNormal_rv_labelStyle, TEXT_STYLE_NORMAL);
            setLabelStyle(val);
        }
        int labelMargin = (int) a.getDimension(R.styleable.LayoutRowViewNormal_rv_labelMargin, 0);
        setLabelMargin(labelMargin);
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_labelGravity)) {
            int attrVal = a.getInt(R.styleable.LayoutRowViewNormal_rv_labelGravity, getLabelDefaultGravity());
            setLabelGravity(Gravity.create(attrVal));
        }
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_labelLayout)) {
            int resId = a.getResourceId(R.styleable.LayoutRowViewNormal_rv_labelLayout, 0);
            if(resId > 0) {
                replaceLabel(resId);
            }
        }

        int optionPadding = (int) a.getDimension(R.styleable.LayoutRowViewNormal_rv_optionPadding, DEFAULT_OPTION_PADDING);
        setOptionPadding(optionPadding);
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_pointDrawable)) {
            setFlagDrawable(a.getDrawable(R.styleable.LayoutRowViewNormal_rv_pointDrawable));
        }
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_optionDrawable)) {
            setOptionDrawable(a.getDrawable(R.styleable.LayoutRowViewNormal_rv_optionDrawable));
        }

        showDivider = a.getBoolean(R.styleable.LayoutRowViewNormal_rv_showDivider, false);
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_dividerPosition)) {
            int attrVal = a.getInt(R.styleable.LayoutRowViewNormal_rv_dividerPosition, DividerPosition.Bottom.getVal());
            dividerPosition = DividerPosition.create(attrVal);
        }
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_dividerSize)) {
            dividerSize = a.getDimensionPixelOffset(R.styleable.LayoutRowViewNormal_rv_dividerSize, 0);
        }
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_dividerColor)) {
            dividerColor = a.getColor(R.styleable.LayoutRowViewNormal_rv_dividerColor, 0xFF333333);
        }
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_dividerMarginLeft)) {
            dividerMarginLeft = a.getDimensionPixelOffset(R.styleable.LayoutRowViewNormal_rv_dividerMarginLeft, -1);
        }
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_dividerMarginRight)) {
            dividerMarginRight = a.getDimensionPixelOffset(R.styleable.LayoutRowViewNormal_rv_dividerMarginRight, -1);
        }

        showOption(a.getBoolean(R.styleable.LayoutRowViewNormal_rv_showOption, true));
        showLabel(a.getBoolean(R.styleable.LayoutRowViewNormal_rv_showLabel, false));
        showPoint(a.getBoolean(R.styleable.LayoutRowViewNormal_rv_showPoint, false));

        a.recycle();

        showHead(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDivider(canvas);
    }

    private void drawDivider(Canvas canvas) {
        if(showDivider) {
            int width = getMeasuredWidth();
            int height = getMeasuredHeight();
            if(height == 0) return;

            Paint paintDivider = new Paint();
            paintDivider.setColor(dividerColor);
            paintDivider.setStrokeWidth(dividerSize);
            float startX = dividerMarginLeft;
            float startY = dividerPosition == DividerPosition.Top ? 0 : height - dividerSize;
            float stopX = width - dividerMarginRight;
            float stopY = dividerPosition == DividerPosition.Top ? 0 : height - dividerSize;
            canvas.drawLine(startX, startY, stopX, stopY, paintDivider);
        }
    }

    public void setTitle(int titleRes) {
        if(rvTitle == null) return;

        rvTitle.setText(titleRes);
    }

    public void setTitle(CharSequence titleStr) {
        if(rvTitle == null) return;

        rvTitle.setText(titleStr);
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

    public void setIconPadding(int padding) {
        if(viewTitle != null) {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) viewTitle.getLayoutParams();
            params.leftMargin = padding;
            viewTitle.setLayoutParams(params);
        }
    }

    public void setIconSize(int width, int height) {
        if(rvIcon == null) return;

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) rvIcon.getLayoutParams();
        params.width = width;
        params.height = height;
        rvIcon.setLayoutParams(params);
    }

    public void setLabel(int labelRes) {
        if(rvLabel == null) return;

        showLabel(labelRes > 0);
        if(labelRes > 0) {
            rvLabel.setText(labelRes);
        }
    }

    public void setLabel(CharSequence labelStr) {
        if(rvLabel == null) return;

        rvLabel.setText(labelStr);
        rvLabel.setVisibility(TextUtils.isEmpty(labelStr) ? View.GONE : View.VISIBLE);
    }

    public void setTitleSize(int unit, float size) {
        if(rvTitle == null) return;

        rvTitle.setTextSize(unit, size);
    }

    public void setTitleColor(int color) {
        if(rvTitle == null) return;

        rvTitle.setTextColor(color);
    }

    public void setTitleGravity(Gravity gravity) {
        if(rvTitle == null) return;

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
        rvTitle.setGravity(gravityVal);
    }

    public void setTitleWidth(int width) {
        if(viewTitle == null) return;

        ViewGroup.LayoutParams params = viewTitle.getLayoutParams();
        params.width = width;
        viewTitle.setLayoutParams(params);
    }

    public void setTitleMaxLines(int maxLines) {
        if(rvTitle == null || maxLines <= 0) return;

        rvTitle.setSingleLine(false);
        rvTitle.setMaxLines(maxLines);
    }

    public void setTitleEllipsize(Ellipsize ellipsize) {
        if(rvTitle == null || ellipsize == null) return;

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
        rvTitle.setEllipsize(truncateAt);
    }

    public void setTitleStyle(int textStyle) {
        Typeface typeface;
        switch (textStyle) {
            case TEXT_STYLE_BOLD:
                typeface = Typeface.defaultFromStyle(Typeface.BOLD);
                break;
            case TEXT_STYLE_ITALIC:
                typeface = Typeface.defaultFromStyle(Typeface.ITALIC);
                break;
            case TEXT_STYLE_BOLD_ITALIC:
                typeface = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC);
                break;
            default:
                typeface = Typeface.defaultFromStyle(Typeface.NORMAL);
                break;
        }
        rvTitle.setTypeface(typeface);
    }

    public void setLabelSize(int unit, float size) {
        if(rvLabel == null) return;

        rvLabel.setTextSize(unit, size);
    }

    public void setLabelColor(int color) {
        if(rvLabel == null) return;

        rvLabel.setTextColor(color);
    }

    public void setLabelGravity(Gravity gravity) {
        if(viewLabel == null) return;

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
        viewLabel.setGravity(gravityVal);
    }

    public void setLabelStyle(int textStyle) {
        Typeface typeface;
        switch (textStyle) {
            case TEXT_STYLE_BOLD:
                typeface = Typeface.defaultFromStyle(Typeface.BOLD);
                break;
            case TEXT_STYLE_ITALIC:
                typeface = Typeface.defaultFromStyle(Typeface.ITALIC);
                break;
            case TEXT_STYLE_BOLD_ITALIC:
                typeface = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC);
                break;
            default:
                typeface = Typeface.defaultFromStyle(Typeface.NORMAL);
                break;
        }
        rvLabel.setTypeface(typeface);
    }

    public void setOptionPadding(int padding) {
        if(viewOption != null) {
            viewOption.setPadding(padding, viewOption.getPaddingTop(), viewOption.getPaddingRight(), viewOption.getPaddingBottom());
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

    public void setLabelMaxLines(int maxLines) {
        if(rvLabel == null || maxLines <= 0) return;

        rvLabel.setSingleLine(false);
        rvLabel.setMaxLines(maxLines);
    }

    public void setLabelEllipsize(Ellipsize ellipsize) {
        if(rvLabel == null || ellipsize == null) return;

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
        rvLabel.setEllipsize(truncateAt);
    }

    public abstract void setLabelMargin(int margin);

    public void showHead(boolean show) {
        if(viewTitle == null) return;

        viewTitle.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showLabel(boolean show) {
        if(viewLabel == null) return;

        viewLabel.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showOption(boolean show) {
        if(viewOption == null) return;

        viewOption.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showPoint(boolean show) {
        if(rvFlag == null) return;

        rvFlag.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void replaceLabel(int resId) {
        if(viewLabel != null && resId > 0) {
            View viewInner = LayoutInflater.from(getContext()).inflate(resId, viewLabel, false);
            replaceLabel(viewInner);
        }
    }

    public void replaceLabel(View viewInner) {
        if(viewLabel != null && viewInner != null) {
            viewLabel.removeAllViews();
            viewLabel.addView(viewInner);
        }
    }

    public String getTitle() {
        return rvTitle == null ? "" : rvTitle.getText().toString();
    }

    public String getLabel() {
        return rvLabel == null ? "" : rvLabel.getText().toString();
    }

    public View getViewTitle() {
        return viewTitle;
    }

    public View getViewLabel() {
        return viewLabel;
    }

    public RelativeLayout getViewOption() {
        return viewOption;
    }

    public TextView getTextTitle() {
        return rvTitle;
    }

    public TextView getTextLabel() {
        return rvLabel;
    }

    public ImageView getImageIcon() {
        return rvIcon;
    }

    public ImageView getImageOP() {
        return rvOption;
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