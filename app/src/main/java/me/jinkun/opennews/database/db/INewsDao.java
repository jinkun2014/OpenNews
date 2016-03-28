package me.jinkun.opennews.database.db;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2015/12/1.
 */
public interface INewsDao {
    public static class NewsChannelDao {
        public static final String ID = "id";
        public static final String TABLE_NAME = "newschannel";
        public static final String TOPICID = "topicid";//
        public static final String TNAME = "tname";
        public static final String TID = "tid";
        public static final String ORDERNUM = "orderNum";
        public static final String ISME = "isMe";

        public static final String CREATE_TABLE = //
                "create table if not exists " + TABLE_NAME + "(" +
                        ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        TOPICID + " TEXT , " +
                        TNAME + " TEXT , " +
                        TID + " TEXT , " +
                        ORDERNUM + " INTEGER , " +
                        ISME + " INTEGER)";
    }
}
