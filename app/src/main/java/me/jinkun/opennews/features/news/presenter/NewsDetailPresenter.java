package me.jinkun.opennews.features.news.presenter;

import me.jinkun.common.utils.L;
import me.jinkun.opennews.base.presenter.AbsBasePresenter;
import me.jinkun.opennews.domain.NewsDetail;
import me.jinkun.opennews.domain.NewsTopic;
import me.jinkun.opennews.features.news.model.NewsDetailModel;
import me.jinkun.opennews.features.news.view.INewsDetailView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2015/12/27.
 */
public class NewsDetailPresenter extends AbsBasePresenter<INewsDetailView> {
    public void loadNewsDetail(final NewsTopic newsTopic) {
        Observable
                .create(new Observable.OnSubscribe<NewsDetail>() {
                    @Override
                    public void call(Subscriber<? super NewsDetail> subscriber) {
                        NewsDetail newsDetail = loadFromLocal(newsTopic.getDocid());
                        if (newsDetail == null) {
                            newsDetail = loadFromNet(newsTopic.getDocid());
                            save2Local(newsTopic.getDocid(), newsDetail);
                        }
                        subscriber.onNext(newsDetail);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewsDetail newsDetail) {
                        getView().onLoadSuccess(newsDetail);
                    }
                });

    }

    private void save2Local(String docid, NewsDetail newsDetail) {
        L.dStart("save2Local");
        NewsDetailModel.getInstance().save2Local(docid, newsDetail);
    }

    private NewsDetail loadFromNet(String docid) {
        L.dStart("loadFromNet");
        return NewsDetailModel.getInstance().loadDetailFromNet(docid);
    }

    private NewsDetail loadFromLocal(String docid) {
        L.dStart("loadFromLocal");
        return NewsDetailModel.getInstance().loadDetailFromLocal(docid);
    }
}
