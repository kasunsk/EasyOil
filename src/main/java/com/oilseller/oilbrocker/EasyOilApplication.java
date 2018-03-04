package com.oilseller.oilbrocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.oilseller.oilbrocker.sellingItem.dao", "com.oilseller.oilbrocker.user.dao", "com.oilseller.oilbrocker.order.dao", "com.oilseller.oilbrocker.history.dao"})
public class EasyOilApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyOilApplication.class, args);
	}
}
