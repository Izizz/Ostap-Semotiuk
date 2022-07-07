package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BeanC extends BEAN {


    public BeanC(String name, int value) {
       super(name, value);
    }

    public void myInitCMethod(){
        System.out.println("BeanC custom init method");
    }
    public void myDestroyCMethod(){
        System.out.println("BeanC custom destroy method");
    }
}
