package com.example.resumeservice2.controller;

import org.springframework.web.multipart.MultipartFile;

import com.example.resumeservice2.entity.FreelancerResume;
import com.example.resumeservice2.service.FreelancerResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/freelancer-resumes")
@CrossOrigin
public class FreelancerResumeController {
    private static final String UPLOAD_DIR = "uploads/";
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

    @PostMapping("/upload-resume")
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            byte[] pdfBytes = file.getBytes();
            String filePath = UPLOAD_DIR + filename;
            Files.write(Paths.get(filePath), pdfBytes);
            return ResponseEntity.ok("PDF uploaded and saved.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading PDF.");
        }
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
        // Define the path to your 'uploads' folder
        Path path = Paths.get("src/target/uploads").resolve(fileName);
        Resource resource = new UrlResource(path.toUri());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


}
