package com.cabe.lib.ui.rowview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public VerticalRowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
        protected void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_custom_row_op_vertical, this);
        viewHead = findViewById(R.id.layout_custom_row_v_view_head);
        viewOption = (RelativeLayout) findViewById(R.id.layout_custom_row_v_view_option);

        rvIcon = (ImageView) findViewById(R.id.layout_custom_row_v_icon);
        rvTitle = (TextView) findViewById(R.id.layout_custom_row_v_head_title);
        rvFlag = (ImageView) findViewById(R.id.layout_custom_row_v_head_point);
        rvHint = (TextView) findViewById(R.id.layout_custom_row_v_hint_tips);
        rvOption = (ImageView) findViewById(R.id.layout_custom_row_v_op_arrow);
    }
}
