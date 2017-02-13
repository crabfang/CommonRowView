package com.cabe.lib.ui.rowview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Tips布局的操作栏
 * Created by cabe on 16/3/28.
 */
public class TipsRowView extends AbstractRowView {
    private final static int DEFAULT_TIPS_SIZE = 12;
    private final static int DEFAULT_TIPS_COLOR = 0xFF999999;

    private View viewTips;
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TipsRowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
        protected void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_custom_row_op_tips, this);
        viewTitle = findViewById(R.id.layout_custom_row_t_view_title);
        viewHint = (RelativeLayout) findViewById(R.id.layout_custom_row_t_view_hint);

        rvIcon = (ImageView) findViewById(R.id.layout_custom_row_t_icon);
        rvTitle = (TextView) findViewById(R.id.layout_custom_row_t_head_title);
        rvFlag = (ImageView) findViewById(R.id.layout_custom_row_t_head_point);
        rvHint = (TextView) findViewById(R.id.layout_custom_row_t_hint_tips);

        viewTips = findViewById(R.id.layout_custom_row_t_view_tips);
        rvTips1 = (TextView) findViewById(R.id.layout_custom_row_t_tips_info);
        rvTips2 = (TextView) findViewById(R.id.layout_custom_row_t_tips_info_second);
    }

    @Override
    protected void initAttr(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super.initAttr(context, attrs, defStyleAttr, defStyleRes);
        showHint(true);

        float density = context.getResources().getDisplayMetrics().density;
        int defaultSize = (int) (density * DEFAULT_TIPS_SIZE);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LayoutRowViewNormal, defStyleAttr, defStyleRes);

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

        a.recycle();
    }

    @Override
    public void setHintMargin(int margin) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewHint.getLayoutParams();
        params.topMargin = margin;
        viewHint.setLayoutParams(params);
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

    @Override
    protected int getTitleDefaultGravity() {
        return Gravity.Left.getVal();
    }

    @Override
    protected int getHintDefaultGravity() {
        return Gravity.Left.getVal();
    }
}
