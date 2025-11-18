package com.project.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * This configuration allows Cross-Origin Resource Sharing (CORS) from any origin (*).
     * This is necessary for  frontend (e.g., running on http://localhost:5500)
     * to communicate with  backend (running on http://localhost:8080).
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Allow CORS for all API endpoints (admin)
                .allowedOrigins("*")     // Allow requests from any origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
                .allowedHeaders("*");    // Allow all headers

        registry.addMapping("/api/public/**") // Allow CORS for all API endpoints (public)
                .allowedOrigins("*")     // Allow requests from any origin
                .allowedMethods("GET", "POST") // Public only needs GET and POST
                .allowedHeaders("*");    // Allow all headers
    }
}