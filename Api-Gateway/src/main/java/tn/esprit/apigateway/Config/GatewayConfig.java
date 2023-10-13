package tn.esprit.apigateway.Config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {


    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder,  FilterAuthentificate filterAuthentificate) {
        return builder.routes()
                .route("formation-service", r -> r.path("/formation-service/**").uri("lb://formation-service"))
                .route("projet-service", r -> r.path("/projet-service/**").uri("lb://projet-service"))
                .route("discovery-server", r -> r.path("/eureka/web").filters(f -> f.setPath("/")).uri("http://localhost:8761"))
                .route("discovery-server-static", r -> r.path("/eureka/**") .uri("http://localhost:8761"))
                .build();
    }

}


