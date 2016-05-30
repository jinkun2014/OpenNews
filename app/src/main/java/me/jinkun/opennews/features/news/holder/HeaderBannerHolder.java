package me.jinkun.opennews.features.news.holder;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.squareup.picasso.Picasso;

import org.simple.eventbus.Subscriber;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.jinkun.common.listview.holder.BaseHolder;
import me.jinkun.common.thread.ThreadHelper;
import me.jinkun.common.utils.L;
import me.jinkun.common.utils.T;
import me.jinkun.opennews.data.domain.NewsAds;
import me.jinkun.opennews.data.domain.NewsTopic;
import me.jinkun.opennews.util.Bus;
import me.jinkun.opennews.widgets.NetworkImageHolderView;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2015/12/31.
 */
public class HeaderBannerHolder extends BaseHolder<NewsTopic> {
    @Bind(me.jinkun.opennews.R.id.cb_image)
    ConvenientBanner mCbImage;
    @Bind(me.jinkun.opennews.R.id.iv_Img)
    ImageView mIvImg;
    @Bind(me.jinkun.opennews.R.id.tv_Title)
    TextView mTvTitle;
    @Bind(me.jinkun.opennews.R.id.vp_Banner)
    ViewPager mVpBanner;

    private int currBannerPosition = -1;
    private NewsTopic newsTopic;

    public HeaderBannerHolder(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(mContext).inflate(me.jinkun.opennews.R.layout.banner_container, null);
        ButterKnife.bind(this, view);
        //注册Bus
        Bus.register(this);
        return view;
    }

    @Override
    public void refreshView() {
        newsTopic = getData();

        final List<NewsAds> ads = newsTopic.getAds();

        //L.e(this.getClass().getSimpleName() + " : refreshView --> " + newsTopic.getTitle() + " , ads.size() --> " + ads.size());

        //如果没有Ads则只显示图片
        if (ads.size() == 0) {
            mCbImage.setVisibility(View.GONE);
            mIvImg.setVisibility(View.VISIBLE);
            mTvTitle.setText(newsTopic.getTitle());
            Picasso.with(mContext).load(newsTopic.getImgsrc()).into(mIvImg);
            return;
        }

        //如果有Ads则开始轮播显示
        mCbImage.setVisibility(View.VISIBLE);
        mCbImage.setPages(mCBViewHolderCreator, ads)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{me.jinkun.opennews.R.mipmap.ic_page_indicator, me.jinkun.opennews.R.mipmap.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                //监听翻页事件
                .setOnPageChangeListener(mOnPageChangeListener).setOnItemClickListener(mOnItemClickListener);

        if (currBannerPosition == -1) {
            mTvTitle.setText(ads.get(0).getTitle());
        }

        // 3000 毫秒后
        if (!mCbImage.isTurning()) {
            ThreadHelper.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    startTurnning(newsTopic.getTitle());
                }
            }, 3000);
        }
    }

    /**
     * Description: Banner网络图片加载 <br/>
     * Autor: Created by jinkun on 2016/1/4.
     */
    private CBViewHolderCreator mCBViewHolderCreator = new CBViewHolderCreator<NetworkImageHolderView>() {
        @Override
        public NetworkImageHolderView createHolder() {
            return new NetworkImageHolderView();
        }
    };

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            currBannerPosition = position;
            mTvTitle.setText(newsTopic.getAds().get(position).getTitle());
        }
    };

    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            T.s(mContext, "你点击了图片");
        }
    };

    /**
     * Description: 当此Holder被隐藏时停止滚动<br/>
     * Autor: Created by jinkun on 2016/1/2.
     */
    @Subscriber(tag = "stopTurnning")
    public void stopTurnning(String title) {
        L.e("--> 来自Bus： --> " + title);
        if (mCbImage != null && mCbImage.isTurning()) {
            if (title.equals(newsTopic.getTitle())) {
                mCbImage.stopTurning();
            }
        }
    }

    /**
     * Description: 当此Holder被隐藏时停止滚动<br/>
     * Autor: Created by jinkun on 2016/1/2.
     */
    @Subscriber(tag = "startTurnning")
    public void startTurnning(String title) {
        if (mCbImage != null) {
            if (title.equals(newsTopic.getTitle())) {
                //每隔 2000 毫秒切换一次
                mCbImage.startTurning(3000);
            }
        }
    }
}
