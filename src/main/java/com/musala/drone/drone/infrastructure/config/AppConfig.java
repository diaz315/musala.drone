package com.musala.drone.drone.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${drone.miliseconds.to.check.battery.task}")
    private String droneSecondsToCheckBatteryTask;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
