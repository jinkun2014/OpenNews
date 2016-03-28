package me.jinkun.opennews.base.fragment;

import android.os.Bundle;
import android.view.View;

import me.jinkun.opennews.base.presenter.AbsBasePresenter;


/**
 * Description: Do one thing at a time, and do well.
 * Created by jinkun on 2015/11/19.
 */
public abstract class MVPBaseFragment<V, T extends AbsBasePresenter<V>> extends BaseFragment {
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

    protected abstract int getLayoutId();

    protected abstract T createPresenter();

    protected void initView(View view) {
    }

    protected abstract void initData(Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

}
