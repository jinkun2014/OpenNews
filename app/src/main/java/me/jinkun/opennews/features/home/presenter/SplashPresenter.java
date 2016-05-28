package me.jinkun.opennews.features.home.presenter;

import java.util.List;

import me.jinkun.common.utils.L;
import me.jinkun.opennews.base.presenter.AbsBasePresenter;
import me.jinkun.opennews.features.home.view.ISplashView;
import me.jinkun.opennews.features.news.bean.NewsChannel;
import me.jinkun.opennews.features.news.model.NewsChannelModel;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2015/12/1.
 */
public class SplashPresenter extends AbsBasePresenter<ISplashView> {

    public void initData() {
        //RxJava
        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        List<NewsChannel> newsChannelList = loadNewsChannelsFromLocal();
                        if (newsChannelList == null) {
                            newsChannelList = loadNewsChannelsFromAssets();
                            saveNewsChannelsToLocal(newsChannelList);
                        }

                        subscriber.onNext("");
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        L.e("-----频道数据初始化开始-----");
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        L.e("-----频道数据初始化完毕-----");
                        getView().onInitFinish();
                    }
                });
    }

    private void saveNewsChannelsToLocal(List<NewsChannel> newsChannelList) {
        NewsChannelModel.getInstance().saveChannelsToLocal(newsChannelList);
    }

    private List<NewsChannel> loadNewsChannelsFromAssets() {
        return NewsChannelModel.getInstance().loadChannelsFromAssets();
    }

    private List<NewsChannel> loadNewsChannelsFromLocal() {
        return NewsChannelModel.getInstance().loadChannelsFromLocal();
    }
}
