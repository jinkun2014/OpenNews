package me.jinkun.common.page;

import java.util.List;

public interface IPageDataQuery<T> {
	public List<T> queryList();
	public int getPage();
	public int getPageSize();
	public void reset();
}
