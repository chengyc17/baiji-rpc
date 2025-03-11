
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

    public HelloResponse sayHello(HelloRequest request)  throws Exception {
    client.doInvoke(1002,"sayHello"),request,requestTimeout);
    }

    public HelloResponse sayHello(HelloRequest request, int requestTimeout)  throws Exception {
    client.doInvoke(1002,"sayHello",request,requestTimeout);
    }

    public CompletableFuture<HelloResponse> sayHelloAsync(HelloRequest request)  throws Exception {
    return threadPoolExecutor == null ?
    CompletableFuture.supplyAsync(() -> client.doInvoke(1002,"sayHello",request,requestTimeout)) :
    CompletableFuture.supplyAsync(() -> client.doInvoke(1002,"sayHello",request,requestTimeout), threadPoolExecutor);
    }

    public CompletableFuture<HelloResponse> sayHelloAsync(HelloRequest request, ThreadPoolExecutor threadPoolExecutor)  throws Exception {
    return threadPoolExecutor == null ?
    CompletableFuture.supplyAsync(() -> client.doInvoke(1002,"sayHello",request,requestTimeout)(request)) :
    CompletableFuture.supplyAsync(() -> client.doInvoke(1002,"sayHello",request,requestTimeout), threadPoolExecutor);
    }
    public Hello2Response sayHello2(Hello2Request request)  throws Exception {
    client.doInvoke(1002,"sayHello2"),request,requestTimeout);
    }

    public Hello2Response sayHello2(Hello2Request request, int requestTimeout)  throws Exception {
    client.doInvoke(1002,"sayHello2",request,requestTimeout);
    }

    public CompletableFuture<Hello2Response> sayHello2Async(Hello2Request request)  throws Exception {
    return threadPoolExecutor == null ?
    CompletableFuture.supplyAsync(() -> client.doInvoke(1002,"sayHello2",request,requestTimeout)) :
    CompletableFuture.supplyAsync(() -> client.doInvoke(1002,"sayHello2",request,requestTimeout), threadPoolExecutor);
    }

    public CompletableFuture<Hello2Response> sayHello2Async(Hello2Request request, ThreadPoolExecutor threadPoolExecutor)  throws Exception {
    return threadPoolExecutor == null ?
    CompletableFuture.supplyAsync(() -> client.doInvoke(1002,"sayHello2",request,requestTimeout)(request)) :
    CompletableFuture.supplyAsync(() -> client.doInvoke(1002,"sayHello2",request,requestTimeout), threadPoolExecutor);
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
