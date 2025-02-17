package com.baiji.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class BaiJiClient<Res, Req> {
    public static HttpClient client = HttpClient.newHttpClient();

    public Res doInvoke(String appid, String method, Req req) {
        HttpRequest.newBuilder().uri(URI.create(appid)).header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString())
    }
}
