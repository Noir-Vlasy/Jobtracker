package com.jobtracker.jobtracker.controller;
import java.util.Map;
import java.util.stream.Collectors;

import com.jobtracker.jobtracker.model.Job;
import com.jobtracker.jobtracker.model.User;
import com.jobtracker.jobtracker.repository.JobRepository;
import com.jobtracker.jobtracker.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminController {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public AdminController(UserRepository userRepository, JobRepository jobRepository) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    @GetMapping ("/admin")
    public String adminPage(Model model, jakarta.servlet.http.HttpSession session) {

        model.addAttribute("totalUsers", userRepository.count());
        model.addAttribute("totalJobs", jobRepository.count());

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null || !"ADMIN".equals(user.getRole())) {
            return "redirect:/";
        }

        model.addAttribute("jobs", jobRepository.findAll());
        model.addAttribute("users", userRepository.findAll());

        Map<String, Long> statusCount = jobRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Job::getStatus, Collectors.counting()));

        model.addAttribute("statusCount", statusCount);

        return "admin";



    }
    @GetMapping("/admin/delete-user/{id}")
    public String deleteUser(@PathVariable Long id, HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null || !"ADMIN".equals(user.getRole())) {
            return "redirect:/";
        }

        userRepository.deleteById(id);
        return "redirect:/admin";
    }
    @GetMapping ("/admin/delete-job/{id}")
    public String deleteJobByAdmin(@PathVariable Long id) {
        jobRepository.deleteById(id);
        return "redirect:/admin";
    }


}
