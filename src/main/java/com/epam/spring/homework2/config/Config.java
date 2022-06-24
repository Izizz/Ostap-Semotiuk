package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;

@Configuration
@Import(ExtendedConfig.class)
@PropertySource("beans.properties")
@ComponentScan("com.epam.spring.homework2.beans")
public class Config {

    @Value("${beanB.name}")
    private String nameB;
    @Value("${beanC.name}")
    private String nameC;
    @Value("${beanD.name}")
    private String nameD;
    @Value("${beanB.value}")
    private int valueB;
    @Value("${beanC.value}")
    private int valueC;
    @Value("${beanD.value}")
    private int valueD;


    @Bean(initMethod = "myInitBMethod", destroyMethod = "myDestroyBMethod")
    @DependsOn("beanD")
    public BeanB beanB(){
        return new BeanB(nameB,valueB);
    }
    @Bean(initMethod = "myInitCMethod", destroyMethod = "myDestroyCMethod")
    @DependsOn("beanB")
    public BeanC beanC(){
        return new BeanC(nameC,valueC);
    }
    @Bean(initMethod = "myInitDMethod", destroyMethod = "myDestroyDMethod")

    public BeanD beanD(){
        return new BeanD(nameD,valueD);
    }

    @Bean
    @Lazy
    public BeanF beanF(){
        return  new BeanF("F",6);
    }

    @Bean
    public BeanA beanA(){
        return  new BeanA("aaa",-343333);
    }

    @Bean
    public BeanE beanE(){
        return  new BeanE("asdasdasd", 123123);
    }

}
