package com.example.freelancerresumesmicroservice.repo;

import com.example.freelancerresumesmicroservice.entity.FreelancerResume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FreelancerResumeRepository extends JpaRepository<FreelancerResume, Long> {

    List<FreelancerResume> findByUserId(Long userId);
}