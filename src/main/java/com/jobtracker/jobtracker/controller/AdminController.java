package com.jobtracker.jobtracker.controller;

import com.jobtracker.jobtracker.model.User;
import com.jobtracker.jobtracker.repository.JobRepository;
import com.jobtracker.jobtracker.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public AdminController(UserRepository userRepository, JobRepository jobRepository) {
     this.userRepository = userRepository;
     this.jobRepository = jobRepository;
    }

    @GetMapping("/admin")
    public String adminPage(Model model, jakarta.servlet.http.HttpSession session) {

    User user =  (User) session.getAttribute("loggedInUser");

    if (user == null || !"ADMIN".equals(user.getRole())) {
    return "redirect:/";
    }

    model.addAttribute("jobs", jobRepository.findAll());
    model.addAttribute("users",userRepository.findAll());
    return "admin";
    }
}
