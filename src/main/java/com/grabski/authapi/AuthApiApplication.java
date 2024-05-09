package com.grabski.authapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApiApplication.class, args);

		// TODO Create a User Response and return it in User Controller
		// TODO Create products/categories api, register in eureka and create token validation in there.
	}

}
