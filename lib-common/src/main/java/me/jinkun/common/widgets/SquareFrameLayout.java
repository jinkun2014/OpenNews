package me.jinkun.common.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import me.jinkun.common.R;

/**
 * Created by nereo on 15/11/10.
 */
public class SquareFrameLayout extends FrameLayout {
    private float ratio = 1f;

    public SquareFrameLayout(Context context) {
        this(context, null);
    }

    public SquareFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SquareFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SquareFrameLayout);
            ratio = a.getFloat(R.styleable.SquareFrameLayout_sfl_ratio, 1f);
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), (int) (getMeasuredWidth() * ratio));
    }
}
