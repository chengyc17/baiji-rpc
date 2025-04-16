package com.baiji.config;

import java.lang.reflect.Field;

public class FieldWrapper<T> {
    private Field field;
    private T bean;

    public FieldWrapper(Field field, T bean) {
        this.field = field;
        this.bean = bean;
    }

    public FieldWrapper() {
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }
}
