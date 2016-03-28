package me.jinkun.opennews.domain;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "NEWS_IMAGE".
 */
public class NewsImage implements java.io.Serializable {

    private Long id;
     /*图片提示*/
    private String alt;
     /*图片像素*/
    private String pixel;
     /*图片应用*/
    private String ref;
     /*图片地址*/
    private String src;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public NewsImage() {
    }

    public NewsImage(Long id) {
        this.id = id;
    }

    public NewsImage(Long id, String alt, String pixel, String ref, String src) {
        this.id = id;
        this.alt = alt;
        this.pixel = pixel;
        this.ref = ref;
        this.src = src;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getPixel() {
        return pixel;
    }

    public void setPixel(String pixel) {
        this.pixel = pixel;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
