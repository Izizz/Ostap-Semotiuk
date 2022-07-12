package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanD extends BeanMain {

    public BeanD(String name, int value) {
        super(name, value);
    }

    public void myInitDMethod() {
        System.out.println("BeanD custom init method");
    }

    public void myDestroyDMethod() {
        System.out.println("BeanD custom destroy method");
    }
}
