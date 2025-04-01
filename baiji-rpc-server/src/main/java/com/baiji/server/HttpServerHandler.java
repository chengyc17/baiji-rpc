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
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ChannelHandler.Sharable
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private Pattern pattern = java.util.regex.Pattern.compile("/api/([^/]+)");
    private Map<String, MethodWrapper> methodMap;
    private Verification verification;

    public HttpServerHandler(Map<String, MethodWrapper> methodMap) {
        initVerification();
        this.methodMap = methodMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        // 获取请求的 URI
        String uri = request.uri();
        // 解析 methodName
        String methodName = extractMethodName(uri);
        String res = doInvoke(methodName, request.content().toString(CharsetUtil.UTF_8));
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
//        Class<?> resCls = methodWrapper.getResCls();
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

    private <Res, Req extends AuthInfo> void tokenVerify(Req req, VerifyType verifyType) throws Exception {
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

    private String extractMethodName(String uri) {
        // 假设 URI 格式为 /api/methodName
        Matcher matcher = pattern.matcher(uri);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

