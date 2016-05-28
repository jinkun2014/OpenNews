package me.jinkun.opennews.features.news.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import me.jinkun.common.cache.DiskLruCacheUtil;
import me.jinkun.common.utils.L;
import me.jinkun.opennews.base.MyApp;
import me.jinkun.opennews.base.model.AbsModel;
import me.jinkun.opennews.database.dao.DaoManager;
import me.jinkun.opennews.database.dao.DaoSession;
import me.jinkun.opennews.database.dao.NewsReadDao;
import me.jinkun.opennews.domain.NewsTopic;
import me.jinkun.opennews.features.news.bean.NewsRead;
import me.jinkun.opennews.network.ApiServiceManager;
import me.jinkun.opennews.network.INewsTopicApi;
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


    public NewsRead getByTopicId(String id) {
        DaoSession daoSession = DaoManager.newSession();
        NewsReadDao mNewsReadDao = daoSession.getNewsReadDao();
        NewsRead newsRead = mNewsReadDao.queryBuilder().where(NewsReadDao.Properties.Docid.eq(id)).unique();
        return newsRead;
    }

    public void saveNewsRead(NewsRead newsRead) {
        DaoSession daoSession = DaoManager.newSession();
        NewsReadDao mNewsReadDao = daoSession.getNewsReadDao();
        mNewsReadDao.insert(newsRead);
    }

}
