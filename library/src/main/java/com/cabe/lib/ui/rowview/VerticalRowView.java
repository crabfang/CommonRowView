package com.cabe.lib.ui.rowview;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;

/**
 * 垂直布局的操作栏
 * Created by cabe on 16/3/28.
 */
public class VerticalRowView extends AbstractRowView {

    public VerticalRowView(Context context) {
        super(context);
    }

    public VerticalRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
        protected void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_custom_row_op_vertical, this);
        viewTitle = findViewById(R.id.layout_custom_row_v_view_head);
        viewLabel = findViewById(R.id.layout_custom_row_v_view_label);
        viewOption = findViewById(R.id.layout_custom_row_v_view_option);

        rvIcon = findViewById(R.id.layout_custom_row_v_icon);
        rvTitle = findViewById(R.id.layout_custom_row_v_head_title);
        rvFlag = findViewById(R.id.layout_custom_row_v_head_point);
        rvLabel = findViewById(R.id.layout_custom_row_v_label_tips);
        rvOption = findViewById(R.id.layout_custom_row_v_op_arrow);
    }

    @Override
    public void setLabelMargin(int margin) {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) viewLabel.getLayoutParams();
        params.topMargin = margin;
        viewLabel.setLayoutParams(params);
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
