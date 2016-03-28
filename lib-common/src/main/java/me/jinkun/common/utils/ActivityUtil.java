package me.jinkun.common.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;

/**
 * Description: Activity跳转辅助类 <br/>
 * Created by jinkun on 2016/3/8.
 */
public class ActivityUtil {

    /**
     * Description: 跳转Activity <br/>
     * Autor: Created by jinkun on 2016/3/8.
     */
    public static void goActivity(Context context, Class clazz) {
        goActivity(context, clazz, null);
    }

    /**
     * Description: 跳转Activity带数据 <br/>
     * Autor: Created by jinkun on 2016/3/8.
     */
    public static void goActivity(Context context, Class cla, Bundle bundle) {
        Intent intent = new Intent(context, cla);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * Description: 跳转Activity带Serializable对象 <br/>
     * Autor: Created by jinkun on 2016/3/16.
     */
    public static void goActivity(Context context, Class cla, String key, Serializable o) {
        Intent intent = new Intent(context, cla);
        intent.putExtra(key, o);
        context.startActivity(intent);
    }
}
