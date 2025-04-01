package com.baiji.server;

import com.baiji.annotation.VerifyType;
import com.baiji.common.AuthInfo;
import com.baiji.common.util.StringUtils;
import com.baiji.server.bo.MethodWrapper;
import com.baiji.spi.Verification;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

public class InterfacesInvoker<Res, Req extends AuthInfo> {

    private Verification verification;

    private Map<String, MethodWrapper> methodMap;

    public InterfacesInvoker() {
        initVerification();
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

    public InterfacesInvoker(Map<String, MethodWrapper> methodMap) {
        this.methodMap = methodMap;
    }

    public Res invoke(String methodName, Req req) throws Exception {
        if (methodMap != null && methodMap.containsKey(methodName)) {
            MethodWrapper methodWrapper = methodMap.get(methodName);
            Method method = methodWrapper.getMethod();
            Object target = methodWrapper.getTarget();
            tokenVerify(req, methodWrapper.getVerifyType());
            return (Res) method.invoke(target, req);
        }
        throw new IllegalArgumentException("interface definition not found");
    }

    private void tokenVerify(Req req, VerifyType verifyType) throws Exception {
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
}
