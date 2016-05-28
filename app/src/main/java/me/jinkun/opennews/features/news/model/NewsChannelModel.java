package me.jinkun.opennews.features.news.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import me.jinkun.common.utils.SPUtil;
import me.jinkun.opennews.base.MyApp;
import me.jinkun.opennews.base.model.AbsModel;
import me.jinkun.opennews.constant.C;
import me.jinkun.opennews.features.news.bean.NewsChannel;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2016/1/10.
 */
public class NewsChannelModel extends AbsModel {

    public static NewsChannelModel getInstance() {
        return getInstance(NewsChannelModel.class);
    }

    public List<NewsChannel> loadChannelsFromLocal() {
        String newsChannesStr = (String) SPUtil.get(MyApp.getInstance(), C.Key.NEWSCHANNES, "");
        if (!"".equals(newsChannesStr)) {
            return fromStr(newsChannesStr);
        }
        return null;
    }

    public List<NewsChannel> loadChannelsFromAssets() {
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
            return fromStr(channels);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveChannelsToLocal(List<NewsChannel> newsChannelList) {
        String str = toStr(newsChannelList);
        SPUtil.put(MyApp.getInstance(), C.Key.NEWSCHANNES, str);
    }

    private List<NewsChannel> fromStr(String str) {
        Gson gson = new Gson();
        List<NewsChannel> newsChannelList = gson.fromJson(str, new TypeToken<List<NewsChannel>>() {
        }.getType());
        return newsChannelList;
    }

    private String toStr(List<NewsChannel> newsChannelList) {
        Gson gson = new Gson();
        return gson.toJson(newsChannelList);
    }
}
