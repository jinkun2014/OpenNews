package me.jinkun.opennews.features.news.holder;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.jinkun.common.listview.holder.BaseHolder;
import me.jinkun.common.utils.DensityUtil;
import me.jinkun.opennews.R;
import me.jinkun.opennews.data.domain.NewsTopic;
import me.jinkun.opennews.features.news.presenter.NewsTopicPresenter;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2015/12/27.
 */
public class NewsTopicHolder extends BaseHolder<NewsTopic> {
    @Bind(R.id.iv_thumb)
    ImageView mIvThumb;
    @Bind(R.id.tv_Title)
    TextView mTvTitle;
    @Bind(R.id.tv_Digest)
    TextView mTvDigest;

    private NewsTopicPresenter mNewsTopicPresenter;

    public NewsTopicHolder(Context context, NewsTopicPresenter newsTopicPresenter) {
        super(context);
        mNewsTopicPresenter = newsTopicPresenter;
    }

    @Override
    public void refreshView() {
        NewsTopic newsTopic = getData();
        //L.e(this.getClass().getSimpleName() + " : refreshView --> " + newsTopic.getTitle() + " , ads.size() --> " + newsTopic.getAds().size());

        Picasso.with(mContext).load(newsTopic.getImgsrc()).resize(DensityUtil.dp2px(mContext, 100), DensityUtil.dp2px(mContext, 70)).centerCrop().into(mIvThumb);
        mTvTitle.setText(newsTopic.getTitle());
        mTvDigest.setText(newsTopic.getDigest());

        //如果新闻已读则设置标题为红色，否则默认颜色
        if (mNewsTopicPresenter.checkIsRead(newsTopic)) {
            mTvTitle.setTextColor(mContext.getResources().getColor(R.color.gray_8f));
        } else {
            mTvTitle.setTextColor(Color.BLACK);
        }
    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_lv_news_topic, null);
        ButterKnife.bind(this, view);
        return view;
    }
}
