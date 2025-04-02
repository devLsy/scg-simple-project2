package com.test.lsy.apigateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

//@Configuration
@Slf4j
public class FilterConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // user Route
                .route("user", r -> r.path("/user/**")
                        .uri("http://localhost:8081"))
                // prod Route
                .route("prod", r -> r.path("/prod/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}
