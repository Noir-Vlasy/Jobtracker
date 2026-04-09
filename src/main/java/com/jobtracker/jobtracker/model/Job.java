package com.jobtracker.jobtracker.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;
    private String role;
    private String status;
    private String notes;
}