package com.project.backend.service.impl;

import com.project.backend.repository.ContactFormRepository;
import com.project.backend.entity.ContactForm;
import com.project.backend.service.ContactFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactFormServiceImpl implements ContactFormService { // Added 'implements ContactFormService'

    @Autowired
    private ContactFormRepository contactFormRepository;

    // --- === Public (Landing Page) Methods === ---
    @Override // Added @Override
    public ContactForm saveContactForm(ContactForm contactForm) {
        // Here  validation will be added, e.g., check for valid email
        return contactFormRepository.save(contactForm);
    }

    // --- === Admin Panel Methods === ---
    @Override // Added @Override
    public List<ContactForm> getAllContactSubmissions() {
        return contactFormRepository.findAll();
    }
}