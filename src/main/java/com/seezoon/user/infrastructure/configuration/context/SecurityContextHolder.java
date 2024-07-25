package com.seezoon.user.infrastructure.configuration.context;

public class SecurityContextHolder {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static Integer getUid() {
        return threadLocal.get();
    }

    public static void setUid(Integer uid) {
        threadLocal.set(uid);
    }

    public static void clear() {
        threadLocal.remove();
    }
}
