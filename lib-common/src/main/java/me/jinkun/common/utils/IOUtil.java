package me.jinkun.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Description: Do one thing at a time, and do well.
 * Created by jinkun on 2015/11/18.
 */
public class IOUtil {
    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(InputStream is, File des) {
        if (des == null) {
            throw new RuntimeException("目标文件不能为空");
        }

        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        byte[] buff = new byte[1024];
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(new FileOutputStream(des));

            int len = -1;
            while ((len = bis.read(buff)) != -1) {
                bos.write(buff, 0, buff.length);
                bos.flush();
            }
        } catch (Exception e) {

        } finally {
            close(bis);
            close(bos);
            buff = null;
        }
    }
}
