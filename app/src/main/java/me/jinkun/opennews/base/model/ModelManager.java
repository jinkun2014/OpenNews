package me.jinkun.opennews.base.model;

import java.util.HashMap;

/**
 * Description: 用来缓存已创建的Model <br/>
 * Autor: Created by jinkun on 2016/1/24.
 */
public class ModelManager {
    private final static HashMap<Class<?>,AbsModel> mModelMap = new HashMap<>();
    public static <T extends AbsModel> AbsModel get(Class<T> clazz) {
        AbsModel absModel = mModelMap.get(clazz);
        if (absModel == null) {
            try {
                absModel = (AbsModel) (clazz.newInstance());
                mModelMap.put(clazz,absModel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return absModel;
    }
}
