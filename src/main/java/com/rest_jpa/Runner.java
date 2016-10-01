package com.rest_jpa;

import com.rest_jpa.utils.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Runner {

	public static void main(String[] args) {
		SpringApplication.run(new Class<?>[] {Runner.class, WebConfig.class}, args);
	}
}
