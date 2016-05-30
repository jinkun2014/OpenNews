package me.jinkun.opennews.features.news.view;

import java.util.List;

import me.jinkun.opennews.base.view.IBaseView;
import me.jinkun.opennews.data.domain.NewsTopic;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2015/12/21.
 */
public interface INewsTopicView extends IBaseView<NewsTopic> {
    public void onLoadStart();

    public void onLoadSuccess(List<NewsTopic> dataList);

    public void onLoadError();

    public void onLoadEmpty();

    public void onLoadFinished();
}
