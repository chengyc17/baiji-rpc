package com.baiji.server;

import com.baiji.annotation.VerifyType;
import com.baiji.common.AuthInfo;
import com.baiji.common.util.JsonUtils;
import com.baiji.common.util.StringUtils;
import com.baiji.server.bo.MethodWrapper;
import com.baiji.spi.Verification;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.lang.reflect.Method;
import java.util.*;

@ChannelHandler.Sharable
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private Map<String, MethodWrapper> methodMap;
    private Verification verification;
    private Map<String, String> requestJson = new HashMap<>();

    private static final String API = "api";
    private static final String JSON = "json";
    private static final String ALL = "all";
    private static final String CHECK_HEALTH = "checkHealth";

    public HttpServerHandler(Map<String, MethodWrapper> methodMap) {
        initVerification();
        this.methodMap = methodMap;
        initRequestJson();
    }

    private void initRequestJson() {
        for (Map.Entry<String, MethodWrapper> entry : methodMap.entrySet()) {
            Class<? extends AuthInfo> reqClass = Optional.ofNullable(entry).map(x -> x.getValue()).map(x -> x.getReqCls()).orElse(null);
            if (reqClass == null) {
                throw new IllegalArgumentException("request class can not be null");
            }
            requestJson.put(entry.getKey(), JsonUtils.generateDefaultJson(reqClass));
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        try {
            setInvokerInfo(request);
            String uri = request.uri();
            List<String> typeAndMethodName = splitUri(uri);
            String type = typeAndMethodName.get(0);
            String methodName = typeAndMethodName.get(1);
            if (StringUtils.equalsIgnoreCase(type, API) && methodMap.containsKey(methodName)) {
                doResponse(ctx, request, doInvoke(methodName, request.content().toString(CharsetUtil.UTF_8)));
                return;
            }
            if (StringUtils.equalsIgnoreCase(type, API) && StringUtils.equalsIgnoreCase(methodName, CHECK_HEALTH)) {
                doResponse(ctx, request, StringUtils.Empty);
                return;
            }
            if (StringUtils.equalsIgnoreCase(type, JSON) && methodMap.containsKey(methodName)) {
                doResponse(ctx, request, requestJson.get(methodName));
                return;
            }
            if (StringUtils.equalsIgnoreCase(type, JSON) && StringUtils.equalsIgnoreCase(methodName, ALL)) {
                doResponse(ctx, request, JsonUtils.serialize(requestJson.keySet()));
                return;
            }
        } finally {
            InvokerInfo.removeAll();
        }
    }

    private void setInvokerInfo(FullHttpRequest request) {
        HttpHeaders headers = request.headers();

        // TODO: 2025/4/8 补全这两个key
        String remoteIP = headers.get("");
        String remoteAppid = headers.get("");
        String from = headers.get("");

        InvokerInfo.setInvokerIp(remoteIP);
        InvokerInfo.setInvokerAppid(remoteAppid);
        InvokerInfo.setInvokerFrom(from);
    }

    private List<String> splitUri(String uri) {
        List<String> res = new ArrayList<>();
        for (String s : uri.split("/+")) {
            if (s.length() == 0) {
                continue;
            }
            res.add(s);
        }
        return res;
    }

    private void doResponse(ChannelHandlerContext ctx, FullHttpRequest request, String res) {
        FullHttpResponse response = new DefaultFullHttpResponse(
                request.protocolVersion(),
                HttpResponseStatus.OK,
                Unpooled.copiedBuffer(res, CharsetUtil.UTF_8)
        );
        response.headers().set("Content-Type", "application/json; charset=UTF-8");
        response.headers().set("Content-Length", response.content().readableBytes());
        // 发送响应
        ctx.writeAndFlush(response);
    }

    private String doInvoke(String methodName, String reqStr) throws Exception {
        MethodWrapper methodWrapper = methodMap.get(methodName);
        Method method = methodWrapper.getMethod();
        Object target = methodWrapper.getTarget();
        Class<? extends AuthInfo> reqCls = methodWrapper.getReqCls();
        VerifyType verifyType = methodWrapper.getVerifyType();
        AuthInfo authInfo = JsonUtils.deserialize(reqStr, reqCls);
        tokenVerify(authInfo, verifyType);
        Object invoke = method.invoke(target, authInfo);
        return JsonUtils.serialize(invoke);
    }

    private void initVerification() {
        ServiceLoader<Verification> load = ServiceLoader.load(Verification.class);
        List<Verification> verificationList = new ArrayList<>();
        for (Verification verification1 : load) {
            verificationList.add(verification1);
        }
        if (verificationList.isEmpty()) {
            //todo 抛出异常，不可以为空
        }
        if (verificationList.size() > 1) {
            //todo 抛出异常，多个被发现
        }
        verification = verificationList.get(0);
    }

    private <Req extends AuthInfo> void tokenVerify(Req req, VerifyType verifyType) throws Exception {
        String token = req.getToken();
        if (verifyType == VerifyType.No_Need) {
            return;
        }
        if (verifyType == VerifyType.Must) {
            if (StringUtils.isBlank(token)) {
                throw new IllegalArgumentException("token can not be empty while VerifyType is Must");
            }
            verification.verify(req);
            return;
        }
        if (verifyType == VerifyType.On_Demand) {
            if (StringUtils.isBlank(token)) {
                return;
            }
            verification.verify(req);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

