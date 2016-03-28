package me.jinkun.opennews.database.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2016/1/10.
 */
public class DaoManager {
    private static DaoMaster daoMaster;

    public static DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public static void initGreenDao(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "wynews-db.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
    }

    public static DaoSession newSession() {
        return daoMaster.newSession();
    }
}
