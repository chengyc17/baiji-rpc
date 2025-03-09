package com.baiji.ecust;

import com.baiji.client.BaiJiClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

public class GreeterClient {

    public static GreeterBuilder newBuilder() {
        return new GreeterBuilder();
    }

    private static Integer connectTimeout = 1000;
    private static Integer requestTimeout = 1000;
    private static ThreadPoolExecutor threadPoolExecutor;
    private static BaiJiClient client;

    private GreeterClient(GreeterBuilder builder) {
        if (builder.connectTimeout != null) {
            connectTimeout = builder.connectTimeout;
        }
        if (builder.requestTimeout != null) {
            requestTimeout=builder.requestTimeout;
        }
        if (builder.threadPoolExecutor != null) {
            threadPoolExecutor = builder.threadPoolExecutor;
        }
        client = BaiJiClient.getInstance(connectTimeout);
    }

    static GreeterClient create(GreeterBuilder builder) {
        return new GreeterClient(builder);
    }

    public HelloResponse sayHello(HelloRequest request)  throw Exception {
        client.doInvoke(1,002,sayHello,request,requestTimeout);
    }

    public HelloResponse sayHello(HelloRequest request, int requestTimeout)  throw Exception {
        client.doInvoke(1,002,sayHello,request,requestTimeout);
    }

    public CompletableFuture<HelloResponse> sayHelloAysnc(HelloRequest request)  throws Exception {
        return threadPoolExecutor == null ?
            CompletableFuture.supplyAsync(() -> sayHello(request)) :
            CompletableFuture.supplyAsync(() -> sayHello(request), threadPoolExecutor);
    }

    public CompletableFuture<HelloResponse> sayHelloAysnc(HelloRequest request, ThreadPoolExecutor threadPoolExecutor)  throw Exception {
        return threadPoolExecutor == null ?
            CompletableFuture.supplyAsync(() -> sayHello(request)) :
            CompletableFuture.supplyAsync(() -> sayHello(request), threadPoolExecutor);
    }
    public Hello2Response sayHello2(Hello2Request request)  throw Exception {
        client.doInvoke(1,002,sayHello2,request,requestTimeout);
    }

    public Hello2Response sayHello2(Hello2Request request, int requestTimeout)  throw Exception {
        client.doInvoke(1,002,sayHello2,request,requestTimeout);
    }

    public CompletableFuture<Hello2Response> sayHello2Aysnc(Hello2Request request)  throw Exception {
        return threadPoolExecutor == null ?
            CompletableFuture.supplyAsync(() -> sayHello2(request)) :
            CompletableFuture.supplyAsync(() -> sayHello2(request), threadPoolExecutor);
    }

    public CompletableFuture<Hello2Response> sayHello2Aysnc(Hello2Request request, ThreadPoolExecutor threadPoolExecutor)  throw Exception {
        return threadPoolExecutor == null ?
            CompletableFuture.supplyAsync(() -> sayHello2(request)) :
            CompletableFuture.supplyAsync(() -> sayHello2(request), threadPoolExecutor);
    }
}

interface Builder {
    Builder connectTimeout(Integer millionSec);

    Builder requestTimeout(Integer millionSec);

    Builder threadPoolExecutor(ThreadPoolExecutor threadPoolExecutor);

    GreeterClient build();
}

class GreeterBuilder implements Builder {

    Integer connectTimeout;
    Integer requestTimeout;
    ThreadPoolExecutor threadPoolExecutor;

    @Override
    public Builder connectTimeout(Integer millionSec) {
        if (millionSec <= 0) {
            throw new IllegalArgumentException("connectTimeout must greater than zero");
        }
        this.connectTimeout = connectTimeout;
        return this;
    }

    @Override
    public Builder requestTimeout(Integer millionSec) {
        if (millionSec <= 0) {
            throw new IllegalArgumentException("requestTimeout must greater than zero");
        }
        this.requestTimeout = connectTimeout;
        return this;
    }

    @Override
    public Builder threadPoolExecutor(ThreadPoolExecutor threadPoolExecutor) {
        if (threadPoolExecutor == null) {
            throw new IllegalArgumentException("threadPoolExecutor can not be null");
        }
        this.threadPoolExecutor = threadPoolExecutor;
        return this;
    }

    @Override
    public GreeterClient build() {
        return GreeterClient.create(this);
    }
}
