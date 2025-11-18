package com.project.backend.service;

import com.project.backend.entity.ContactForm;

import java.util.List;

public interface ContactFormService {

    // --- === Public (Landing Page) Methods === ---
    public ContactForm saveContactForm(ContactForm contactForm);

    // --- === Admin Panel Methods === ---
    public List<ContactForm> getAllContactSubmissions();
}
