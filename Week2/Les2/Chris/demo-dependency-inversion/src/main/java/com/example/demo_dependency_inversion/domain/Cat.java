package com.example.demo_dependency_inversion.domain;

public class Cat implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow Meow");
    }
}
