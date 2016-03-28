package me.jinkun.opennews.base;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;

import me.jinkun.common.utils.SPUtil;
import me.jinkun.opennews.base.activity.BaseActivity;
import me.jinkun.opennews.database.dao.DaoManager;

/**
 * Created by jinkun on 2015/8/28.
 */
public class MyApp extends Application {
    private static MyApp instance;
    private BaseActivity currActivity;
    private List<BaseActivity> activityList = new ArrayList<BaseActivity>();

    public MyApp() {
        instance = this;
    }

    public static MyApp getInstance() {
        return instance;
    }

    public void addActivity(BaseActivity activity) {
        activityList.add(activity);
    }

    public void removeActivity(BaseActivity activity) {
        activityList.remove(activity);
    }

    public BaseActivity getCurrActivity() {
        return currActivity;
    }

    public void setCurrActivity(BaseActivity activity) {
        this.currActivity = activity;
    }

    public void finishAllActivity() {
        for (BaseActivity baseActivity : activityList) {
            baseActivity.finish();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    private Handler mHandler;

    public Handler getHandler() {
        return mHandler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initApp();

        //初始化GreenDao
        DaoManager.initGreenDao(this);
    }

    private void initApp() {
        SPUtil.context = this;

        mHandler = new Handler(Looper.getMainLooper());
    }
}
