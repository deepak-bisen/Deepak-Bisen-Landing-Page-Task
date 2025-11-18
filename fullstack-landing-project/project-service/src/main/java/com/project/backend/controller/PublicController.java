package com.project.backend.controller;

import com.project.backend.entity.Client;
import com.project.backend.entity.ContactForm;
import com.project.backend.entity.Newsletter;
import com.project.backend.entity.Project;
import com.project.backend.service.ClientService;
import com.project.backend.service.ContactFormService;
import com.project.backend.service.NewsletterService;
import com.project.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for all PUBLIC-facing API endpoints.
 * This services the landing.js file.
 */
@RestController
@RequestMapping("/api/public") // Base path for all public APIs
public class PublicController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ContactFormService contactFormService;

    @Autowired
    private NewsletterService newsletterService;

    /**
     * GET /api/public/projects : Get all projects for the landing page.
     */
    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getPublicProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    /**
     * GET /api/public/clients : Get all clients for the landing page.
     */
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getPublicClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    /**
     * POST /api/public/contact : Submit the contact form.
     */
    @PostMapping("/contact")
    public ResponseEntity<String> submitContactForm(@RequestBody ContactForm contactForm) {
        try {
            contactFormService.saveContactForm(contactForm);
            return ResponseEntity.ok("Thank you! We have received your quote request.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error submitting form: " + e.getMessage());
        }
    }

    /**
     * POST /api/public/subscribe : Submit the newsletter form.
     */
    @PostMapping("/subscribe")
    public ResponseEntity<String> submitNewsletter(@RequestBody Newsletter newsletter) {
        try {
            newsletterService.saveNewsletter(newsletter);
            return ResponseEntity.ok("Thank you for subscribing!");
        } catch (Exception e) {
            // This will catch duplicate emails if the service layer throws an exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: Email may already be subscribed.");
        }
    }
}