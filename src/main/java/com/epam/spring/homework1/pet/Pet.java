package com.epam.spring.homework1.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Pet implements Animal {

    @Autowired
    private List<Animal> animals;

    public Pet(List<Animal> animals){
        this.animals = animals;
    }

    public void printPets(){
        for(Animal e: animals)
            System.out.println(e.getClass().getSimpleName() );
    }

}
