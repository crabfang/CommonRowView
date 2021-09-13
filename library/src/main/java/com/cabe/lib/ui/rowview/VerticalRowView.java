package com.cabe.lib.ui.rowview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

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
        viewHint = findViewById(R.id.layout_custom_row_v_view_hint);
        viewOption = findViewById(R.id.layout_custom_row_v_view_option);

        rvIcon = findViewById(R.id.layout_custom_row_v_icon);
        rvTitle = findViewById(R.id.layout_custom_row_v_head_title);
        rvFlag = findViewById(R.id.layout_custom_row_v_head_point);
        rvHint = findViewById(R.id.layout_custom_row_v_hint_tips);
        rvOption = findViewById(R.id.layout_custom_row_v_op_arrow);
    }

    @Override
    public void setHintMargin(int margin) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewHint.getLayoutParams();
        params.topMargin = margin;
        viewHint.setLayoutParams(params);
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
