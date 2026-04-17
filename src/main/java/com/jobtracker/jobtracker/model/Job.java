package com.jobtracker.jobtracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank (message = "Company is required")
    private String company;
    @NotBlank(message = "Role is required")
    private String role;
    private String status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String notes;
}