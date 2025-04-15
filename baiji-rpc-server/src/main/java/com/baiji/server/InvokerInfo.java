package com.baiji.server;

public class InvokerInfo {
    private static ThreadLocal<String> INVOKER_APPID = new InheritableThreadLocal<>();
    private static ThreadLocal<String> INVOKER_IP = new InheritableThreadLocal<>();
    private static ThreadLocal<String> INVOKER_FROM = new InheritableThreadLocal<>();

    protected static void setInvokerAppid(String appid) {
        INVOKER_APPID.set(appid);
    }

    protected static void setInvokerIp(String ip) {
        INVOKER_IP.set(ip);
    }

    protected static void setInvokerFrom(String from) {
        INVOKER_FROM.set(from);
    }

    public static String getInvokerAppid() {
        return INVOKER_APPID.get();
    }

    public static void getInvokerIp() {
        INVOKER_IP.get();
    }

    public static void getInvokerFrom() {
        INVOKER_FROM.get();
    }

    public static void removeAll() {
        INVOKER_APPID.remove();
        INVOKER_IP.remove();
        INVOKER_FROM.remove();
    }
}
