package com.baiji.grammar;

import java.util.List;

public class BaijiGrammarDefinition {
    private String syntax;
    private String packageName;
    private Integer appid;

    private List<ClassInfoDefinition> clsInfo;
    private ServiceInfoDefinition serviceInfo;

    public BaijiGrammarDefinition() {
    }

    public BaijiGrammarDefinition(String syntax, String packageName, Integer appid, List<ClassInfoDefinition> clsInfo, ServiceInfoDefinition serviceInfo) {
        this.syntax = syntax;
        this.packageName = packageName;
        this.appid = appid;
        this.clsInfo = clsInfo;
        this.serviceInfo = serviceInfo;
    }

    public String getSyntax() {
        return syntax;
    }

    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public List<ClassInfoDefinition> getClsInfo() {
        return clsInfo;
    }

    public void setClsInfo(List<ClassInfoDefinition> clsInfo) {
        this.clsInfo = clsInfo;
    }

    public ServiceInfoDefinition getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(ServiceInfoDefinition serviceInfo) {
        this.serviceInfo = serviceInfo;
    }
}
