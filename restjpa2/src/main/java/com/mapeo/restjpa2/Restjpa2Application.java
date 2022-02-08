package com.mapeo.restjpa2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EntityScan(basePackages = {"com.mapeo.entity.entity"})
public class Restjpa2Application {

	public static void main(String[] args) {
		SpringApplication.run(Restjpa2Application.class, args);
	}

}
