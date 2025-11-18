package com.project.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class ContactForm {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private String city;
}

