package com.oilseller.oilbrocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class EasyOilApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyOilApplication.class, args);
	}
}
