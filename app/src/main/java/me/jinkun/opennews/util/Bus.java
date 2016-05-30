package me.jinkun.opennews.util;

import org.simple.eventbus.EventBus;

/**
 * Description: Do one thing at a time, and do well.<br/>
 * Created by jinkun on 2015/12/2.
 */
public class Bus {

    private Bus() {}

    public static void register(Object o) {
        BusImpl.bus.register(o);
    }

    public static void post(Object event) {
        BusImpl.bus.post(event);
    }

    public static void post(Object event, String tag) {
        BusImpl.bus.post(event, tag);
    }

    public static void unregister(Object o) {
        BusImpl.bus.unregister(o);
    }

    private static class BusImpl {
        private static final EventBus bus = EventBus.getDefault();
    }
}
