package me.jinkun.opennews.features.news.model;

import java.util.List;

import me.jinkun.opennews.base.model.AbsModel;
import me.jinkun.opennews.database.dao.DaoManager;
import me.jinkun.opennews.database.dao.DaoSession;
import me.jinkun.opennews.database.dao.NewsChannelDao;
import me.jinkun.opennews.features.news.bean.NewsChannel;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2016/1/13.
 */
public class NewsModel extends AbsModel {
    public static NewsModel getInstance() {
        return getInstance(NewsModel.class);
    }

    public List<NewsChannel> loadChannelsFromNet() {
        return null;
    }

    public List<NewsChannel> loadChannelsFromLocal() {
        DaoSession daoSession = DaoManager.newSession();
        NewsChannelDao newsChannelDao = daoSession.getNewsChannelDao();
        List<NewsChannel> newsChannels = newsChannelDao.loadAll();
        daoSession.clear();
        return newsChannels;
    }

    public List<NewsChannel> loadChannelsFromAssets() {
        return null;
    }
}
