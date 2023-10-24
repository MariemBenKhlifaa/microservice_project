package com.example.freelancerresumesmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FreelancerResumesMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreelancerResumesMicroserviceApplication.class, args);
    }

}
