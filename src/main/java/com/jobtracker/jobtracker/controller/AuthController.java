package com.jobtracker.jobtracker.controller;

import com.jobtracker.jobtracker.model.User;
import com.jobtracker.jobtracker.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Show register page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    // Handle register
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        user.setProvider("LOCAL");
        userRepository.save(user);
        return "redirect:/login";
    }

    // Show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Handle login
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            jakarta.servlet.http.HttpSession session) {

        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", user); // 🔥 IMPORTANT
            return "redirect:/"; // login success
        }

        return "redirect:/login?error=true";
    }
}