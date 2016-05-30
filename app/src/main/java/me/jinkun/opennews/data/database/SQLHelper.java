package me.jinkun.opennews.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.jinkun.opennews.data.database.dao.NewsChannelDao;

public class SQLHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "database.db";// 数据库名称
    public static final int VERSION = 1;

    private Context context;

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO 创建数据库后，对数据库的操作
        db.execSQL(NewsChannelDao.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO 更改数据库版本的操作
        switch (oldVersion) {
            case 1:
            case 2:
            case 3:
            default:
                break;
        }
    }
}
