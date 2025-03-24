package com.example.demo_dependency_inversion;

import com.example.demo_dependency_inversion.domain.Bird;
import com.example.demo_dependency_inversion.domain.Cat;
import com.example.demo_dependency_inversion.domain.Dog;
import com.example.demo_dependency_inversion.domain.Zoo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoDependencyInversionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoDependencyInversionApplication.class, args);

		Zoo dogZoo = new Zoo(new Dog());
		Zoo catZoo = new Zoo(new Cat());
		Zoo birdZoo = new Zoo(new Bird());

		dogZoo.makeSound();  // Woof! Woof!
		catZoo.makeSound();  // Meow! Meow!
		birdZoo.makeSound(); // Chirp! Chirp!
	}

}
