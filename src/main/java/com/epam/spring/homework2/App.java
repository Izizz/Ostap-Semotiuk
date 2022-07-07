package com.epam.spring.homework2;

import com.epam.spring.homework2.beans.BEAN;
import com.epam.spring.homework2.beans.BeanB;
import com.epam.spring.homework2.config.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        List beans =  Arrays.asList(context.getBeanDefinitionNames());
        BeanB b = (BeanB) context.getBean("beanB");
        for(Object s : beans){
            if(context.getBean(s.toString()) instanceof  BEAN) {
                BEAN bean = (BEAN) context.getBean(s.toString());
                System.out.println(bean);
            }
        }
       ;

        context.close();

    }
}
