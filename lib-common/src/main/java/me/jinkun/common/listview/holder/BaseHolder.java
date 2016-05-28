package me.jinkun.common.listview.holder;

import android.content.Context;
import android.view.View;

public abstract class BaseHolder<T> {
    private static final String TAG = "BaseHolder";
    private View mView;
    private T mData;
    protected Context mContext;

    public BaseHolder(Context context) {
        mContext = context;
        //将ListView一个条目的布局转换成一个view对象,xml-->view
        mView = initView();
        //给view设置一个tag的操作,等同于convertView.setTag()
        mView.setTag(this);
    }

    /**
     * Description: 设置数据并刷新View <br/>
     * Autor: Created by jinkun on 2015/12/27.
     */
    public void setData(T data) {
        this.mData = data;
        refreshView();
    }

    /**
     * Description: setData设置进来什么数据,返回什么数据 <br/>
     * Autor: Created by jinkun on 2015/12/27.
     */
    public T getData() {
        return mData;
    }

    /**
     * Description: 返回holder构造方法中xml转换成的view对象操作 <br/>
     * Autor: Created by jinkun on 2015/12/27.
     */
    public View getRootView() {
        return mView;
    }

    /**
     * Description: 初始化Item的布局 <br/>
     * Autor: Created by jinkun on 2015/12/27.
     */
    public abstract View initView();

    /**
     * Description: 绑定数据到相应的控件上 <br/>
     * Autor: Created by jinkun on 2015/12/27.
     */
    public abstract void refreshView();
}
