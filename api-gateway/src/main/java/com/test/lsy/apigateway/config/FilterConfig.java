package com.test.lsy.apigateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

//@Configuration
@Slf4j
public class FilterConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // user Route
                .route("user", r -> r.path("/user/**")
                        .filters(f -> f.modifyResponseBody(String.class, String.class, (exchange, body) -> {
                            String upperCase = body.toUpperCase();
                            return Mono.just(upperCase);
                        }))
                        .uri("http://localhost:8081"))
                // prod Route
                .route("prod", r -> r.path("/prod/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}
