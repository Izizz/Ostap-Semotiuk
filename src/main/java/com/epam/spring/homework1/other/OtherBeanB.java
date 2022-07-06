package com.epam.spring.homework1.other;

import com.epam.spring.homework1.beans.BeanB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OtherBeanB {

    private BeanB bean;

    @Autowired
    public void setBean(BeanB beanB) {
        this.bean = beanB;
        System.out.println(this.getClass().getSimpleName() + ", " + bean.getClass().getSimpleName() + "was injected through the constructor");
    }
}
