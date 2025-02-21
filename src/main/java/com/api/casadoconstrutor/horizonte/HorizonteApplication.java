package com.api.casadoconstrutor.horizonte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HorizonteApplication {

	public static void main(String[] args) {
		SpringApplication.run(HorizonteApplication.class, args);
	}

}
