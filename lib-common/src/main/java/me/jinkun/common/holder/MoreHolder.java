package me.jinkun.common.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.jinkun.common.adapter.ListBaseAdapter;


public class MoreHolder extends BaseHolder {
    private boolean hasMore;
    private LinearLayout ll_load_more;
    private TextView tv_load_error;
    private ListBaseAdapter adapter;

    //定义三种类型状态
    //服务器端还有更多数据可加载
    public final static int HAS_MORE = 1;
    //服务器端没有跟多数据可以加载
    public final static int NO_MORE = 2;
    //加载更多数据失败
    public final static int LOAD_MORE_ERROR = 3;

    //在未知服务器有没有更多数据的时候,就默认为有更多数据,尝试去做加载更多操作,再由服务器告知结果
    //传递true代表有更多数据,传递false代表没有更多数据
    public MoreHolder(Context context, boolean hasMore, ListBaseAdapter adapter) {
        super(context);
        this.adapter = adapter;
        this.hasMore = hasMore;
        setData(hasMore ? HAS_MORE : NO_MORE);
    }

    @Override
    public void refreshView() {
        //根据状态,判断那个ll_load_more,tv_load_error显示隐藏的状态
        //将setData中的数据,获取出来,作为判断展示ll_load_more或者tv_load_error的状态码
        Integer state = (Integer) getData();

        if (state == HAS_MORE) {
            ll_load_more.setVisibility(View.VISIBLE);
            tv_load_error.setVisibility(View.GONE);
        }

        if (state == NO_MORE) {
            ll_load_more.setVisibility(View.GONE);
            tv_load_error.setVisibility(View.GONE);
        }

        if (state == LOAD_MORE_ERROR) {
            ll_load_more.setVisibility(View.GONE);
            tv_load_error.setVisibility(View.VISIBLE);
        }
    }

    //初始化holder布局效果的方法
    @Override
    public View initView() {
        View view = LayoutInflater.from(mContext).inflate(me.jinkun.common.R.layout.layout_load_more, null);
        ll_load_more = (LinearLayout) view.findViewById(me.jinkun.common.R.id.ll_load_more);
        tv_load_error = (TextView) view.findViewById(me.jinkun.common.R.id.tv_load_error);

        //加载中的事件为null
        ll_load_more.setOnClickListener(null);
        //加载失败时重新加载
        tv_load_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(hasMore ? HAS_MORE : NO_MORE);
                loadMore();
            }
        });
        return view;
    }

    @Override
    public View getRootView() {
        loadMore();
        return super.getRootView();
    }

    /**
     * Description: 加载更多数据(有更多数据可供加载) <br/>
     * Autor: Created by jinkun on 2015/12/27.
     */
    private void loadMore() {
        Integer state = (Integer) getData();
        if (adapter != null && state == HAS_MORE) {
            //请求网络加载更多数据操作
            adapter.loadMore();
        }
    }
}
