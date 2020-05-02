package com.js.appointment.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.js.appointment.dao",
        "com.js.appointment.repository"
})
public class TestConfig {
}
