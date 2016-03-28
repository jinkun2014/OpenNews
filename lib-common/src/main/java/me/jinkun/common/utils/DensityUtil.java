package me.jinkun.common.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Description: 常用单位转换的辅助类
 * Autor: Created by jinkun on 2015/9/2.
 */
public class DensityUtil {
    private DensityUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * Description: dp转px <br/>
     * Autor: Created by jinkun on 2016/3/8.
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * Description: sp转px <br/>
     * Autor: Created by jinkun on 2016/3/8.
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * Description: px转dp <br/>
     * Autor: Created by jinkun on 2016/3/8.
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * Description: px转sp <br/>
     * Autor: Created by jinkun on 2016/3/8.
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

}

