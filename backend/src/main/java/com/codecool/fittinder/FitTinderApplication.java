package com.codecool.fittinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync(proxyTargetClass = true)
@SpringBootApplication
public class FitTinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitTinderApplication.class, args);
	}
}
