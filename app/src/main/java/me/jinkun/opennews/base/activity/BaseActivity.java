package me.jinkun.opennews.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Description: Do one thing at a time, and do well.
 * Created by jinkun on 2015/11/30.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏状态栏
        onBeforeLayout();
        View view = getLayoutInflater().inflate(getLayoutId(), null);
        setContentView(view);
        mActivity = this;
        ButterKnife.bind(this);
        //view初始化布局,有了ButterKnife其实不是必须的、savedInstanceState初始化数据
        init(view, savedInstanceState);
    }
    /**
     * Description: 主要做全屏以及主题设置等操作 ，不是必须的<br/>
     * Autor: Created by jinkun on 2015/12/6.
     */
    protected void onBeforeLayout(){}
    /**
     * Description: 初始化布局ID <br/>
     * Autor: Created by jinkun on 2015/11/30.
     */
    protected abstract int getLayoutId();
    /**
     * Description: 初始化，子类可以分开初始化view和savedInstanceState <br/>
     * 例如：initView（View view）和initData（Bundle savedInstanceState）<br/>
     * Autor: Created by jinkun on 2015/12/5.
     */
    protected abstract void init(View view, Bundle savedInstanceState);


}
