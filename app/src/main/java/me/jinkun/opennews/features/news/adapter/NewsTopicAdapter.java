package me.jinkun.opennews.features.news.adapter;

import android.content.Context;

import java.util.List;

import me.jinkun.common.listview.adapter.ListBaseAdapter;
import me.jinkun.common.listview.holder.BaseHolder;
import me.jinkun.opennews.domain.NewsTopic;
import me.jinkun.opennews.features.news.bean.NewsChannel;
import me.jinkun.opennews.features.news.holder.HeaderBannerHolder;
import me.jinkun.opennews.features.news.holder.NewsTopicHolder;
import me.jinkun.opennews.features.news.presenter.NewsTopicPresenter;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2015/12/27.
 */
public class NewsTopicAdapter extends ListBaseAdapter<NewsTopic> {
    private int start = 0;
    private int size = 20;
    private NewsChannel mNewsChannel;
    private NewsTopicPresenter mNewsTopicPresenter;

    public NewsTopicAdapter(Context context, List<NewsTopic> list, NewsChannel newsChannel, NewsTopicPresenter newsTopicPresenter) {
        super(context, list);
        this.mNewsChannel = newsChannel;
        this.mNewsTopicPresenter = newsTopicPresenter;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    @Override
    public int getInnerType(int position) {
        if (position == 0) {
            return super.getInnerType(position) + 1;
        }
        return super.getInnerType(position);
    }

    @Override
    public BaseHolder getHolder() {
        if (currentPosition == 0) {
            return new HeaderBannerHolder(mContext);
        }
        return new NewsTopicHolder(mContext, mNewsTopicPresenter);
    }

    @Override
    public List<NewsTopic> subLoadMore() {
        start += size;
        List<NewsTopic> newsTopicList = mNewsTopicPresenter.loadDataFromNet(mNewsChannel.getTid(), start, size);

        //防止加载失败后重新加载时跳过了加载失败的部分
        if (newsTopicList != null && newsTopicList.size() > 0) {
            start = start + size;
        }
        return newsTopicList;
    }


    public void setNewsRead(int position) {
        mNewsTopicPresenter.setIsRead(getItem(position));
    }
}
