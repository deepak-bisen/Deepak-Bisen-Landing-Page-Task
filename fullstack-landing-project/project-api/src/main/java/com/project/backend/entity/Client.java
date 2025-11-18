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
    private String imageUrl;

    @Column(nullable = false)
    private String name;

    @Column(length = 2000, nullable = false)
    private String description;

    @Column(nullable = false)
    private String designation; // e.g., "CEO, Fancorp"
}
