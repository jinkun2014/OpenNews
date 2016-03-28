package me.jinkun.common.widgets;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

/**
 * Description: Do one thing at a time, and do well.</br>
 * Autor: Created by jinkun on 2016/3/28.
 */
public class CusSwipeRefreshLayout extends SwipeRefreshLayout {
    public CusSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public CusSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
    }
}
