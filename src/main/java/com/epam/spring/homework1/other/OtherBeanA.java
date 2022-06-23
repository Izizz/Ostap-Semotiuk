package com.epam.spring.homework1.other;

import com.epam.spring.homework1.beans.BeanA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OtherBeanA {
    BeanA bean;

    @Autowired
    public  OtherBeanA(BeanA beanA){
        this.bean = beanA;
        System.out.println(this.getClass().getSimpleName() + ", " + beanA.getClass().getSimpleName() + "was injected through the constructor");
    }
}
