package ${packageName};

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

public class ${serviceName}Impl implements ${serviceName} {
    private static volatile ${serviceName}Impl instance;

    private volatile Integer connectTimeout = 1000;
    private volatile Integer requestTimeout = 1000;

    private volatile ThreadPoolExecutor threadPoolExecutor;

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setRequestTimeout(Integer requestTimeout) {
        this.requestTimeout = requestTimeout;
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

    <#list serviceMethods as method>

    @Override
    public ${method.responseType} ${method.name}(${method.requestType} request)  throw Exception {

    }

    public CompletableFuture<${method.responseType}> ${method.name}Aysnc(${method.requestType} request)  throw Exception {
        return threadPoolExecutor == null ?
                CompletableFuture.supplyAsync(() -> ${method.name}(request)) :
                CompletableFuture.supplyAsync(() -> ${method.name}(request), threadPoolExecutor);
    }
    </#list>
}
