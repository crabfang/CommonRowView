package com.cabe.lib.ui.line;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.cabe.lib.ui.rowview.R;

/**
 * 操作栏抽象类
 * Created by cabe on 16/3/28.
 */
public class DashLineView extends View {
    private int DEFAULT_GAP_COLOR = 0x00999999;
    private int DEFAULT_DASH_COLOR = 0xFF999999;
    private int DEFAULT_GAP_WIDTH = 0;
    private int DEFAULT_DASH_WIDTH = 0;

    private int gapColor;
    private int dashColor;
    private int gapWidth;
    private int dashWidth;
    private boolean isSolid;
    private Direct direct;

    private Paint paint = new Paint();

    public DashLineView(Context context) {
        this(context, null);
    }

    public DashLineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initDefaultConfig(context);
        initAttr(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DashLineView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        initDefaultConfig(context);
        initAttr(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initDefaultConfig(Context context) {
        DEFAULT_GAP_COLOR = 0x00999999;
        DEFAULT_DASH_COLOR = 0xFF999999;

        float density = context.getResources().getDisplayMetrics().density;
        DEFAULT_GAP_WIDTH = (int) (density * 2);
        DEFAULT_DASH_WIDTH = (int) (density * 4);
    }

    protected void initAttr(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DashLineView, defStyleAttr, defStyleRes);

        gapColor = a.getColor(R.styleable.DashLineView_gl_gapColor, DEFAULT_GAP_COLOR);
        dashColor = a.getColor(R.styleable.DashLineView_gl_dashColor, DEFAULT_DASH_COLOR);
        gapWidth = (int) a.getDimension(R.styleable.DashLineView_gl_gapWidth, DEFAULT_GAP_WIDTH);
        dashWidth = (int) a.getDimension(R.styleable.DashLineView_gl_dashWidth, DEFAULT_DASH_WIDTH);
        isSolid = a.getBoolean(R.styleable.DashLineView_gl_isSolid, false);

        int directVal = a.getInteger(R.styleable.DashLineView_gl_direct, Direct.horizontal.toValue());
        direct = directVal == Direct.horizontal.toValue() ? Direct.horizontal : Direct.vertical;
        a.recycle();

        paint.setAntiAlias(true);
        paint.setColor(dashColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        if(width * height == 0) return;

        switch(direct) {
            case horizontal:
                paint.setStrokeWidth(height);
                if(isSolid) {
                    canvas.drawLine(0, 0, width, 0, paint);
                } else {
                    drawGapH(canvas);
                }
                break;
            case vertical:
                paint.setStrokeWidth(width);
                if(isSolid) {
                    canvas.drawLine(0, 0, 0, height, paint);
                } else {
                    drawGapV(canvas);
                }
                break;
        }
    }

    private void drawGapH(Canvas canvas) {
        int width = getWidth();

        int offset = 0;
        while(offset < width) {
            paint.setColor(dashColor);
            canvas.drawLine(offset, 0, offset + dashWidth, 0, paint);
            offset += dashWidth;

            paint.setColor(gapColor);
            canvas.drawLine(offset, 0, offset + gapWidth, 0, paint);
            offset += gapWidth;
        }
    }

    private void drawGapV(Canvas canvas) {
        int height = getHeight();

        //DashPathEffect的虚线效果某些机型有兼容性问题
        int offset = 0;
        while(offset < height) {
            paint.setColor(dashColor);
            canvas.drawLine(0, offset, 0, offset + dashWidth, paint);
            offset += dashWidth;

            paint.setColor(gapColor);
            canvas.drawLine(0, offset, 0, offset + gapWidth, paint);
            offset += gapWidth;
        }
    }

    public enum Direct {
        horizontal(0),
        vertical(1);
        private int val;
        Direct(int val) {
            this.val = val;
        }
        public int toValue() {
            return val;
        }
    }
}