package me.jinkun.opennews.data.domain;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

import java.util.ArrayList;
import java.util.List;

/**
 * Entity mapped to table "NEWS_TOPIC".
 */
public class NewsTopic implements java.io.Serializable {

    private Long id;
     /*标题*/
    private String title;
     /*缩略图*/
    private String imgsrc;
     /*简述*/
    private String digest;
     /*详情ID*/
    private String docid;

    // KEEP FIELDS - put your custom fields here
    private List<NewsAds> ads = new ArrayList<>();
    // KEEP FIELDS END

    public NewsTopic() {
    }

    public NewsTopic(Long id) {
        this.id = id;
    }

    public NewsTopic(Long id, String title, String imgsrc, String digest, String docid) {
        this.id = id;
        this.title = title;
        this.imgsrc = imgsrc;
        this.digest = digest;
        this.docid = docid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    // KEEP METHODS - put your custom methods here
    public List<NewsAds> getAds() {
        return ads;
    }

    public void setAds(List<NewsAds> ads) {
        this.ads = ads;
    }
    // KEEP METHODS END

}
