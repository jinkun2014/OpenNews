package me.jinkun.opennews.features.home;

import android.content.Intent;
import android.os.Bundle;

import me.jinkun.opennews.R;
import me.jinkun.opennews.base.MyApp;
import me.jinkun.opennews.base.activity.MVPBaseActivity;
import me.jinkun.opennews.features.home.presenter.SplashPresenter;
import me.jinkun.opennews.features.home.view.ISplashView;


/**
 * Description: Do one thing at a time, and do well. <br/>
 * Autor: Created by jinkun on 2015/11/30.
 */
public class SplashActivity extends MVPBaseActivity<ISplashView, SplashPresenter> implements ISplashView {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter.initData();
    }

    @Override
    public void onInitFinish() {
        MyApp.getInstance().getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
