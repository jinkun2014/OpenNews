package me.jinkun.opennews.network;

import java.util.HashMap;
import java.util.Map;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Description: Do one thing at a time, and do well.
 * Created by jinkun on 2015/10/24.
 */
public class RetrofitSingleton {
    private static final Retrofit retrofit;
    private static final RetrofitSingleton _instance;
    private Map<String, Object> clsMap = new HashMap<>();

    private RetrofitSingleton() {
    }

    static {
        _instance = new RetrofitSingleton();

        //请求网络
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private <T> T newInstance(final Class<T> cls) {
        if (clsMap.get(cls.getSimpleName()) != null) {
            return (T) clsMap.get(cls.getSimpleName());
        } else {
            T t = retrofit.create(cls);
            clsMap.put(cls.getSimpleName(), t);
            return t;
        }
    }

    public static <T> T getInstance(final Class<T> cls) {
        return _instance.newInstance(cls);
    }
}
