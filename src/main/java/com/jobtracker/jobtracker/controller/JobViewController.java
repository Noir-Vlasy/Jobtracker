package com.jobtracker.jobtracker.controller;

import com.jobtracker.jobtracker.model.Job;
import com.jobtracker.jobtracker.model.User;
import com.jobtracker.jobtracker.repository.JobRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class JobViewController {

    private final JobRepository jobRepository;

    public JobViewController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping("/")
    public String viewHomePage(Model model, jakarta.servlet.http.HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login"; // 🔒 not logged in
        }

        model.addAttribute("jobs", jobRepository.findAll()); // temp
        return "index";

    }

    @PostMapping("/add-job")
    public String addJob(@ModelAttribute Job job) {
        jobRepository.save(job);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobRepository.deleteById(id);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        model.addAttribute("job", job);
        return "edit-job";
    }
    @PostMapping("/update/{id}")
    public String updateJob(@PathVariable Long id, @ModelAttribute Job updatedJob) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        job.setCompany(updatedJob.getCompany());
        job.setRole(updatedJob.getRole());
        job.setStatus(updatedJob.getStatus());
        job.setNotes(updatedJob.getNotes());

        jobRepository.save(job);

        return "redirect:/";
    }
}