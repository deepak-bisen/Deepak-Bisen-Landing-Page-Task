package com.project.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "newsletter_subscriptions")
@Data
public class Newsletter {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // 'unique = true' ensures we don't get duplicate email signups.
    @Column(nullable = false, unique = true)
    private String email;
}