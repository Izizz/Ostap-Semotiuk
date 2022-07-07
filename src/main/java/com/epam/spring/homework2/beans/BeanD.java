package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BeanD extends BEAN {


    public BeanD(String name, int value) {
        super(name, value);
    }

    public void myInitDMethod(){
        System.out.println("BeanD custom init method");
    }
    public void myDestroyDMethod(){
        System.out.println("BeanD custom destroy method");
    }
}
