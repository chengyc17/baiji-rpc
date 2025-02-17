package com.baiji.gen.java;


public class rrrr2 {serviceName}Impl implements ${serviceName} {

private static volatile ${serviceName}Impl instance;

    private volatile Integer connectTimeout;
    private volatile Integer requestTimeout;

    private volatile ThreadPoolExecutor threadPoolExecutor;

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public void setThreadPoolExecutor(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }


    public static final ${serviceName}Impl getInstance() {
        if (instance == null) {
            synchronized (${serviceName}Impl.class) {
                if (instance == null) {
                    instance = new ${serviceName}Impl();
                }
            }
        }
        return instance;
    }

//        ${method.responseType} ${method.name}(${method.requestType} request);

<#list serviceMethods as method>
    @Override
    public ${method.responseType} ${method.name}(${method.requestType} request) {

    }

    public CompletableFuture<${method.responseType}> ${method.name}Aysnc() {
        return threadPoolExecutor == null ?
                CompletableFuture.supplyAsync(() -> s1()).orTimeout(timeout, TimeUnit.MILLISECONDS) :
                CompletableFuture.supplyAsync(() -> s1(), threadPoolExecutor).orTimeout(timeout, TimeUnit.MILLISECONDS);
    }

</#list>



}
