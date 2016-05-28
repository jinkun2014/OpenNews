package me.jinkun.opennews.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import me.jinkun.opennews.database.DBUtil;
import me.jinkun.opennews.features.news.bean.NewsChannel;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2015/12/1.
 */
public class NewsChannelDao {
    public static final String TABLE_NAME = "newschannel";

    //        "isMe": 0,
    //        "orderNum": 0,
    //        "tid": "T1370583240249",
    //        "tname": "原创",
    //        "topicid": "00040BGE"
    public static final String TABLE_COLUMN_ID = "id";
    public static final String TABLE_COLUMN_ISME = "isMe";
    public static final String TABLE_COLUMN_ORDERNUM = "orderNum";
    public static final String TABLE_COLUMN_TID = "tid";
    public static final String TABLE_COLUMN_TNAME = "tname";
    public static final String TABLE_COLUMN_TOPICID = "topicid";//

    public static final String CREATE_TABLE = //
            "create table if not exists " + TABLE_NAME + "(" +
                    TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TABLE_COLUMN_TOPICID + " TEXT , " +
                    TABLE_COLUMN_TNAME + " TEXT , " +
                    TABLE_COLUMN_TID + " TEXT , " +
                    TABLE_COLUMN_ORDERNUM + " INTEGER , " +
                    TABLE_COLUMN_ISME + " INTEGER)";


    private static NewsChannelDao mNewsChannelDao;

    private NewsChannelDao() {
    }

    public static NewsChannelDao getInstance() {
        if (mNewsChannelDao == null) {
            mNewsChannelDao = new NewsChannelDao();
        }
        return mNewsChannelDao;
    }

    public List<NewsChannel> findAll() {
        List<NewsChannel> newsChannelList = new ArrayList<NewsChannel>();
        Cursor cursor = DBUtil.getReadableDatabase().rawQuery("select * from " + TABLE_NAME, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                NewsChannel channel = new NewsChannel();
                channel.setId(cursor.getLong(cursor.getColumnIndex(TABLE_COLUMN_ID)));
                channel.setIsSelected(cursor.getInt(cursor.getColumnIndex(TABLE_COLUMN_ISME)));
                channel.setTid(cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_TID)));
                channel.setOrderNum(cursor.getInt(cursor.getColumnIndex(TABLE_COLUMN_ORDERNUM)));
                channel.setTname(cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_TNAME)));
                channel.setTopicid(cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_TOPICID)));
                newsChannelList.add(channel);
            }
            cursor.close();
        }
        return newsChannelList;
    }

    public boolean save(NewsChannel channel) {
        SQLiteDatabase db = DBUtil.getWritableDatabase();
        db.execSQL("insert into ");
        return false;
    }
}
