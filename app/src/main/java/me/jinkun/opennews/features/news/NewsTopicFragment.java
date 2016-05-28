package me.jinkun.opennews.features.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import me.jinkun.common.utils.L;
import me.jinkun.common.widgets.MultiStateView;
import me.jinkun.opennews.R;
import me.jinkun.opennews.base.fragment.MVPBaseFragment;
import me.jinkun.opennews.domain.NewsTopic;
import me.jinkun.opennews.features.news.adapter.NewsTopicAdapter;
import me.jinkun.opennews.features.news.bean.NewsChannel;
import me.jinkun.opennews.features.news.presenter.NewsTopicPresenter;
import me.jinkun.opennews.features.news.view.INewsTopicView;
import me.jinkun.opennews.helper.Bus;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2015/12/1.
 */
public class NewsTopicFragment extends MVPBaseFragment<INewsTopicView, NewsTopicPresenter> implements INewsTopicView {
    private static final int NEWSDETAIL_CODE = 1;
    @Bind(R.id.msv)
    MultiStateView mMsv;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.lv_Topics)
    ListView mLvTopics;

    private NewsChannel mNewsChannel;
    private NewsTopicAdapter mNewsTopicAdapter;
    private List<NewsTopic> mNewsTopicList;
    //本页是否处于显示状态
    private boolean isDisplay;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_topic;
    }

    @Override
    protected NewsTopicPresenter createPresenter() {
        return new NewsTopicPresenter();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mNewsTopicList = new ArrayList<NewsTopic>();
        mNewsTopicAdapter = new NewsTopicAdapter(mActivity, mNewsTopicList, mNewsChannel, mPresenter);
        mLvTopics.setAdapter(mNewsTopicAdapter);
        mLvTopics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //设置postion的条目为已读
                mNewsTopicAdapter.setNewsRead(position);

                //跳转到详情页面
                Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                intent.putExtra("NewsTopic", mNewsTopicList.get(position));
                startActivityForResult(intent, NEWSDETAIL_CODE);
            }
        });

        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshData(mNewsChannel.getTid(), 0, 20);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //主要是刷新ListView里Item被点击后的效果
        mNewsTopicAdapter.notifyDataSetChanged();
    }

    public void startLoad() {
        if (mNewsTopicList.size() == 0) {
            //加载数据
            mPresenter.loadData(mNewsChannel.getTid(), 0, 20);
        }
    }

    @Override
    public void onLoadStart() {
        //设置加载状态
        mMsv.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public void onLoadSuccess(List<NewsTopic> newsTopicList) {
        //设置加载成功状态
        mMsv.setViewState(MultiStateView.VIEW_STATE_CONTENT);

        L.e(this.getClass().getSimpleName() + " : onLoadSuccess()");
        mNewsTopicList.clear();
        mNewsTopicList.addAll(newsTopicList);
        mNewsTopicAdapter.notifyDataSetChanged();

        if (mRefresh.isRefreshing()) {
            mRefresh.setRefreshing(false);
        }
    }


    @Override
    public void onLoadError() {
        mMsv.setViewState(MultiStateView.VIEW_STATE_ERROR);
    }

    @Override
    public void onLoadEmpty() {
        mMsv.setViewState(MultiStateView.VIEW_STATE_EMPTY);
    }

    @Override
    public void onLoadFinished() {

    }

    public NewsChannel getNewsChannel() {
        return mNewsChannel;
    }

    public void setNewsChannel(NewsChannel newsChannel) {
        mNewsChannel = newsChannel;
    }

    public boolean isDisplay() {
        return isDisplay;
    }

    public void setDisplay(boolean display) {
        isDisplay = display;

        if (!isDisplay) {
            stopBanner();
        }
    }

    /**
     * Description: 停止Banner的轮播 <br/>
     * Autor: Created by jinkun on 2016/1/2.
     */
    private void stopBanner() {
        if (mNewsTopicList.size() > 0) {
            Bus.post(mNewsTopicList.get(0).getTitle(), "stopTurnning");
        }
    }

}
