package me.jinkun.opennews.base.model;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2016/1/10.
 */
public class AbsModel {
    protected static final <T extends AbsModel> T getInstance(Class<T> clazz) {
        AbsModel model = ModelManager.get(clazz);
        if (model == null)
            throw new RuntimeException(clazz.getName() + " No Found , Have you declare MODEL in the manifests?");
        return (T) model;
    }
}
