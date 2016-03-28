package me.jinkun.common.cache;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description: 硬盘缓存工具类 <br/>
 * Autor: Created by jinkun on 2015/12/27.
 */
public class DiskLruCacheUtil {

    // 同一个key可以对应多少个缓存文件
    private static int valueCount = 1;
    // 一个缓存文件最大可以缓存10M
    private static int maxSize = 10 * 1024 * 1024;
    // 对象缓存目录
    private static final String CACHE_OBJECT = "object";

    /**
     * 保存对象缓存
     *
     * @param ser
     * @param key
     */
    public static void saveObject(Context context,Serializable ser, String key) {
        ObjectOutputStream oos = null;
        try {
            DiskLruCache mDiskLruCache = getDiskLruCache(context);
            DiskLruCache.Editor editor = mDiskLruCache.edit(hashKeyForDisk(key));
            if (editor != null) {
                oos = new ObjectOutputStream(editor.newOutputStream(0));
                oos.writeObject(ser);
                oos.flush();
                editor.commit();
            }
            mDiskLruCache.flush();
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取对象缓存
     */
    public static Serializable readObject(Context context,String key) {
        ObjectInputStream ois = null;
        try {
            DiskLruCache mDiskLruCache = getDiskLruCache(context);
            DiskLruCache.Snapshot snapShot = mDiskLruCache.get(hashKeyForDisk(key));
            if (snapShot != null) {
                InputStream is = snapShot.getInputStream(0);
                ois = new ObjectInputStream(is);
                return (Serializable) ois.readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 移除对象
     */
    public static void removeObject(Context context,String key) {
        try {
            DiskLruCache mDiskLruCache = getDiskLruCache(context);
            mDiskLruCache.remove(hashKeyForDisk(key));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static DiskLruCache getDiskLruCache(Context context) {
        DiskLruCache mDiskLruCache = null;
        try {
            File cacheDir = getDiskCacheDir(context,CACHE_OBJECT);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(getDiskCacheDir(context,CACHE_OBJECT), getAppVersion(context), valueCount, maxSize);
            return mDiskLruCache;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取相应的缓存目录
     *
     * @param uniqueName
     * @return
     */
    public static File getDiskCacheDir(Context context,String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 传入缓存的key值，以得到相应的MD5值
     */
    public static String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    private static int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}
