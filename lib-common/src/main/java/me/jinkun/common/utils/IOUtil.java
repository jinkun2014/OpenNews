package me.jinkun.common.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Description: Do one thing at a time, and do well.
 * Created by jinkun on 2015/11/18.
 */
public class IOUtil {
    public static void colse(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
