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

                .route("reclamation-service", r -> r.path("/reclamation-service/**").uri("lb://reclamation-service"))
                .route("blog-service", r -> r.path("/blogs/**").uri("http://localhost:5000"))
                .route("blog-service", r -> r.path("/upload/**").uri("http://localhost:5000"))
                .route("uploads-route", r -> r.path("/reclamation-service/uploads/**")
                        .uri("lb://reclamation-service"))
                .route("projet-service", r -> r.path("/projet-service/**").uri("lb://projet-service"))


                .route("events-service", r -> r.path("/events-service/**").uri("lb://events-service"))

                .route("discovery-server", r -> r.path("/eureka/web").filters(f -> f.setPath("/")).uri("http://localhost:8761"))
                .route("discovery-server-static", r -> r.path("/eureka/**") .uri("http://localhost:8761"))
                .build();
    }

}


