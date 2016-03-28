package me.jinkun.opennews.base.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Description: Do one thing at a time, and do well.
 * Created by jinkun on 2015/11/18.
 */
public abstract class AbsBasePresenter<V> {
    protected Reference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    protected V getView() {
        return mViewRef != null ? mViewRef.get() : null;
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
        onDestory();
    }

    protected void onDestory() {
    }
}
