package com.example.freelancerresumesmicroservice.controller;

import com.example.freelancerresumesmicroservice.entity.FreelancerResume;
import com.example.freelancerresumesmicroservice.service.FreelancerResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/freelancer-resumes")
public class FreelancerResumeController {

    @Autowired
    private FreelancerResumeService freelancerResumeService;

    @PostMapping
    public ResponseEntity<FreelancerResume> createFreelancerResume(@RequestBody FreelancerResume freelancerResume) {
        FreelancerResume createdResume = freelancerResumeService.createFreelancerResume(freelancerResume);
        return new ResponseEntity<>(createdResume, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FreelancerResume>> getAllFreelancerResumes() {
        List<FreelancerResume> resumes = freelancerResumeService.getAllFreelancerResumes();
        return new ResponseEntity<>(resumes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FreelancerResume> getFreelancerResumeById(@PathVariable Long id) {
        FreelancerResume resume = freelancerResumeService.getFreelancerResumeById(id);
        return new ResponseEntity<>(resume, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FreelancerResume> updateFreelancerResume(@PathVariable Long id, @RequestBody FreelancerResume updatedResume) {
        FreelancerResume updated = freelancerResumeService.updateFreelancerResume(id, updatedResume);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFreelancerResume(@PathVariable Long id) {
        freelancerResumeService.deleteFreelancerResume(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
