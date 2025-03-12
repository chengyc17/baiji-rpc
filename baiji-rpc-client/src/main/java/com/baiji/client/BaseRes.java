package com.baiji.client;

public class BaseRes {
    private InvokeResult invokeResult;

    public BaseRes() {
    }

    public BaseRes(InvokeResult invokeResult) {
        this.invokeResult = invokeResult;
    }

    public InvokeResult getInvokeResult() {
        return invokeResult;
    }

    public void setInvokeResult(InvokeResult invokeResult) {
        this.invokeResult = invokeResult;
    }
}
