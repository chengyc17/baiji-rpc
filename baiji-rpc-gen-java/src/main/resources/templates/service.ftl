<#setting number_format="0">

<#function addQuotes text>
    <#return '"' + text + '"'>
</#function>

package ${packageName};

import com.baiji.client.BaiJiClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

public class ${serviceName}Client {

    public static ${serviceName}Builder newBuilder() {
        return new ${serviceName}Builder();
    }

    private static Integer connectTimeout = 1000;
    private static Integer requestTimeout = 1000;
    private static ThreadPoolExecutor threadPoolExecutor;
    private static BaiJiClient client;

    private ${serviceName}Client(${serviceName}Builder builder) {
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

    static ${serviceName}Client create(${serviceName}Builder builder) {
        return new ${serviceName}Client(builder);
    }

<#list methods as method>
    public ${method.resType} ${method.methodName}(${method.reqType} request)  throws Exception {
        client.doInvoke(${appid},${addQuotes(method.methodName)}),request,requestTimeout);
    }

    public ${method.resType} ${method.methodName}(${method.reqType} request, int requestTimeout)  throws Exception {
        client.doInvoke(${appid},${addQuotes(method.methodName)},request,requestTimeout);
    }

    public CompletableFuture<${method.resType}> ${method.methodName}Async(${method.reqType} request)  throws Exception {
        return threadPoolExecutor == null ?
            CompletableFuture.supplyAsync(() -> client.doInvoke(${appid},${addQuotes(method.methodName)},request,requestTimeout)) :
            CompletableFuture.supplyAsync(() -> client.doInvoke(${appid},${addQuotes(method.methodName)},request,requestTimeout), threadPoolExecutor);
    }

    public CompletableFuture<${method.resType}> ${method.methodName}Async(${method.reqType} request, ThreadPoolExecutor threadPoolExecutor)  throws Exception {
        return threadPoolExecutor == null ?
            CompletableFuture.supplyAsync(() -> client.doInvoke(${appid},${addQuotes(method.methodName)},request,requestTimeout)) :
            CompletableFuture.supplyAsync(() -> client.doInvoke(${appid},${addQuotes(method.methodName)},request,requestTimeout), threadPoolExecutor);
    }
</#list>
}

interface Builder {
    Builder connectTimeout(Integer millionSec);

    Builder requestTimeout(Integer millionSec);

    Builder threadPoolExecutor(ThreadPoolExecutor threadPoolExecutor);

    ${serviceName}Client build();
}

class ${serviceName}Builder implements Builder {

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
    public ${serviceName}Client build() {
        return ${serviceName}Client.create(this);
    }
}
