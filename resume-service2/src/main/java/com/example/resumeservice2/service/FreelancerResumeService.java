package com.example.resumeservice2.service;

import com.example.resumeservice2.entity.FreelancerResume;
import com.example.resumeservice2.repo.FreelancerResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
        List<FreelancerResume> resumes = freelancerResumeRepository.findByUserId(id);

        if (resumes.isEmpty()) {
            // Handle the case where no resume was found for the provided userId
            // You can throw an exception or return a default value or response
            // For example, you can throw a NotFoundException
           return null;
        }

        // Return the first (and presumably only) resume in the list
        return resumes.get(0);

    }

    public FreelancerResume updateFreelancerResume(Long id, FreelancerResume updatedResume) {
        FreelancerResume existingResume = getFreelancerResumeById(id);

        if (existingResume != null) {
            // Update all fields from updatedResume
            existingResume.setName(updatedResume.getName());
            existingResume.setEmail(updatedResume.getEmail());
            existingResume.setAge(updatedResume.getAge());
            existingResume.setLocation(updatedResume.getLocation());
            existingResume.setProfessionTitle(updatedResume.getProfessionTitle());
            existingResume.setWeb(updatedResume.getWeb());
            existingResume.setPreHour(updatedResume.getPreHour());
            existingResume.setResumePdf(updatedResume.getResumePdf());
            existingResume.setUserId(updatedResume.getUserId());

            // Update other fields as needed.

            return freelancerResumeRepository.save(existingResume);
        } else {
            return null;
        }
    }


    public void deleteFreelancerResume(Long id) {
        FreelancerResume existingResume = getFreelancerResumeById(id);
        freelancerResumeRepository.delete(existingResume);
    }
}
