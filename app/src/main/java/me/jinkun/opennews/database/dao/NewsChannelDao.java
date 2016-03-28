package me.jinkun.opennews.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import me.jinkun.opennews.features.news.bean.NewsChannel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NEWS_CHANNEL".
*/
public class NewsChannelDao extends AbstractDao<NewsChannel, Long> {

    public static final String TABLENAME = "NEWS_CHANNEL";

    /**
     * Properties of entity NewsChannel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Topicid = new Property(1, String.class, "topicid", false, "TOPICID");
        public final static Property Tname = new Property(2, String.class, "tname", false, "TNAME");
        public final static Property Tid = new Property(3, String.class, "tid", false, "TID");
        public final static Property IsSelected = new Property(4, Integer.class, "isSelected", false, "IS_SELECTED");
        public final static Property OrderNum = new Property(5, Integer.class, "orderNum", false, "ORDER_NUM");
    };


    public NewsChannelDao(DaoConfig config) {
        super(config);
    }
    
    public NewsChannelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NEWS_CHANNEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"TOPICID\" TEXT," + // 1: topicid
                "\"TNAME\" TEXT," + // 2: tname
                "\"TID\" TEXT," + // 3: tid
                "\"IS_SELECTED\" INTEGER," + // 4: isSelected
                "\"ORDER_NUM\" INTEGER);"); // 5: orderNum
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NEWS_CHANNEL\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, NewsChannel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String topicid = entity.getTopicid();
        if (topicid != null) {
            stmt.bindString(2, topicid);
        }
 
        String tname = entity.getTname();
        if (tname != null) {
            stmt.bindString(3, tname);
        }
 
        String tid = entity.getTid();
        if (tid != null) {
            stmt.bindString(4, tid);
        }
 
        Integer isSelected = entity.getIsSelected();
        if (isSelected != null) {
            stmt.bindLong(5, isSelected);
        }
 
        Integer orderNum = entity.getOrderNum();
        if (orderNum != null) {
            stmt.bindLong(6, orderNum);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public NewsChannel readEntity(Cursor cursor, int offset) {
        NewsChannel entity = new NewsChannel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // topicid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // tname
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // tid
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // isSelected
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5) // orderNum
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, NewsChannel entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTopicid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTname(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTid(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setIsSelected(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setOrderNum(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(NewsChannel entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(NewsChannel entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
