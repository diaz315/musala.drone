package com.musala.drone.drone.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
    @Bean
    public GroupedOpenApi api()
    {
        return GroupedOpenApi.builder()
                .group("OpenApiController")
                .packagesToScan("com.drones.handledrones")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Handle Drone Spring Boot 3 API")
                        .version("1")
                        .description("")
                        .termsOfService("")
                        .license(new License().name("Apache 2.6").url("http://springdoc.org")));
    }
}