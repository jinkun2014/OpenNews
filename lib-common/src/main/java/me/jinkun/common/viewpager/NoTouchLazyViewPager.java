package me.jinkun.common.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Description: Do one thing at a time, and do well.
 * Created by jinkun on 2015/11/21.
 */
public class NoTouchLazyViewPager extends LazyViewPager {
    public NoTouchLazyViewPager(Context context) {
        super(context);
    }

    public NoTouchLazyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
