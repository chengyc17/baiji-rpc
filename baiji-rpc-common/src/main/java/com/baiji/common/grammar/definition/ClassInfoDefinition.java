package com.baiji.common.grammar.definition;

import java.util.List;

public class ClassInfoDefinition {
    private String clsName;
    private List<FieldInfoDefinition> fieldInfos;

    public ClassInfoDefinition() {
    }

    public ClassInfoDefinition(String clsName, List<FieldInfoDefinition> fieldInfos) {
        this.clsName = clsName;
        this.fieldInfos = fieldInfos;
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public List<FieldInfoDefinition> getFieldInfos() {
        return fieldInfos;
    }

    public void setFieldInfos(List<FieldInfoDefinition> fieldInfos) {
        this.fieldInfos = fieldInfos;
    }
}
