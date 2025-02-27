package com.baiji.common.grammar.definition;

import java.util.List;

public class FieldInfoDefinition {
    private String dataType;
    private String fieldName;
    private String comment;
    private List<String> generics;

    public FieldInfoDefinition() {
    }

    public FieldInfoDefinition(String dataType, String fieldName, String comment, List<String> generics) {
        this.dataType = dataType;
        this.fieldName = fieldName;
        this.comment = comment;
        this.generics = generics;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getGenerics() {
        return generics;
    }

    public void setGenerics(List<String> generics) {
        this.generics = generics;
    }
}
