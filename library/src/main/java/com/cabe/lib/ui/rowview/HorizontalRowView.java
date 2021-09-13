package com.cabe.lib.ui.rowview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

/**
 * 水平布局的操作栏
 * Created by cabe on 16/3/28.
 */
public class HorizontalRowView extends AbstractRowView {

    public HorizontalRowView(Context context) {
        super(context);
    }

    public HorizontalRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_custom_row_op_horizontal, this);
        viewTitle = findViewById(R.id.layout_custom_row_h_view_title);
        viewHint = findViewById(R.id.layout_custom_row_h_view_hint);
        viewOption = findViewById(R.id.layout_custom_row_h_view_option);

        rvIcon = findViewById(R.id.layout_custom_row_h_icon);
        rvTitle = findViewById(R.id.layout_custom_row_h_head_title);
        rvFlag = findViewById(R.id.layout_custom_row_h_head_point);
        rvHint = findViewById(R.id.layout_custom_row_h_hint_tips);
        rvOption = findViewById(R.id.layout_custom_row_h_op_arrow);
    }

    @Override
    protected int getTitleDefaultGravity() {
        return Gravity.Right.getVal();
    }

    @Override
    protected int getHintDefaultGravity() {
        return Gravity.Right.getVal();
    }

    @Override
    public void setHintMargin(int margin) {
        ConstraintLayout.LayoutParams params = (LayoutParams) viewHint.getLayoutParams();
        params.leftMargin = margin;
        viewHint.setLayoutParams(params);
    }
}
