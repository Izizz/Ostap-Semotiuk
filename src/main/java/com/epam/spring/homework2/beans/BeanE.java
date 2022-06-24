package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BeanE extends BEAN  {

    public BeanE(String name, int value) {
       super(name,value);
    }



    @PostConstruct
    public void postConstruct(){
        System.out.println("BeanE -> PostConstruct method");
    }
    @PreDestroy
    public void preDestroy(){
        System.out.println("Bean -> PreDestroy method");
    }
}
