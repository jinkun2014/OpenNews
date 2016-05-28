package me.jinkun.common.listview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import me.jinkun.common.listview.holder.BaseHolder;
import me.jinkun.common.listview.holder.MoreHolder;
import me.jinkun.common.thread.ThreadHelper;
import me.jinkun.common.thread.manager.ThreadManager;

/**
 * Description: 对BaseAdapter的封装适用于ListView <br/>
 * 用法：<br/>
 * <p>你好<p/>
 * Autor: Created by jinkun on 2015/12/27.
 */
public abstract class ListBaseAdapter<T> extends BaseAdapter {
    /* 每页加载数据的大小 */
    private static final int PAGE_SIZE = 20;
    /* 加载更多类型条目状态码    */
    private static int LOAD_MORE_ITEM = 0;
    /* 展示数据类型条目的状态码,一般类型条目 */
    private static int LIST_VIEW_ITEM = 1;

    protected Context mContext;
    public List<T> mList;
    private BaseHolder holder;
    private MoreHolder moreHolder;
    protected int currentPosition = -1;

    public ListBaseAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mList = list;
    }

    /**
     * 因为添加了一种更多类型条目,所以条目总数+1
     * 如果子类是多条目的则也需实现
     */
    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    /**
     * 索引为最后一个的时候,条目类型为加载更多的条目类型,否则为一般条目类型
     */
    @Override
    public int getItemViewType(int position) {
        currentPosition = position;
        if (getCount() - 1 == position) {
            return LOAD_MORE_ITEM;
        } else {
            return getInnerType(position);
        }
    }

    /**
     * 在子类中可以重写, 多维护条目类型
     */
    public int getInnerType(int position) {
        return LIST_VIEW_ITEM;
    }

    /**
     * 添加多一个条目,多的这个条目就是加载更多的条目
     */
    @Override
    public int getCount() {
        return mList.size() + 1;
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // 创建holder,然后将此holder设置在view的tag上
            if (getItemViewType(position) == LOAD_MORE_ITEM) {
                // 加载更多条目复用加载更多条目的holder,创建加载更多的holder,position == getCount()-1
                holder = getMoreHolder();
            } else {
                // 一般条目复用一般条目的holder,创建一般条目的holder,position不是最后一个的时候
                holder = getHolder();
            }
        } else {
            if (convertView.getTag() != null) {
                holder = (BaseHolder) convertView.getTag();
            }
        }
        // 填充数据适配的集合根据索引得到的集合中的对象,也就是一个条目需要使用的对象
        if (getItemViewType(position) != LOAD_MORE_ITEM) {
            holder.setData(mList.get(position));
        }
        // 如果看见加载更多的条目的时候时候,说明调用的加载更多此item的getView方法,调用getView方法,即会调用holder.getRootView方法
        // 所以将加载更多的操作放置在MoreHolder对应对象的getRootView方法中去做编写
        return holder.getRootView();
    }

    /**
     * 每一个界面加载更多的页面效果是一致的,所以可以实现
     */
    private BaseHolder getMoreHolder() {
        // 创建一个加载更多holder,默认情况给其维护有更多数据
        if (moreHolder == null) {
            moreHolder = new MoreHolder(mContext, hasMore(), this);
        }
        return moreHolder;
    }

    /**
     * @return true默认就有更多数据 false没有更多数据,不需要进行加载更多操作
     */
    public boolean hasMore() {
        return true;
    }

    /**
     * 给使用此数据适配器的ListView,提供加载更多的操作方法
     */
    public void loadMore() {
        ThreadManager.ThreadProxyPool threadProxyPool = ThreadManager.getThreadProxyPool();
        threadProxyPool.execute(new RunnableTask());
    }

    class RunnableTask implements Runnable {
        @Override
        public void run() {
            final List<T> subLoadMore = subLoadMore();
            // 后续的代码设计到控件的是否可见,设计UI操作需要放置到主线程中处理
            ThreadHelper.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // 本次加载更多请求完成后,是否还有会更多数据,依赖条件就是每次请求获取的数据是否小于或等于20条
                    if (subLoadMore != null) {
                        // 有数据,和20做比对
                        if (subLoadMore.size() == PAGE_SIZE) {
                            // 获取满了一页的数据,说明还有更多
                            moreHolder.setData(MoreHolder.HAS_MORE);
                        } else if (subLoadMore.size() < PAGE_SIZE) {
                            // 获取不满20条数据,说明本次请求已经将服务器端的所有数据获取完了,那后续就不可能还有更多数据可供加载
                            moreHolder.setData(MoreHolder.NO_MORE);
                        }
                    } else {
                        // 本次加载更多,失败,通知加载更多的UI显示为,加载更多失败,点击重试
                        moreHolder.setData(MoreHolder.LOAD_MORE_ERROR);
                    }

                    // 要将加载更多的数据,放在原有数据集合的后面去
                    if (mList != null && subLoadMore != null && subLoadMore.size() > 0) {
                        mList.addAll(subLoadMore);
                        // 刷新数据适配器才可以出发UI的变化
                        notifyDataSetChanged();
                    }
                }
            });
        }
    }

    /**
     * 时刻返回填充数据适配器中集合大小的方法
     */
    public int getListSize() {
        return mList.size();
    }

    /**
     * 每一个ListView使用到的holder都是有差异的,所以在父类的数据适配器中,无法去确定如何构建holder,于是抽象
     */
    public abstract BaseHolder getHolder();

    /**
     * 请求加载数据（本方法在子线程运行），第一次和第二次一致
     */
    public abstract List<T> subLoadMore();
}
