package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class BeanA extends BeanMain implements InitializingBean, DisposableBean {

    public BeanA(String name, int value) {
        super(name,value);
    }

    @Override
    public void destroy() {
        System.out.println("DisposableBean -> BeanA destroy method");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("InitializingBean -> BeanA afterPropertiesSet method");
    }
}
