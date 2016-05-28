package me.jinkun.opennews.network;

import java.util.List;
import java.util.Map;

import me.jinkun.opennews.domain.NewsDetail;
import me.jinkun.opennews.domain.NewsTopic;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by clevo on 2015/6/10.
 */
public interface ApiService {
    @GET("nc/article/list/{tid}/{start}-{end}.html")
    Observable<ApiResp<Map<String, List<NewsTopic>>>> listTopics(@Path("tid") String tid, @Path("start") int start, @Path("end") int end);

    @GET("nc/article/{docid}/full.html")
    Observable<ApiResp<Map<String, NewsDetail>>> listNewsDetail(@Path("docid") String docid);
}
