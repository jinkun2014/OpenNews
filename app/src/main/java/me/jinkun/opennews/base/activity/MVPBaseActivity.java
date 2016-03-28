package me.jinkun.opennews.base.activity;

import android.os.Bundle;
import android.view.View;

import me.jinkun.opennews.base.presenter.AbsBasePresenter;


/**
 * Description: MVPBaseActivity 实现 MVP 模式<br/>
 * 其中Presenter使用若引用关联View<br/>
 * 子类继承即可<br/>
 * Created by jinkun on 2015/11/19.
 */
public abstract class MVPBaseActivity<V, T extends AbsBasePresenter<V>> extends BaseActivity {
    //子类可以直接使用
    protected T mPresenter;

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        //初始化布局
        initView(view);

        //初始化业务类
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);

        //初始化数据
        initData(savedInstanceState);
    }

    protected void onBeforeLayout() {
    }

    protected abstract int getLayoutId();

    /**
     * Description: 初始化View ,有了ButterKnife其实不是必须的 <br/>
     * Autor: Created by jinkun on 2015/12/5.
     */
    protected void initView(View view) {
    }

    /**
     * Description: 创建Presenter <br/>
     * Autor: Created by jinkun on 2015/12/1.
     */
    protected abstract T createPresenter();

    /**
     * Description: 初始化数据 <br/>
     * Autor: Created by jinkun on 2015/12/5.
     */
    protected abstract void initData(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
