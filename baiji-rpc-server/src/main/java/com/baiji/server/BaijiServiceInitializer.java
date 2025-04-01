package com.baiji.server;

import com.baiji.annotation.TokenVerification;
import com.baiji.annotation.VerifyType;
import com.baiji.common.AuthInfo;
import com.baiji.common.BaijiService;
import com.baiji.common.util.ArrayUtils;
import com.baiji.common.util.CollectionUtils;
import com.baiji.server.bo.MethodWrapper;
import com.baiji.server.spring.BaijiServicesScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

@Component
public class BaijiServiceInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private BaijiServicesScanner servicesScanner;

    private static Map<String, MethodWrapper> methodMap;

    //spring环境下，通过后置处理器扫描得到类对象和对象
    private <T extends BaijiService> void initMethodMap() {
        if (methodMap != null) {
            throw new RuntimeException("service already been initialized");
        }
        List<Object> baijiServices = servicesScanner.getBaijiServices();
        if (CollectionUtils.isEmpty(baijiServices)) {
            throw new RuntimeException("没有实现类被找到");
        }
        if (baijiServices.size() > 1) {
            throw new RuntimeException("多个实现类被找到，但只能有一个");
        }
        Object serviceTarget = baijiServices.get(0);
        Class<?>[] parentClasses = Optional.ofNullable(serviceTarget.getClass().getInterfaces()).orElse(null);

        if (parentClasses.length > 1) {
            throw new RuntimeException("不支持多个接口实现");
        }
        Arrays.stream(parentClasses[0].getMethods()).forEach(
                x -> {
                    Class<?> reqCls = x.getParameterTypes()[0];
                    if (!reqCls.isAssignableFrom(AuthInfo.class)) {
                        return;
                    }

                    Class<?> resCls = x.getReturnType();
                    TokenVerification annotation = x.getAnnotation(TokenVerification.class);
                    VerifyType value = annotation.value();
                    methodMap.put(x.getName(), new MethodWrapper(x, serviceTarget, value, (Class<? extends AuthInfo>) reqCls, resCls));
                }
        );
    }

    //非Spring环境下，通过反射的方式获取到对象，和Method对象进行执行；
    public static <T extends BaijiService> void initMethodMap(Class<T> cls) throws Exception {
        if (methodMap != null) {
            throw new IllegalAccessException("service already been initialized");
        }
        methodMap = new HashMap<>();
        Class<?>[] interfaces = cls.getInterfaces();
        if (ArrayUtils.isEmpty(interfaces)) {
            throw new IllegalArgumentException("should implement the service interface class");
        }
        //父接口集合
        List<Class<?>> serverCls = new ArrayList<>();
        for (Class<?> anInterface : interfaces) {
            if (BaijiService.class.isAssignableFrom(anInterface)) {
                serverCls.add(anInterface);
            }
        }
        if (serverCls.isEmpty()) {
            throw new IllegalArgumentException("should implement the service interface class");
        }
        //保证父接口中没有重载的情况
        if (serverCls.size() > 1) {
            throw new IllegalArgumentException("should implement only one service interface class");
        }
        Set<String> superMethodNames = new HashSet<>();
        for (Method method : serverCls.get(0).getMethods()) {
            String methodName = method.getName();
            if (superMethodNames.contains(methodName)) {
                throw new IllegalArgumentException("method override are not unsupported in interface class");
            }
            superMethodNames.add(methodName);
        }
        Set<String> methodNames = new HashSet<>();
        Constructor<T> constructor = cls.getDeclaredConstructor();
        T target = constructor.newInstance();
        for (Method method : cls.getMethods()) {
            String methodName = method.getName();
            if (methodNames.contains(methodName)) {
                //保证接口实现类中没有方法重载
                throw new IllegalArgumentException("method override are not unsupported");
            }
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1) {
                throw new IllegalArgumentException("multiple input parameter are not support, can only one parameter");
            }
            Class<?> parameterType = parameterTypes[0];
            if (!parameterType.isAssignableFrom(AuthInfo.class)) {
                throw new RuntimeException("input parameter must inherit AuthInfo.class");
            }
            Class<?> returnType = method.getReturnType();
            methodMap.put(methodName, new MethodWrapper(method, target, getVerifyType(method), (Class<? extends AuthInfo>) parameterType, returnType));
        }
        start();
    }

    private static VerifyType getVerifyType(Method method) {
        TokenVerification annotation = method.getAnnotation(TokenVerification.class);
        return annotation.value();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initMethodMap();
        try {
            start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void start() throws Exception {
        EmbeddingServer server = new EmbeddingServer(methodMap, 8080);
        server.run();
    }
}
