package com.example.demo_dependency_inversion.domain;

public class Zoo {
    private final Animal animal;

    public Zoo(Animal animal) {
        this.animal = animal;
    }

    public void makeSound() {
        animal.makeSound();
    }
}
