package me.jinkun.opennews.network;

import me.jinkun.opennews.domain.NewsDetail;
import me.jinkun.opennews.domain.NewsTopic;

import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2015/12/21.
 */
public interface INewsTopicApi {
    @GET("nc/article/list/{tid}/{start}-{end}.html")
    Call<Map<String, List<NewsTopic>>> listTopics(@Path("tid") String tid, @Path("start") int start, @Path("end") int end);

    @GET("nc/article/{docid}/full.html")
    Call<Map<String, NewsDetail>> listNewsDetail(@Path("docid") String docid);
}
