package com.codecool.fittinder.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

@EnableWebSecurity
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/api/**").permitAll();
    }

    @Bean
    public SimpleMailMessage templateSimpleMessage() throws FileNotFoundException {
        String path = Paths.get("").toAbsolutePath().normalize().toString();
        SimpleMailMessage message = new SimpleMailMessage();
        Scanner scanner = new Scanner(new File(path + "/backend/src/main/resources/templates/welcome_email.html"));
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();
        message.setText(text);
        return message;
    }
}
