package com.oilseller.oilbrocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableJpaRepositories(basePackages = {"com.oilseller.oilbrocker.product.dao", "com.oilseller.oilbrocker.user.dao", "com.oilseller.oilbrocker.order.dao", "com.oilseller.oilbrocker.history.dao"})
public class EasyOilApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyOilApplication.class, args);
	}
}
