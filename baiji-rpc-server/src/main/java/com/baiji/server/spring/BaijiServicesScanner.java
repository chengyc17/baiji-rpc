package com.baiji.server.spring;

import com.baiji.annotation.BaijiService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class BaijiServicesScanner implements BeanPostProcessor {

    private final List<Object> baijiServices = new LinkedList<>();

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(BaijiService.class) && bean.getClass().isAssignableFrom(com.baiji.common.BaijiService.class)) {
            baijiServices.add(bean);
        }
        return bean;
    }

    public List<Object> getBaijiServices() {
        return baijiServices;
    }
}
