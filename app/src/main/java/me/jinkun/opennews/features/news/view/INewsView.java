package me.jinkun.opennews.features.news.view;

import java.util.List;

import me.jinkun.opennews.features.news.NewsTopicFragment;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2015/12/1.
 */
public interface INewsView {
    public void showDialog();

    public void hideDialog();

    public void onLoadSuccess(List<NewsTopicFragment> newsTopicFragmentList);

}
