package com.project.backend.controller;

import com.project.backend.entity.Project;
import com.project.backend.service.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Project entities.
 * Exposes API endpoints for CRUD operations on projects.
 */
@RestController
@RequestMapping("/api/projects") // Base path for all project-related APIs
public class ProjectControllerImpl implements ProjectController {

    @Autowired
    private ProjectServiceImpl projectService;

    /**
     * POST /api/projects : Create a new project.
     * This endpoint fixes the "405 Method Not Allowed" error.
     */
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        // The service now returns the saved Project object
        Project savedProject = projectService.createProject(project);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    /**
     * GET /api/projects : Get all projects.
     */
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        // The service now returns the List of projects
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    /**
     * GET /api/projects/{id} : Get a single project by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable String id) {
        // The service now returns the Project
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    /**
     * PUT /api/projects/{id} : Update an existing project.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable String id, @RequestBody Project projectDetails) {
        // The service now returns the updated Project
        Project updatedProject = projectService.updateProject(id, projectDetails);
        return ResponseEntity.ok(updatedProject);
    }

    /**
     * DELETE /api/projects/{id} : Delete a project by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }
}