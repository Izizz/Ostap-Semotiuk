package com.epam.spring.homework2;

import com.epam.spring.homework2.beans.BeanB;
import com.epam.spring.homework2.config.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Arrays.asList(context.getBeanDefinitionNames()).forEach(System.out::println);

    }
}
