package me.jinkun.opennews.features.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import me.jinkun.opennews.features.news.NewsTopicFragment;

import java.util.List;

/**
 * Description: NewsFragment切换的Adapter<br/>
 * Created by jinkun on 2015/12/2.
 */
public class NewsChannelAdapter extends FragmentStatePagerAdapter {
    private final List<NewsTopicFragment> mNewsTopicFragmentList;

    public NewsChannelAdapter(FragmentManager fm, List<NewsTopicFragment> newsTopicFragmentList) {
        super(fm);
        this.mNewsTopicFragmentList = newsTopicFragmentList;
    }

    /**
     * Description: 刷新Fragment <br/>
     * Autor: Created by jinkun on 2015/12/5.
     */
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return mNewsTopicFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mNewsTopicFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mNewsTopicFragmentList.get(position).getNewsChannel().getTname();
    }
}
