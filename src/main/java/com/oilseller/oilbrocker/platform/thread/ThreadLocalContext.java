package com.oilseller.oilbrocker.platform.thread;

import com.oilseller.oilbrocker.platform.dto.Context;

public class ThreadLocalContext {

    private static ThreadLocal<Context> threadLocal = new ThreadLocal<>();

    public static void setContext(Context context) {
        threadLocal.set(context);
    }

    public static String getUser() {
        return threadLocal.get().getUsername();
    }

    public static String getAccessToken(){
        return threadLocal.get().getAccessToken();
    }

    public static void clear() {
        threadLocal.set(null);
    }
}
