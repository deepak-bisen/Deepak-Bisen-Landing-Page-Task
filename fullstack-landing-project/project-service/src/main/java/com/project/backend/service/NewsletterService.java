package com.project.backend.service;

import com.project.backend.entity.Newsletter;

import java.util.List;

public interface NewsletterService {

    // --- === Public (Landing Page) Methods === ---
    public Newsletter saveNewsletter(Newsletter newsletter);

    // --- === Admin Panel Methods === ---
    public List<Newsletter> getAllNewsletterSubscribers();
}