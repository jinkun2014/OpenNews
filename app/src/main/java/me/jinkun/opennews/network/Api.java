package me.jinkun.opennews.network;

/**
 * Description: Do one thing at a time, and do well.
 * Created by jinkun on 2015/11/21.
 */
public class Api {
    public final static String BASEURL = "http://c.m.163.com/";

    //标题分类
    //http://c.m.163.com/nc/topicset/android/subscribe/manage/listspecial.html
    public final static String NEWSTOPIC_LIST_URL = "http://c.m.163.com/nc/topicset/android/subscribe/manage/listspecial.html";

    //标题分类下的文章列表
    //http://c.m.163.com/nc/article/list/T1348648517839/0-20.html
    public final static String NEWSARTICLE_LIST_URL = "http://c.m.163.com/nc/article/list/";

    //详情页
    // http://c.m.163.com/nc/article/B2O6LUS500031H2L/full.html,
    public final static String NEWS_FULL_URL = "http://c.m.163.com/nc/article/%s/full.html";


    //http://c.m.163.com/nc/topicset/android/subscribe/manage/listspecial.html
    public static String getTopicUrl() {
        return NEWSTOPIC_LIST_URL;
    }

    //http://c.m.163.com/nc/article/list/T1348648517839/0-20.html
    public static String getNewsArticleUrl(String tid, int pageNo, int pageSize) {
        return String.format("http://c.m.163.com/nc/article/list/" + tid + "/" + pageNo + "-" + pageSize + ".html");
    }

    // String.format("http://c.m.163.com/nc/article/%s/full.html", bid);
    public static String getNewsFullUrl(String docid) {
        return String.format("http://c.m.163.com/nc/article/%s/full.html", docid);
    }
}
