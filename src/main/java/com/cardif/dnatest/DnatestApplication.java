package com.cardif.dnatest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DnatestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DnatestApplication.class, args);
	}

}
