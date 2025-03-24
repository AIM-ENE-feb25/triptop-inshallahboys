package com.example.demo_dependency_inversion.domain;

public class Bird implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Tweet Tweet");
    }
}
