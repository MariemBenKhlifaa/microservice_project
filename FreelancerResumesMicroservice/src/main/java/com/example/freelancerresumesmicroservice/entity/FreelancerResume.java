package com.example.freelancerresumesmicroservice.entity;

import javax.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Entity
@Data
public class FreelancerResume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String name;
    private String email;
    private String professionTitle;
    private String location;
    private String web;
    private Double preHour;
    private Integer age;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
