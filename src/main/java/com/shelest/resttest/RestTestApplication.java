package com.shelest.resttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.shelest.resttest"})
public class RestTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestTestApplication.class, args);
	}

}
