package me.jinkun.opennews.data.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import me.jinkun.common.cache.DiskLruCacheUtil;
import me.jinkun.common.utils.L;
import me.jinkun.common.utils.SPUtil;
import me.jinkun.opennews.base.MyApp;
import me.jinkun.opennews.base.model.AbsModel;
import me.jinkun.opennews.data.domain.NewsTopic;
import me.jinkun.opennews.data.network.ApiServiceManager;
import me.jinkun.opennews.data.network.interfs.INewsTopicApi;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2016/1/15.
 */
public class NewsTopicModel extends AbsModel {
    public static NewsTopicModel getInstance() {
        return getInstance(NewsTopicModel.class);
    }

    public List<NewsTopic> loadDataFromNet(String tid, int start, int end) {
        L.e("http://c.m.163.com/nc/article/list/" + tid + "/" + start + "-" + end + ".html");
        try {
            INewsTopicApi mNewsChannelApi = ApiServiceManager.getApiService(INewsTopicApi.class);
            Call<Map<String, List<NewsTopic>>> newsTopicCall = mNewsChannelApi.listTopics(tid, start, end);
            Response<Map<String, List<NewsTopic>>> execute = newsTopicCall.execute();
            Map<String, List<NewsTopic>> body = execute.body();
            List<NewsTopic> newsTopicList = body.get(tid);
            return newsTopicList;
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NewsTopic> loadDataFromLocal(String tid, int start, int end) {
        return (List<NewsTopic>) DiskLruCacheUtil.readObject(MyApp.getInstance(), tid);
    }


    public boolean isRead(NewsTopic newsTopic) {
        return (boolean) SPUtil.get(MyApp.getInstance(), newsTopic.getDocid(), false);
    }

    public void setIsRead(NewsTopic newsTopic) {
        SPUtil.put(MyApp.getInstance(), newsTopic.getDocid(), true);
    }
}
