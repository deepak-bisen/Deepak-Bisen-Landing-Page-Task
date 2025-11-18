package com.project.backend.controller;

import com.project.backend.entity.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProjectController {
    /**
     * POST /api/projects : Create a new project.
     * This endpoint fixes the "405 Method Not Allowed" error.
     */
    public ResponseEntity<Project> createProject( Project project);

    /**
     * GET /api/projects : Get all projects.
     */
    public ResponseEntity<List<Project>> getAllProjects();
    /**
     * GET /api/projects/{id} : Get a single project by ID.
     */
    public ResponseEntity<Project> getProjectById(String id);

    /**
     * PUT /api/projects/{id} : Update an existing project.
     */
    public ResponseEntity<Project> updateProject(String id, Project projectDetails);

    /**
     * DELETE /api/projects/{id} : Delete a project by ID.
     */
    public ResponseEntity<?> deleteProject(String id);
}
