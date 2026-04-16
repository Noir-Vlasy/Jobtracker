package com.jobtracker.jobtracker.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String password;

    private String provider; // LOCAL or GOOGLE

    @Column(nullable = false)
    private String role = "USER"; // user or Admin (Updated Manually)

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // Used in Admin Console, deletes Jobs associated to a user if user is removed
    private List<Job> jobs;
}