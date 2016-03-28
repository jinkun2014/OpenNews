package me.jinkun.opennews.widgets;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import me.jinkun.opennews.R;

/**
 * Description: 加载等待对话框.<br/>
 * Created by jinkun on 2015/12/1.
 */
public class LoadingDialog extends Dialog {
    public LoadingDialog(Context context) {
        this(context, R.style.dialog_loading);
        View contentView = getLayoutInflater().inflate(R.layout.dialog_loading, null);
        super.setContentView(contentView);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
