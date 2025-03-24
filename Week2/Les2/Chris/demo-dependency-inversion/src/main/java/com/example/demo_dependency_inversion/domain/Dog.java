package com.example.demo_dependency_inversion.domain;

public class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof Woof");
    }
}
