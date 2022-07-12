package com.epam.spring.homework2.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationCustomBeanPostProccesor implements BeanPostProcessor {

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof BeanMain){
            if(((BeanMain) bean).getName() != null && ((BeanMain) bean).getValue() >= 0 ){
                System.out.println( ((BeanMain) bean).getName() + " ->Is valid");
            }
            else {
                System.out.println(((BeanMain) bean).getName() + " ->Is invalid");
            }
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

}
