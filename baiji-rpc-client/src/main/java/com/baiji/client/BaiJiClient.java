package com.baiji.client;

import com.baiji.common.util.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class BaiJiClient {

    private static HttpClient client = null;

    private static volatile BaiJiClient instance;

    private BaiJiClient() {
    }

    private BaiJiClient(Integer connectTimeout) {
        client = HttpClient.newBuilder().connectTimeout(Duration.ofMillis(connectTimeout)).version(HttpClient.Version.HTTP_2).build();
    }

    public static final BaiJiClient getInstance(Integer connectTimeout) {
        if (instance == null) {
            synchronized (BaiJiClient.client) {
                if (instance == null) {
                    instance = new BaiJiClient(connectTimeout);
                }
            }
        }
        return instance;
    }

    public <Res, Req> Res doInvoke(String appid, String method, Req req, Integer requestTimeout) throws IOException, InterruptedException {
        HttpRequest build = HttpRequest.newBuilder()
                .uri(URI.create(appid))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofMillis(requestTimeout))
                .POST(HttpRequest.BodyPublishers.ofString(JsonUtils.serialize(req)))
                .build();
        HttpResponse<String> response = client.send(build, HttpResponse.BodyHandlers.ofString());
        return JsonUtils.deserialize(response.body(), new TypeReference<Res>() {
        });
    }
}
