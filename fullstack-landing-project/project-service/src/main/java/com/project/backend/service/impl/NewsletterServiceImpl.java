package com.project.backend.service.impl;

import com.project.backend.entity.Newsletter;
import com.project.backend.repository.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsletterServiceImpl {

    @Autowired
    private NewsletterRepository newsletterRepository;

    // --- === Public (Landing Page) Methods === ---
    public Newsletter saveNewsletter(Newsletter newsletter) {
        // The 'unique=true' on the email column in the entity will handle duplicates
        try {
            return newsletterRepository.save(newsletter);
        } catch (Exception e) {
            // This will catch the error if the email already exists
            System.err.println("Error saving newsletter: " + e.getMessage());
            return null;
        }
    }

    // --- === Admin Panel Methods === ---
    public List<Newsletter> getAllNewsletterSubscribers() {
        return newsletterRepository.findAll();
    }

}
