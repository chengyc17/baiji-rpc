<#setting number_format="0">
<#function addQuotes text>
    <#return '"' + text + '"'>
</#function>

package ${packageName};

import com.baiji.client.BaiJiClient;

import java.time.Duration;

public class ${serviceName}Client {
    private static BaiJiClient client;

    public static ${serviceName}Builder builder() {
        return new ${serviceName}Builder();
    }

    private ${serviceName}Client(${serviceName}Builder builder) {
        BaiJiClient.Builder baijiBuilder = BaiJiClient.builder();
        if (builder.getConnectTimeout() != null) {
            baijiBuilder.connectTimeout(builder.getConnectTimeout());
        }
        if (builder.getReadTimeout() != null) {
            baijiBuilder.readTimeout(builder.getReadTimeout());
        }
        if (builder.getWriteTimeout() != null) {
            baijiBuilder.writeTimeout(builder.getWriteTimeout());
        }
        if (builder.getKeepAliveDuration() != null) {
            baijiBuilder.keepAliveDuration(builder.getKeepAliveDuration());
        }
        if (builder.getMaxIdleConnections() != null) {
            baijiBuilder.maxIdleConnections(builder.getMaxIdleConnections());
        }
        client = baijiBuilder.build();
    }

    static ${serviceName}Client create(${serviceName}Builder builder) {
        return new ${serviceName}Client(builder);
    }

    <#list methods as method>
    public ${method.resType} ${method.methodName}(${method.reqType} request) throws Exception {
        return client.doInvoke(${appid}, ${addQuotes(method.methodName)}, request);
    }
    </#list>
}

interface Builder {
    Builder connectTimeout(Duration connectTimeout);

    Builder readTimeout(Duration readTimeout);

    Builder writeTimeout(Duration writeTimeout);

    Builder maxIdleConnections(Integer maxIdleConnections);

    Builder keepAliveDuration(Duration keepAliveDuration);

    ${serviceName}Client build();
}

class ${serviceName}Builder implements Builder {

    private  Duration connectTimeout;
    private  Duration readTimeout;
    private  Duration writeTimeout;
    private  Integer maxIdleConnections;
    private  Duration keepAliveDuration;

    public Duration getConnectTimeout() {
        return connectTimeout;
    }

    public Duration getReadTimeout() {
        return readTimeout;
    }

    public Duration getWriteTimeout() {
        return writeTimeout;
    }

    public Integer getMaxIdleConnections() {
        return maxIdleConnections;
    }

    public Duration getKeepAliveDuration() {
        return keepAliveDuration;
    }

    @Override
    public Builder connectTimeout(Duration connectTimeout) {
        if (connectTimeout == null || connectTimeout.isNegative()) {
            throw new IllegalArgumentException("connectTimeout must greater than zero");
        }
        this.connectTimeout = connectTimeout;
        return this;
    }

    @Override
    public Builder readTimeout(Duration readTimeout) {
        if (readTimeout == null || readTimeout.isNegative()) {
            throw new IllegalArgumentException("connectTimeout must greater than zero");
        }
        this.readTimeout = readTimeout;
        return this;
    }

    @Override
    public Builder writeTimeout(Duration writeTimeout) {
        if (writeTimeout == null || writeTimeout.isNegative()) {
            throw new IllegalArgumentException("connectTimeout must greater than zero");
        }
        this.writeTimeout = writeTimeout;
        return this;
    }

    @Override
    public Builder maxIdleConnections(Integer maxIdleConnections) {
        if (maxIdleConnections == null || maxIdleConnections <= 0) {
            throw new IllegalArgumentException("connectTimeout must greater than zero");
        }
        this.maxIdleConnections = maxIdleConnections;
        return this;
    }

    @Override
    public Builder keepAliveDuration(Duration keepAliveDuration) {
        if (keepAliveDuration == null || keepAliveDuration.isNegative()) {
            throw new IllegalArgumentException("connectTimeout must greater than zero");
        }
        this.keepAliveDuration = keepAliveDuration;
        return this;
    }

    @Override
    public ${serviceName}Client build() {
        return ${serviceName}Client.create(this);
    }
}
