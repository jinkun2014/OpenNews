package me.jinkun.opennews.features.news.model;

import java.io.IOException;
import java.util.Map;

import me.jinkun.common.cache.DiskLruCacheUtil;
import me.jinkun.opennews.base.MyApp;
import me.jinkun.opennews.base.model.AbsModel;
import me.jinkun.opennews.domain.NewsDetail;
import me.jinkun.opennews.network.ApiServiceManager;
import me.jinkun.opennews.network.INewsTopicApi;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2016/1/13.
 */
public class NewsDetailModel extends AbsModel {
    public static NewsDetailModel getInstance() {
        return getInstance(NewsDetailModel.class);
    }

    public NewsDetail loadDetailFromNet(String docid) {
        INewsTopicApi instance = ApiServiceManager.getApiService(INewsTopicApi.class);
        Call<Map<String, NewsDetail>> call = instance.listNewsDetail(docid);
        try {
            Response<Map<String, NewsDetail>> resp = call.execute();
            return resp.body().get(docid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save2Local(String docid, NewsDetail newsDetail) {
        DiskLruCacheUtil.saveObject(MyApp.getInstance(), newsDetail, docid);
    }

    public NewsDetail loadDetailFromLocal(String docid) {
        return (NewsDetail) DiskLruCacheUtil.readObject(MyApp.getInstance(), docid);
    }
}
