package me.jinkun.opennews.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import me.jinkun.opennews.base.MyApp;


/**
 * Description: Do one thing at a time, and do well.
 * Created by jinkun on 2015/11/15.
 */
public class DBUtil {
    private static DBUtil mInstance;
    private Context mContext;
    private SQLHelper mSQLHelp;

    private DBUtil(Context context) {
        mContext = context;
        mSQLHelp = new SQLHelper(context);
    }

    /**
     * 初始化数据库操作DBUtil类
     */
    private static DBUtil getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DBUtil(context);
        }
        return mInstance;
    }

    public static SQLiteDatabase getWritableDatabase() {
        return getInstance(MyApp.getInstance()).mSQLHelp.getWritableDatabase();
    }

    public static SQLiteDatabase getReadableDatabase() {
        return getInstance(MyApp.getInstance()).mSQLHelp.getReadableDatabase();
    }
}
