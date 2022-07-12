package com.epam.spring.homework2.beans;

public class BeanMain {

    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public BeanMain(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "BEAN{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
