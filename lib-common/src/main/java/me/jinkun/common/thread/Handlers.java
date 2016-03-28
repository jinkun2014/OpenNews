package me.jinkun.common.thread;

import android.os.Handler;
import android.os.Looper;

public class Handlers {
    public static final Handler sUIHandler = new Handler(Looper.getMainLooper());
    public static final Handler sBackgroundHandler = BackgroundHandlerThread.getInstance().getHandler();
}