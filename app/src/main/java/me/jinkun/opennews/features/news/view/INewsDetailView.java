package me.jinkun.opennews.features.news.view;

import me.jinkun.opennews.base.view.IBaseView;
import me.jinkun.opennews.data.domain.NewsDetail;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2015/12/27.
 */
public interface INewsDetailView extends IBaseView<NewsDetail> {
    void onLoadSuccess(NewsDetail newsDetail);
}
