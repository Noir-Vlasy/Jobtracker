package com.jobtracker.jobtracker.controller;

import com.jobtracker.jobtracker.model.User;
import com.jobtracker.jobtracker.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public AuthController(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    // Show register page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    // Handle register
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "redirect:/register?error=username";
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
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
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            jakarta.servlet.http.HttpSession session) {

        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null && encoder.matches(password, user.getPassword())) {
            session.setAttribute("loggedInUser", user); // 🔥 IMPORTANT
            return "redirect:/"; // login success
        }

        return "redirect:/login?error=true";
    }

    @GetMapping("/logout")
    public String logout(jakarta.servlet.http.HttpSession session) {
        session.invalidate(); // clears session for session ID/token received as parameter when method is called
        return "redirect:/login";
    }

}