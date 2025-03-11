package com.baiji.client;


import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaiJiClient {
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);
    private static final Duration DEFAULT_KEEPALIVEDURATION = Duration.ofMinutes(3);
    public Duration connectTimeout;
    public Duration readTimeout;
    public Duration writeTimeout;
    //最大连接数
    public Integer maxIdleConnections;
    //空闲连接存活时间
    public Duration keepAliveDuration;
    private ConnectionPool connectionPool;
    private OkHttpClient client;


    private BaiJiClient(Builder builder) {
        this.connectTimeout = builder.connectTimeout == null ? DEFAULT_TIMEOUT : builder.connectTimeout;
        this.readTimeout = builder.readTimeout == null ? DEFAULT_TIMEOUT : builder.readTimeout;
        this.writeTimeout = builder.writeTimeout == null ? DEFAULT_TIMEOUT : builder.writeTimeout;
        this.maxIdleConnections = builder.maxIdleConnections == null ? Runtime.getRuntime().availableProcessors() * 2 : builder.maxIdleConnections;
        this.keepAliveDuration = builder.keepAliveDuration == null ? DEFAULT_KEEPALIVEDURATION : builder.keepAliveDuration;

        connectionPool = new ConnectionPool(maxIdleConnections, keepAliveDuration.toMillis(), TimeUnit.MILLISECONDS);

        client = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout.toMillis(), TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout.toMillis(), TimeUnit.MILLISECONDS)
                .writeTimeout(writeTimeout.toMillis(), TimeUnit.MILLISECONDS)
                .connectionPool(connectionPool)
                .build();
    }

    public <Res, Req> Res doInvoke(Integer appid, String methodName, Req request) {
        client.newCall()

    }

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

    public static class Builder {
        private Duration connectTimeout;
        private Duration readTimeout;
        private Duration writeTimeout;
        private Integer maxIdleConnections;
        private Duration keepAliveDuration;

        // 设置连接超时时间
        public Builder connectTimeout(Duration connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        // 设置读取超时时间
        public Builder readTimeout(Duration readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        // 设置写入超时时间
        public Builder writeTimeout(Duration writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        // 设置最大空闲连接数
        public Builder maxIdleConnections(Integer maxIdleConnections) {
            this.maxIdleConnections = maxIdleConnections;
            return this;
        }

        // 设置空闲连接存活时间
        public Builder keepAliveDuration(Duration keepAliveDuration) {
            this.keepAliveDuration = keepAliveDuration;
            return this;
        }

        // 构建 BaiJiClient 实例
        public BaiJiClient build() {
            return new BaiJiClient(this);
        }
    }
}
