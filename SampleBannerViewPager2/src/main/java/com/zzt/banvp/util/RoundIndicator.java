package com.zzt.banvp.util;

import static com.zzt.utilcode.util.SizeUtils.dp2px;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.annotation.Nullable;


/**
 * @author: zeting
 * @date: 2021/3/11
 * 下方圆角矩形指示器
 */
public class RoundIndicator extends BaseIndicator {
    private static final String TAG = RoundIndicator.class.getSimpleName();
    private int mNormalRadius;
    private RectF mRect = new RectF();

    public RoundIndicator(Context context) {
        super(context, null);
    }

    public RoundIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public RoundIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        config.setNormalWidth(dp2px(16));
        mNormalRadius = config.getSelectedHeight() / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = config.getIndicatorSize();
        if (count <= 1) {
            return;
        }
        mNormalRadius = config.getSelectedHeight() / 2;
        //间距*（总数-1） 默认宽度* 总数
        int width = (count - 1) * config.getIndicatorSpace() + config.getNormalWidth() * count;
        setMeasuredDimension(width, config.getSelectedHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = config.getIndicatorSize();
        if (count <= 1) {
            return;
        }
        float left = 0;
        for (int i = 0; i < count; i++) {
            mPaint.setColor(config.getCurrentPosition() == i ? config.getSelectedColor() : config.getNormalColor());
            mRect.set(left, 0, left + config.getNormalWidth(), config.getSelectedHeight());
            //绘制指示器
            canvas.drawRoundRect(mRect, mNormalRadius, mNormalRadius, mPaint);
            left += config.getNormalWidth() + config.getIndicatorSpace();
        }
    }
}
