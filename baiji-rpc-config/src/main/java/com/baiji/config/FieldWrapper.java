package com.baiji.config;

import java.lang.reflect.Field;

public class FieldWrapper<T> {
    private Field field;
    private T obj;

    public FieldWrapper(Field field, T obj) {
        this.field = field;
        this.obj = obj;
    }

    public FieldWrapper() {
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
