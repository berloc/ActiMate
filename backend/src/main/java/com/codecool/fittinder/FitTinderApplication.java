package com.codecool.fittinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync(proxyTargetClass = true)
@EnableScheduling
@SpringBootApplication
public class FitTinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitTinderApplication.class, args);
	}

	// todo use aspect for debugging
}
