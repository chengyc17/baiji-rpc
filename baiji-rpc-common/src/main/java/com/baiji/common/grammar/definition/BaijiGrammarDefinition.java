package com.baiji.common.grammar.definition;

import java.util.List;

public class BaijiGrammarDefinition {
    private String syntax;
    private String packageName;
    private Integer appid;

    private List<ClassInfoDefinition> clsInfos;
    private ServiceInfoDefinition serviceInfo;

    public BaijiGrammarDefinition() {
    }

    public BaijiGrammarDefinition(String syntax, String packageName, Integer appid, List<ClassInfoDefinition> clsInfos, ServiceInfoDefinition serviceInfo) {
        this.syntax = syntax;
        this.packageName = packageName;
        this.appid = appid;
        this.clsInfos = clsInfos;
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
        return clsInfos;
    }

    public void setClsInfo(List<ClassInfoDefinition> clsInfo) {
        this.clsInfos = clsInfo;
    }

    public ServiceInfoDefinition getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(ServiceInfoDefinition serviceInfo) {
        this.serviceInfo = serviceInfo;
    }
}
