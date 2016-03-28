package me.jinkun.opennews.features.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import me.jinkun.common.utils.T;
import me.jinkun.opennews.R;
import me.jinkun.opennews.base.MyApp;
import me.jinkun.opennews.base.activity.BaseActivity;
import me.jinkun.opennews.features.news.NewsFragment;

/**
 * Description: 主界面 <br/>
 * Autor: Created by jinkun on 2015/11/30.
 */
public class HomeActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        //新闻模块的Fragment
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.fl_Container, new NewsFragment());
        tx.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search://开启搜索Activity
                startActivity(new Intent(mActivity, SearchActivity.class));
                break;
            case R.id.action_about:
                startActivity(new Intent(mActivity, AboutActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Description: 双击退出程序 <br/>
     * Autor: Created by jinkun on 2016/3/27.
     */
    private long firstTime;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 800) {//如果两次按键时间间隔大于800毫秒，则不退出
                T.s(this, "再按一次退出程序");
                firstTime = secondTime;//更新firstTime
                return true;
            } else {
                MyApp.getInstance().finishAllActivity();//否则退出程序
            }
        }
        return super.onKeyUp(keyCode, event);
    }
}
