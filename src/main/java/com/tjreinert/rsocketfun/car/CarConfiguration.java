package com.tjreinert.rsocketfun.car;

import com.tjreinert.rsocketfun.config.Profiles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CarConfiguration {
    @Bean
    @Profile(Profiles.DEVELOP)
    public SecurityWebFilterChain devSecurityWebFilterChain(ServerHttpSecurity http) {
        return  http.authorizeExchange().anyExchange().permitAll().and().build();
    }

    @Bean
    @Profile(Profiles.NOT_DEVELOP)
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return  http.authorizeExchange().anyExchange().authenticated().and().build();
    }

    @Bean
    public RouterFunction<ServerResponse> route(CarService carService) {
        return RouterFunctions
                .route().GET("/car", carService::getCar)
                .build();
    }
}
