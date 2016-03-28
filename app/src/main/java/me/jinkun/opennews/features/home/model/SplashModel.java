package me.jinkun.opennews.features.home.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import me.jinkun.opennews.base.MyApp;
import me.jinkun.opennews.base.model.AbsModel;
import me.jinkun.opennews.database.dao.DaoManager;
import me.jinkun.opennews.database.dao.DaoSession;
import me.jinkun.opennews.database.dao.NewsChannelDao;
import me.jinkun.opennews.features.news.bean.NewsChannel;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2016/1/10.
 */
public class SplashModel extends AbsModel {

    public static SplashModel getInstance() {
        return getInstance(SplashModel.class);
    }

    public void initNewsChennels() {
        DaoSession daoSession = DaoManager.newSession();
        NewsChannelDao newsChannelDao = daoSession.getNewsChannelDao();

        //如果已有频道的数据，则不必初始化
        List<NewsChannel> newsChannels = newsChannelDao.loadAll();
        if (newsChannels == null || newsChannels.size() == 0) {
            //初始化频道数据
            try {
                InputStream is = MyApp.getInstance().getAssets().open("json/newschannels.json");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line = "";
                StringBuffer sb = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                String channels = sb.toString();

                Gson gson = new Gson();
                List<NewsChannel> newsChannelList = gson.fromJson(channels, new TypeToken<List<NewsChannel>>() {}.getType());

                for (NewsChannel channel : newsChannelList) {
                    newsChannelDao.insert(channel);
                }
                daoSession.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
