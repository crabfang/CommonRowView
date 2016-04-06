package com.cabe.lib.ui.rowview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 垂直布局的操作栏
 * Created by cabe on 16/3/28.
 */
public class VerticalRowView extends AbstractRowView {
    public VerticalRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_custom_row_op_vertical, this);
        viewHead = findViewById(R.id.layout_custom_row_op_head);
        viewHint = (LinearLayout) findViewById(R.id.layout_custom_row_op_hint);

        iconVal = (ImageView) findViewById(R.id.layout_custom_row_op_icon);
        titleVal = (TextView) findViewById(R.id.layout_custom_row_op_head_title);
        point = (ImageView) findViewById(R.id.layout_custom_row_op_head_point);
        hintTips = (TextView) findViewById(R.id.layout_custom_row_op_hint_tips);
        rightImg = (ImageView) findViewById(R.id.layout_custom_row_op_arrow);
    }
}
