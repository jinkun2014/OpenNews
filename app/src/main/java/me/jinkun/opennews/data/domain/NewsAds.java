package me.jinkun.opennews.data.domain;

import java.io.Serializable;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2016/1/2.
 */
public class NewsAds implements Serializable {
    private String imgsrc;
    private String subtitle;
    private String tag;
    private String title;
    private String url;

    public NewsAds() {
    }

    public NewsAds(String imgsrc, String subtitle, String tag, String title, String url) {
        this.imgsrc = imgsrc;
        this.subtitle = subtitle;
        this.tag = tag;
        this.title = title;
        this.url = url;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
