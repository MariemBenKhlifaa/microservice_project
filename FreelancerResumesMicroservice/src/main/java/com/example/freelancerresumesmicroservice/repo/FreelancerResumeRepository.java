package com.example.freelancerresumesmicroservice.repo;

import com.example.freelancerresumesmicroservice.entity.FreelancerResume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreelancerResumeRepository extends JpaRepository<FreelancerResume, Long> {
}