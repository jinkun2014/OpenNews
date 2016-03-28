package me.jinkun.opennews.features.home.presenter;

import me.jinkun.common.utils.L;
import me.jinkun.common.utils.SPUtil;
import me.jinkun.opennews.constant.ISpKey;
import me.jinkun.opennews.base.presenter.AbsBasePresenter;
import me.jinkun.opennews.features.home.model.SplashModel;
import me.jinkun.opennews.features.home.view.ISplashView;
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

    public SplashPresenter() {
    }

    public void initData() {

        boolean isInitDB = (boolean) SPUtil.get(ISpKey.INIT_DB, false);

        if (!isInitDB) {
            //RxJava
            Observable
                    .create(new Observable.OnSubscribe<String>() {
                        @Override
                        public void call(Subscriber<? super String> subscriber) {
                            SplashModel.getInstance().initNewsChennels();
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
        } else {
            getView().onInitFinish();
        }
    }

    @Override
    protected void onDestory() {

    }
}
