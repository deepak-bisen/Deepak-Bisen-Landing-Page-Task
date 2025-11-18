package com.project.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CLIENTS")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String clientId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String avatarUrl; // Standardized from imageUrl

    @Column(nullable = false)
    private String name;

    @Column(length = 2000, nullable = false)
    private String testimonial; // Standardized from description

    @Column(nullable = false)
    private String company; // Standardized from designation
}