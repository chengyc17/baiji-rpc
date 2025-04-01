package com.baiji.common;

import java.util.Map;

public class AuthInfo {
    private String ticket;
    private String ver;
    private String token;
    private Map<String, String> ext;

    public AuthInfo() {
    }

    public AuthInfo(String ticket, String ver, String token, Map<String, String> ext) {
        this.ticket = ticket;
        this.ver = ver;
        this.token = token;
        this.ext = ext;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, String> getExt() {
        return ext;
    }

    public void setExt(Map<String, String> ext) {
        this.ext = ext;
    }
}
