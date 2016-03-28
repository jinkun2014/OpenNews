package me.jinkun.common.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class BackgroundHandlerThread {
    private HandlerThread mHandlerThread;
    private Handler mHandler;

    public BackgroundHandlerThread() {
    }

    public static BackgroundHandlerThread getInstance() {
        return Holder._instance;
    }

    private void init() {
        this.mHandlerThread = new HandlerThread(BackgroundHandlerThread.class.getSimpleName());
        this.mHandlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
    }

    public Looper getLooper() {
        return this.mHandlerThread.getLooper();
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    private static class Holder {
        private static BackgroundHandlerThread _instance = new BackgroundHandlerThread();

        private Holder() {
        }

        static {
            _instance.init();
        }
    }
}