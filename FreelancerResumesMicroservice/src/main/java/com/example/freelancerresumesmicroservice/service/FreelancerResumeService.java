package com.example.freelancerresumesmicroservice.service;

import com.example.freelancerresumesmicroservice.entity.FreelancerResume;
import com.example.freelancerresumesmicroservice.repo.FreelancerResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FreelancerResumeService {

    @Autowired
    private FreelancerResumeRepository freelancerResumeRepository;

    public FreelancerResume createFreelancerResume(FreelancerResume freelancerResume) {
        // You can add any business logic or validation here before saving.
        return freelancerResumeRepository.save(freelancerResume);
    }

    public List<FreelancerResume> getAllFreelancerResumes() {
        return freelancerResumeRepository.findAll();
    }

    public FreelancerResume getFreelancerResumeById(Long id) {
        return freelancerResumeRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public FreelancerResume updateFreelancerResume(Long id, FreelancerResume updatedResume) {
        FreelancerResume existingResume = getFreelancerResumeById(id);
        // You can update specific fields here.
        existingResume.setName(updatedResume.getName());
        existingResume.setEmail(updatedResume.getEmail());
        // Update other fields as needed.
        return freelancerResumeRepository.save(existingResume);
    }

    public void deleteFreelancerResume(Long id) {
        FreelancerResume existingResume = getFreelancerResumeById(id);
        freelancerResumeRepository.delete(existingResume);
    }
}
