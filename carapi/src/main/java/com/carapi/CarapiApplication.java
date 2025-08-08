package com.carapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@RestController
public class CarapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarapiApplication.class, args);
	}

	@GetMapping("/hello")
	public String sayHello(){
		return "Hello, welcome to the Car API!";
	}
}
