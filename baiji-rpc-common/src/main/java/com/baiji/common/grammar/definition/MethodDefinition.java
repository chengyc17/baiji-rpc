package com.baiji.common.grammar.definition;

public class MethodDefinition {
    private String reqType;
    private String reqName;
    private String resType;
    private String methodName;

    public MethodDefinition() {
    }

    public MethodDefinition(String reqType, String reqName, String resType, String methodName) {
        this.reqType = reqType;
        this.reqName = reqName;
        this.resType = resType;
        this.methodName = methodName;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getReqName() {
        return reqName;
    }

    public void setReqName(String reqName) {
        this.reqName = reqName;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
