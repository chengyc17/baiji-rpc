package com.baiji.client;

public class InvokeResult {

    private String currDate;
    private Integer code;
    private String msg;

    public InvokeResult() {
    }

    public InvokeResult(String currDate, Integer code, String msg) {
        this.currDate = currDate;
        this.code = code;
        this.msg = msg;
    }

    public String getCurrDate() {
        return currDate;
    }

    public void setCurrDate(String currDate) {
        this.currDate = currDate;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
