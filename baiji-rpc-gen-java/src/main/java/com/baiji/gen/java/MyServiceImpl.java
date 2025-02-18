package com.baiji.gen.java;

import com.baiji.client.BaiJiClient;

import java.net.http.HttpClient;
import java.util.concurrent.ThreadPoolExecutor;

public class MyServiceImpl {
    private static volatile MyServiceImpl instance;

    private volatile Integer connectTimeout = 1000;
    private volatile Integer requestTimeout = 1000;

    private volatile ThreadPoolExecutor threadPoolExecutor;

    private BaiJiClient client = BaiJiClient.getInstance(connectTimeout);

    private MyServiceImpl(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setRequestTimeout(Integer requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public void setThreadPoolExecutor(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    private MyServiceImpl(){}

    private MyServiceImpl(){

    }

    public static final MyServiceImpl getInstance() {
        if (instance == null) {
            synchronized (MyServiceImpl.class) {
                if (instance == null) {
                    instance = new MyServiceImpl();
                }
            }
        }
        return instance;
    }


//    @Override
//    public ${method.responseType} ${method.name}(${method.requestType} request)  throw Exception {
//
//    }
//
//    public CompletableFuture<${method.responseType}> ${method.name}Aysnc(${method.requestType} request)  throw Exception {
//        return threadPoolExecutor == null ?
//                CompletableFuture.supplyAsync(() -> ${method.name}(request)) :
//        CompletableFuture.supplyAsync(() -> ${method.name}(request), threadPoolExecutor);
//    }
}
