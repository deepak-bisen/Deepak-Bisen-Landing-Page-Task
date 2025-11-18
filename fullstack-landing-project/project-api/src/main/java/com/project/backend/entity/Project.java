package com.project.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PROJECTS")
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String projectId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String name;

    @Column(length = 2000, nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

}
