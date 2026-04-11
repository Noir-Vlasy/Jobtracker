package com.jobtracker.jobtracker.controller;

import com.jobtracker.jobtracker.model.Job;
import com.jobtracker.jobtracker.repository.JobRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // 👉 Add Job
    @PostMapping
    public Job addJob(@RequestBody Job job) {
        return jobRepository.save(job);
    }

    // 👉 Get All Jobs
    @GetMapping
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job updatedjob) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        job.setCompany(updatedjob.getCompany());
        job.setRole(updatedjob.getRole());
        job.setNotes(updatedjob.getNotes());
        job.setStatus(updatedjob.getStatus());

        return jobRepository.save(job);
    }

    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id){
        jobRepository.deleteById(id);
        return "Job Deleted successfully";
    }

}