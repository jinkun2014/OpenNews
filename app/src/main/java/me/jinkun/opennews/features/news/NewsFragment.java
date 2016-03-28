package me.jinkun.opennews.features.news;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import me.jinkun.common.utils.L;
import me.jinkun.common.utils.ScreenUtil;
import me.jinkun.opennews.R;
import me.jinkun.opennews.base.MyApp;
import me.jinkun.opennews.base.fragment.MVPBaseFragment;
import me.jinkun.opennews.features.home.HomeActivity;
import me.jinkun.opennews.features.news.adapter.NewsChannelAdapter;
import me.jinkun.opennews.features.news.presenter.NewsPresenter;
import me.jinkun.opennews.features.news.view.INewsView;
import me.jinkun.opennews.helper.Bus;

/**
 * Description: 新闻模块的主页<br/>
 * Created by jinkun on 2015/12/1.
 */
public class NewsFragment extends MVPBaseFragment<INewsView, NewsPresenter> implements INewsView {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tl_TabTitle)
    TabLayout mTlTabTitle;
    @Bind(R.id.tv_ItemExist)
    TextView mTvItemExist;
    @Bind(R.id.iv_MoreItem)
    ImageView mIvMoreItem;
    @Bind(R.id.ll_MoreItem)
    LinearLayout mLlMoreItem;
    @Bind(R.id.vp_TabContent)
    ViewPager mVpTabContent;

    private List<NewsTopicFragment> mNewsTopicFragmentList;
    private NewsChannelAdapter mAdapter;
    private MyOnPageChangeListener myOnPageChangeListener;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected NewsPresenter createPresenter() {
        return new NewsPresenter();
    }

    @Override
    protected void initView(View view) {
        //初始化Toolbar
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.setTitle("新闻");
        ((HomeActivity) mActivity).setSupportActionBar(mToolbar);
//        // 返回按钮
//        ((HomeActivity) mActivity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //初始化新闻列表数据
        initPagerData();

        //初始化Listenter
        initListener();

        //开始加载数据
        beginLoad();
    }


    private void initPagerData() {
        mNewsTopicFragmentList = new ArrayList<>();
        mAdapter = new NewsChannelAdapter(getChildFragmentManager(), mNewsTopicFragmentList);

        mVpTabContent.setAdapter(mAdapter);

        mTlTabTitle.setTabTextColors(Color.GRAY, Color.RED);//设置文本在选中和为选中时候的颜色
        //用来设置tab的，同时也要覆写  PagerAdapter 的 CharSequence getPageTitle(int position) 方法，要不然 Tab 没有 title
        mTlTabTitle.setupWithViewPager(mVpTabContent);
        mTlTabTitle.setTabsFromPagerAdapter(mAdapter);
        mTlTabTitle.setTabMode(TabLayout.MODE_SCROLLABLE);

        myOnPageChangeListener = new MyOnPageChangeListener();
        mVpTabContent.addOnPageChangeListener(myOnPageChangeListener);
    }

    private void initListener() {

        mIvMoreItem.setOnClickListener(new View.OnClickListener() {
            //是否展开频道列表
            boolean isDown;

            @Override
            public void onClick(View v) {
                isDown = !isDown;
                if (isDown) {
                    //展开列表
                    mTlTabTitle.setVisibility(View.GONE);
                    mTvItemExist.setVisibility(View.VISIBLE);
                    mIvMoreItem.setImageResource(R.mipmap.iv_more_up);

                    mLlMoreItem.setVisibility(View.VISIBLE);
                    mVpTabContent.setVisibility(View.GONE);

                    //更多栏目的显示动画
                    ValueAnimator valueAnimator = ValueAnimator.ofInt(-ScreenUtil.getScreenHeight(MyApp.getInstance()), 0);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int height = (int) animation.getAnimatedValue();
                            mLlMoreItem.layout(mLlMoreItem.getLeft(), height, mLlMoreItem.getRight(), mLlMoreItem.getBottom());
                        }
                    });
                    valueAnimator.setDuration(500);
                    valueAnimator.start();

                } else {
                    //隐藏列表
                    mTlTabTitle.setVisibility(View.VISIBLE);
                    mTvItemExist.setVisibility(View.GONE);
                    mIvMoreItem.setImageResource(R.mipmap.iv_more_down);

                    mVpTabContent.setVisibility(View.VISIBLE);
                    mLlMoreItem.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void showDialog() {
        L.dStart("等待加载 NewsTopicList");
    }

    @Override
    public void hideDialog() {
        L.dEnd("NewsTopicList 加载完毕");
    }

    @Override
    public void beginLoad() {
        //加载数据
        mPresenter.loadNewsTopicData();
    }

    @Override
    public void onLoadSuccess(List<NewsTopicFragment> newsTopicFragmentList) {
        //刷新ViewPager
        mNewsTopicFragmentList.clear();
        mNewsTopicFragmentList.addAll(newsTopicFragmentList);
        mAdapter.notifyDataSetChanged();

        //更新TabLayout
        mTlTabTitle.setTabsFromPagerAdapter(mAdapter);

        //默认选中第一页
        ViewTreeObserver viewTreeObserver = mVpTabContent.getViewTreeObserver();
        if (viewTreeObserver != null) {//监听第一个的全局layout事件，来设置当前的mCurrentPosition，显示对应的tab
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mVpTabContent.getViewTreeObserver().removeGlobalOnLayoutListener(this);//只需要监听一次，之后通过listener回调即可
                    myOnPageChangeListener.onPageSelected(0);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Bus.register(this);
    }

    @Override
    public void onPause() {
        Bus.unregister(this);
        super.onPause();
    }

    /**
     * Description: 当用户调整完频道管理后，刷新tab和Viewpager <br/>
     * Autor: Created by jinkun on 2015/12/5.
     */
    @Subscriber(tag = "bus")
    private void refresh(String s) {
        L.e(s);

        //刷新ViewPager
        mNewsTopicFragmentList.remove(0);
        mAdapter.notifyDataSetChanged();

        //更新TabLayout
        mTlTabTitle.setTabsFromPagerAdapter(mAdapter);
    }

    /**
     * Description: 通过监听来实现左右切换时手动触发Tab选项Fragment去加载数据，起到懒加载的目的 <br/>
     * Autor: Created by jinkun on 2015/12/23.
     */
    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private int lastPosition = -1;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            L.e(this.getClass().getSimpleName() + " : page position is " + position);

            //如果切换当前的页面不是当前页面才去加载数据
            if (lastPosition != position) {
                //选中哪一页，就把本页设置为显示状态并去加载数据
                NewsTopicFragment newsTopicFragment = mNewsTopicFragmentList.get(position);
                newsTopicFragment.setDisplay(true);
                newsTopicFragment.startLoad();

                //选中哪一页，就把上页设置为隐藏状态
                if (lastPosition != -1) {
                    mNewsTopicFragmentList.get(lastPosition).setDisplay(false);
                }

                //记录本页为最后一页
                lastPosition = position;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
