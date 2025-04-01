package com.baiji.server.bo;

import com.baiji.annotation.VerifyType;
import com.baiji.common.AuthInfo;

import java.lang.reflect.Method;

public class MethodWrapper {

    private Method method;
    private Object target;
    private VerifyType verifyType;
    private Class<? extends AuthInfo> reqCls;
    private Class<?> resCls;

    public MethodWrapper() {
    }

    public MethodWrapper(Method method, Object target, VerifyType verifyType, Class<? extends AuthInfo> reqCls, Class<?> resCls) {
        this.method = method;
        this.target = target;
        this.verifyType = verifyType;
        this.reqCls = reqCls;
        this.resCls = resCls;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public VerifyType getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(VerifyType verifyType) {
        this.verifyType = verifyType;
    }

    public Class<? extends AuthInfo> getReqCls() {
        return reqCls;
    }

    public void setReqCls(Class<? extends AuthInfo> reqCls) {
        this.reqCls = reqCls;
    }

    public Class<?> getResCls() {
        return resCls;
    }

    public void setResCls(Class<?> resCls) {
        this.resCls = resCls;
    }
}
