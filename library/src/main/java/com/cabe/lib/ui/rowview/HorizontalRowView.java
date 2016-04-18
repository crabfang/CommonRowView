package com.cabe.lib.ui.rowview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 水平布局的操作栏
 * Created by cabe on 16/3/28.
 */
public class HorizontalRowView extends AbstractRowView {
    public HorizontalRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_custom_row_op_horizontal, this);
        viewHead = findViewById(R.id.layout_custom_row_op_head);
        viewHint = (LinearLayout) findViewById(R.id.layout_custom_row_op_hint);

        rvIcon = (ImageView) findViewById(R.id.layout_custom_row_op_icon);
        rvTitle = (TextView) findViewById(R.id.layout_custom_row_op_head_title);
        rvFlag = (ImageView) findViewById(R.id.layout_custom_row_op_head_point);
        rvHint = (TextView) findViewById(R.id.layout_custom_row_op_hint_tips);
        rvOption = (ImageView) findViewById(R.id.layout_custom_row_op_arrow);
    }

    @Override
    protected void setInnerMargin(int margin) {
        if(viewHint != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewHint.getLayoutParams();
            params.leftMargin = margin;
            viewHint.setLayoutParams(params);
        }
    }
}
