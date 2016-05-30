package me.jinkun.opennews.features.news;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.Bind;
import me.jinkun.opennews.R;
import me.jinkun.opennews.base.activity.MVPBaseActivity;
import me.jinkun.opennews.data.domain.NewsDetail;
import me.jinkun.opennews.data.domain.NewsTopic;
import me.jinkun.opennews.features.news.presenter.NewsDetailPresenter;
import me.jinkun.opennews.features.news.view.INewsDetailView;

public class NewsDetailActivity extends MVPBaseActivity<INewsDetailView, NewsDetailPresenter> implements INewsDetailView {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.wv_NewsDetails)
    WebView mWvNewsDetails;

    NewsTopic mNewsTopic;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected NewsDetailPresenter createPresenter() {
        return new NewsDetailPresenter();
    }

    @Override
    protected void initView(View view) {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("详情");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mNewsTopic = (NewsTopic) getIntent().getSerializableExtra("NewsTopic");

        mPresenter.loadNewsDetail(mNewsTopic);
    }

    @Override
    public void onLoadSuccess(NewsDetail newsDetail) {
        WebSettings settings = mWvNewsDetails.getSettings();
        //设置允许使用js
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置允许访问本地文件
        settings.setAllowFileAccess(true);
        mWvNewsDetails.setWebViewClient(new MyWebViewClient(newsDetail));
        mWvNewsDetails.setWebChromeClient(new MyWebChromeClient());
        // 添加一个对象, 让JS可以访问该对象的方法, 该对象中可以调用JS中的方法
        mWvNewsDetails.addJavascriptInterface(newsDetail, "news");
        mWvNewsDetails.loadUrl("file:///android_asset/web/newspage.html");
    }

    /**
     * Provides a hook for calling "alert" from javascript. Useful for
     * debugging your javascript.
     */
    final class MyWebChromeClient extends WebChromeClient {
        @Override
        @JavascriptInterface
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.e("----", message);
            result.confirm();
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyWebViewClient extends WebViewClient {

        private NewsDetail mNewsDetail;

        public MyWebViewClient(NewsDetail newsDetail) {
            this.mNewsDetail = newsDetail;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            //当页面加载完再去加载图片
            for (int i = 0; i < mNewsDetail.getImg().size(); i++) {
                String src = mNewsDetail.getImg().get(i).getSrc();
                view.loadUrl("javascript:loadImages(" + i + ",'" +src+ "')");
            }
        }
    }
}
