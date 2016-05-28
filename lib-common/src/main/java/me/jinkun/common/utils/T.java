package me.jinkun.common.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Description: Toast统一管理类
 * Autor: Created by jinkun on 2015/9/2.
 */
public class T {

    public static boolean isShow = true;
    private static Toast toast = null;

    private T() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static void cancelToast() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void s(Context context, CharSequence message) {
        cancelToast();
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void l(Context context, CharSequence message) {
        cancelToast();
        toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }


}
