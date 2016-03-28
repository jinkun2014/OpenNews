package me.jinkun.opennews.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import me.jinkun.opennews.features.news.bean.NewsRead;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NEWS_READ".
*/
public class NewsReadDao extends AbstractDao<NewsRead, Long> {

    public static final String TABLENAME = "NEWS_READ";

    /**
     * Properties of entity NewsRead.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Docid = new Property(1, String.class, "docid", false, "DOCID");
        public final static Property IsRead = new Property(2, Integer.class, "isRead", false, "IS_READ");
    };


    public NewsReadDao(DaoConfig config) {
        super(config);
    }
    
    public NewsReadDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NEWS_READ\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"DOCID\" TEXT," + // 1: docid
                "\"IS_READ\" INTEGER);"); // 2: isRead
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NEWS_READ\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, NewsRead entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String docid = entity.getDocid();
        if (docid != null) {
            stmt.bindString(2, docid);
        }
 
        Integer isRead = entity.getIsRead();
        if (isRead != null) {
            stmt.bindLong(3, isRead);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public NewsRead readEntity(Cursor cursor, int offset) {
        NewsRead entity = new NewsRead( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // docid
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2) // isRead
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, NewsRead entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDocid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setIsRead(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(NewsRead entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(NewsRead entity) {
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
