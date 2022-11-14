package com.insmart.app;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group("public-api").pathsToMatch("/api/token/get","/api/user/login").build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/api/user/logout/**","/api/user/get/**","/api/user/get","/api/user/create","/api/user/delete/**","/api/user/update/**","/api/user/f/**","/api/user/deleteAll/**")
                .build();
    }
}