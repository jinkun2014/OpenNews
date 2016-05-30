package me.jinkun.opennews.data.network;


import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Description: Do one thing at a time, and do well.
 * Created by jinkun on 2015/10/24.
 */
public class ApiServiceManager {
    private static ApiServiceManager _instance;
    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    private Map<String, Object> apiMap = new HashMap<>();

    static {
        _instance = new ApiServiceManager();
    }

    private ApiServiceManager() {
    }

    public static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            //日志
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            mOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
        }
        return mOkHttpClient;
    }

    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            //请求网络
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Api.BASEURL)
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    private <T> T newInstance(final Class<T> cls) {
        if (apiMap.get(cls.getSimpleName()) != null) {
            return (T) apiMap.get(cls.getSimpleName());
        } else {
            T t = getRetrofit().create(cls);
            apiMap.put(cls.getSimpleName(), t);
            return t;
        }
    }

    public static <T> T getApiService(final Class<T> cls) {
        return _instance.newInstance(cls);
    }
}
