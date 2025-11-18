package com.project.backend.service;


import com.project.backend.entity.Project;

import java.util.List;

/**
 * Service interface for managing Project entities.
 * Defines the business logic operations for projects.
 */
public interface ProjectService {

    /**
     * Creates and saves a new project.
     *
     * @param project The project entity to save.
     * @return The saved project entity (with ID).
     */
    Project createProject(Project project);

    /**
     * Retrieves all projects.
     *
     * @return A list of all project entities.
     */
    List<Project> getAllProjects();

    /**
     * Retrieves a project by its ID.
     *
     * @param id The ID of the project to retrieve.
     * @return The found project entity.
     * @throws com.flipr.fullstack.exception.ResourceNotFoundException if project not found.
     */
    Project getProjectById(String id);

    /**
     * Updates an existing project.
     *
     * @param id             The ID of the project to update.
     * @param projectDetails The project object with updated details.
     * @return The updated project entity.
     * @throws com.project.backend.exception.ResourceNotFoundException if project not found.
     */
    Project updateProject(String id, Project projectDetails);

    /**
     * Deletes a project by its ID.
     *
     * @param id The ID of the project to delete.
     * @throws com.project.backend.exception.ResourceNotFoundException if project not found.
     */
    void deleteProject(String id);

}