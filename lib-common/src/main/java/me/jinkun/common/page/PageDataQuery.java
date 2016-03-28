package me.jinkun.common.page;

import java.util.List;

public abstract class PageDataQuery<T> implements IPageDataQuery<T> {
    private int pageSize;
    private int currentPage;

    public PageDataQuery(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public List<T> queryList() {
        List<T> list = queryImpl(currentPage, pageSize);
        if (list != null && list.size() > 0) {
            currentPage += 1;
        }

        sort(list);

        return list;
    }

    @Override
    public int getPage() {
        return currentPage;
    }

    @Override
    public int getPageSize() {
        return this.pageSize;
    }

    @Override
    public void reset() {
        currentPage = 1;
    }

    public abstract List<T> queryImpl(int page, int pageSize);

    protected abstract void sort(List<T> list);
}
