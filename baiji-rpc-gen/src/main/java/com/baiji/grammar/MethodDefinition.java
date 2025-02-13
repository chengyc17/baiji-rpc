package com.baiji.grammar;

public class MethodDefinition {
    private String req;
    private String res;
    private String methodName;

    public MethodDefinition() {
    }

    public MethodDefinition(String req, String res, String methodName) {
        this.req = req;
        this.res = res;
        this.methodName = methodName;
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
