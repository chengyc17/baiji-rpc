package com.baiji.grammar;

import java.util.List;

public class ServiceInfoDefinition {
    private String serviceName;
    private List<MethodDefinition> methodDefinitionList;

    public ServiceInfoDefinition() {
    }

    public ServiceInfoDefinition(String serviceName, List<MethodDefinition> methodDefinitionList) {
        this.serviceName = serviceName;
        this.methodDefinitionList = methodDefinitionList;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<MethodDefinition> getMethodDefinitionList() {
        return methodDefinitionList;
    }

    public void setMethodDefinitionList(List<MethodDefinition> methodDefinitionList) {
        this.methodDefinitionList = methodDefinitionList;
    }
}
