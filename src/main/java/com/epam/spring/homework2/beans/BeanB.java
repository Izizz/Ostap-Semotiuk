package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanB extends BeanMain {

    public BeanB (String name,int value){
        super(name,value);
    }

    public void myInitBMethod() {
        System.out.println("BeanB custom init method");
    }
    public void changedInitMethod() {
        System.out.println("BeanB new changed InitMethod()");
    }
    public void myDestroyBMethod() {
        System.out.println("BeanB custom destroy method");
    }
}
