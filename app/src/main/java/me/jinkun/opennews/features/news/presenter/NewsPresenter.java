package me.jinkun.opennews.features.news.presenter;

import java.util.ArrayList;
import java.util.List;

import me.jinkun.opennews.base.presenter.AbsBasePresenter;
import me.jinkun.opennews.features.news.NewsTopicFragment;
import me.jinkun.opennews.data.domain.NewsChannel;
import me.jinkun.opennews.data.model.NewsChannelModel;
import me.jinkun.opennews.features.news.view.INewsView;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2015/12/1.
 */
public class NewsPresenter extends AbsBasePresenter<INewsView> {
    private Subscription subscribe;

    public void loadNewsTopicData() {
        //如果View为null则
        if (getView() == null) {
            return;
        }

        getView().showDialog();

        subscribe = Observable
                .create(new Observable.OnSubscribe<List<NewsChannel>>() {
                    @Override
                    public void call(Subscriber<? super List<NewsChannel>> subscriber) {
                        List<NewsChannel> newsChannelList = loadChannelsFromLocal();
                        if (newsChannelList == null) {
                            newsChannelList = loadChannelsFromNet();
                        }
                        subscriber.onNext(newsChannelList);
                        subscriber.onCompleted();
                    }
                })//
                .subscribeOn(Schedulers.io())//
                .map(new Func1<List<NewsChannel>, List<NewsTopicFragment>>() {
                    @Override
                    public List<NewsTopicFragment> call(List<NewsChannel> newsChannelList) {
                        if (newsChannelList == null) {
                            return null;
                        }
                        return channels2NewsTopicFragments(newsChannelList);
                    }
                })//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe(new Subscriber<List<NewsTopicFragment>>() {
                    @Override
                    public void onCompleted() {
                        getView().hideDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideDialog();
                    }

                    @Override
                    public void onNext(List<NewsTopicFragment> newsTopicFragments) {
                        getView().onLoadSuccess(newsTopicFragments);
                    }
                });
    }

    /**
     * Description: 将NewsChannelList 转换封装到 NewsTopicFragmentList中<br/>
     * Autor: Created by jinkun on 2016/1/13.
     */
    private List<NewsTopicFragment> channels2NewsTopicFragments(List<NewsChannel> newsChannelList) {
        List<NewsTopicFragment> newsTopicFragmentList = new ArrayList<NewsTopicFragment>();
        for (NewsChannel channel : newsChannelList) {
            NewsTopicFragment newsTopicFragment = new NewsTopicFragment();
            newsTopicFragment.setNewsChannel(channel);
            newsTopicFragmentList.add(newsTopicFragment);
        }
        return newsTopicFragmentList;
    }

    private List<NewsChannel> loadChannelsFromNet() {
        return null;
    }

    private List<NewsChannel> loadChannelsFromLocal() {
        return NewsChannelModel.getInstance().loadChannelsFromLocal();
    }

    @Override
    protected void onDestory() {
        subscribe.unsubscribe();
    }
}
