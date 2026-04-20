package com.jobtracker.jobtracker.controller;

import com.jobtracker.jobtracker.model.Job;
import com.jobtracker.jobtracker.model.User;
import com.jobtracker.jobtracker.repository.JobRepository;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
public class JobViewController {

    private final JobRepository jobRepository;

    public JobViewController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping ("/")
    public String viewHomePage(@RequestParam (required = false) String keyword,
                               @RequestParam (required = false) String status,
                               @RequestParam(required = false) String sort,
                               Model model,
                               jakarta.servlet.http.HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        List<Job> jobs = jobRepository.searchJobs(user, keyword, status);

        if ("company".equals(sort)) {
            jobs.sort(Comparator.comparing(Job::getCompany));
        } else if ("status".equals(sort)) {
            jobs.sort(Comparator.comparing(Job::getStatus));
        } else if ("recent".equals(sort)) {
            jobs.sort(Comparator.comparing(Job::getId).reversed());
        }
        model.addAttribute("keyword", keyword);
        model.addAttribute("status", status);
        model.addAttribute("sort", sort);
        model.addAttribute("jobs", jobs);
        return "index";
    }
    
    @PostMapping ("/add-job")
    public String addJob(@Valid @ModelAttribute Job job,BindingResult result, jakarta.servlet.http.HttpSession session ) {

        if (result.hasErrors()) {
            return "index"; // reload form with errors
        }

        User user = (User) session.getAttribute("loggedInUser");

        job.setUser(user); //  LINK JOB TO USER
        jobRepository.save(job);
        return "redirect:/?success=added";
    }

    @GetMapping ("/delete/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobRepository.deleteById(id);
        return "redirect:/?success=deleted";
    }

    @GetMapping ("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        model.addAttribute("job", job);
        return "edit-job";
    }

    @PostMapping ("/update/{id}")
    public String updateJob(@PathVariable Long id, @ModelAttribute Job updatedJob) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        job.setCompany(updatedJob.getCompany());
        job.setRole(updatedJob.getRole());
        job.setStatus(updatedJob.getStatus());
        job.setNotes(updatedJob.getNotes());

        jobRepository.save(job);

        return "redirect:/?success=updated";
    }
}