package com.cabe.lib.ui.rowview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Tips布局的操作栏
 * Created by cabe on 16/3/28.
 */
public class TipsRowView extends AbstractRowView {
    private final static int DEFAULT_TIPS_SIZE = 12;
    private final static int DEFAULT_TIPS_COLOR = 0xFF999999;

    private LinearLayout viewTips;
    private TextView rvTips1;
    private TextView rvTips2;

    public TipsRowView(Context context) {
        super(context);
    }

    public TipsRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TipsRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initDefaultConfig() {
        super.initDefaultConfig();
        DEFAULT_LABEL_LINE = 1;
    }

    @Override
    protected void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_custom_row_op_tips, this);
        viewTitle = findViewById(R.id.layout_custom_row_t_view_title);
        viewLabel = findViewById(R.id.layout_custom_row_t_view_label);

        rvIcon = findViewById(R.id.layout_custom_row_t_icon);
        rvTitle = findViewById(R.id.layout_custom_row_t_head_title);
        rvFlag = findViewById(R.id.layout_custom_row_t_head_point);
        rvLabel = findViewById(R.id.layout_custom_row_t_label_tips);

        viewTips = findViewById(R.id.layout_custom_row_t_view_tips);
        rvTips1 = findViewById(R.id.layout_custom_row_t_tips_info);
        rvTips2 = findViewById(R.id.layout_custom_row_t_tips_info_second);
    }

    @Override
    protected void initAttr(Context context, AttributeSet attrs, int defStyleAttr) {
        super.initAttr(context, attrs, defStyleAttr);
        showLabel(true);

        float density = context.getResources().getDisplayMetrics().density;
        int defaultSize = (int) (density * DEFAULT_TIPS_SIZE);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LayoutRowViewNormal, defStyleAttr, 0);

        setTipsSize1(TypedValue.COMPLEX_UNIT_PX, a.getDimension(R.styleable.LayoutRowViewNormal_rv_tipsSize_1, defaultSize));
        setTipsColor1(a.getColor(R.styleable.LayoutRowViewNormal_rv_tipsColor_1, DEFAULT_TIPS_COLOR));
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_tips_1)) {
            setTipsValue1(a.getText(R.styleable.LayoutRowViewNormal_rv_tips_1));
        } else {
            setTipsValue1("");
        }

        setTipsSize2(TypedValue.COMPLEX_UNIT_PX, a.getDimension(R.styleable.LayoutRowViewNormal_rv_tipsSize_2, defaultSize));
        setTipsColor2(a.getColor(R.styleable.LayoutRowViewNormal_rv_tipsColor_2, DEFAULT_TIPS_COLOR));
        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_tips_2)) {
            setTipsValue2(a.getText(R.styleable.LayoutRowViewNormal_rv_tips_2));
        } else {
            setTipsValue2("");
        }

        if(a.hasValue(R.styleable.LayoutRowViewNormal_rv_tipsLayout)) {
            int resId = a.getResourceId(R.styleable.LayoutRowViewNormal_rv_tipsLayout, 0);
            if(resId > 0) {
                replaceTips(resId);
            }
        }

        a.recycle();
    }

    @Override
    public void setLabelMargin(int margin) {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) viewLabel.getLayoutParams();
        params.topMargin = margin;
        viewLabel.setLayoutParams(params);
    }

    public void setTipsColor1(int color) {
        if(rvTips1 == null) return;

        rvTips1.setTextColor(color);
    }

    public void setTipsSize1(int unit, float size) {
        if(rvTips1 == null) return;

        rvTips1.setTextSize(unit, size);
    }

    public void setTipsValue1(CharSequence tips) {
        if(rvTips1 == null) return;

        rvTips1.setText(tips);
    }

    public void setTipsColor2(int color) {
        if(rvTips2 == null) return;

        rvTips2.setTextColor(color);
    }

    public void setTipsSize2(int unit, float size) {
        if(rvTips2 == null) return;

        rvTips2.setTextSize(unit, size);
    }

    public void setTipsValue2(CharSequence tips) {
        if(rvTips2 == null) return;

        rvTips2.setText(tips);
        showTipsValue2(!TextUtils.isEmpty(tips));
    }

    public void showTipsValue2(boolean show) {
        if(rvTips2 == null) return;

        rvTips2.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showTipsView(boolean show) {
        if(viewTips == null) return;

        viewTips.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void replaceTips(int resId) {
        if(viewLabel != null && resId > 0) {
            View viewInner = LayoutInflater.from(getContext()).inflate(resId, viewLabel, false);
            replaceTips(viewInner);
        }
    }

    public void replaceTips(View viewInner) {
        if(viewTips != null && viewInner != null) {
            viewTips.removeAllViews();
            viewTips.addView(viewInner);
        }
    }

    public LinearLayout getTipsView() {
        return viewTips;
    }

    @Override
    protected int getTitleDefaultGravity() {
        return Gravity.Left.getVal();
    }

    @Override
    protected int getLabelDefaultGravity() {
        return Gravity.Left.getVal();
    }
}
