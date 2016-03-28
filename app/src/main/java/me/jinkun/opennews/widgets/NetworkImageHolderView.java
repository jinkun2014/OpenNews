package me.jinkun.opennews.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import me.jinkun.opennews.R;
import me.jinkun.opennews.domain.NewsAds;
import com.bigkoo.convenientbanner.holder.Holder;
import com.squareup.picasso.Picasso;

/**
 * Description: 轮播图网络图片加载例子 <br/>
 * Autor: Created by jinkun on 2015/12/31.
 */
public class NetworkImageHolderView implements Holder<NewsAds> {
    private ImageView imageView;
    @Override
    public View createView(final Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
        imageView = (ImageView) view.findViewById(R.id.iv_Ads);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context,int position, NewsAds data) {
        Picasso.with(context).load(data.getImgsrc()).into(imageView);
    }
}
