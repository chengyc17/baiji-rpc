package com.baiji.grammar;

public class ClassInfoDefinition {
    private String dataType;
    private String fieldName;
    private String comment;

    public ClassInfoDefinition() {
    }

    public ClassInfoDefinition(String dataType, String fieldName, String comment) {
        this.dataType = dataType;
        this.fieldName = fieldName;
        this.comment = comment;
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
}
