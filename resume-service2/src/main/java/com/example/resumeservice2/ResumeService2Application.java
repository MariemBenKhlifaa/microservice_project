package com.example.resumeservice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@SpringBootApplication
@EnableEurekaClient
public class ResumeService2Application {

    public static void main(String[] args) {
        SpringApplication.run(ResumeService2Application.class, args);
    }

}
