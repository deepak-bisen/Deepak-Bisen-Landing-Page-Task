package com.project.backend.service.impl;

import com.project.backend.exception.ResourceNotFoundException;
import com.project.backend.entity.Project;
import com.project.backend.repository.ProjectRepository;
import com.project.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Implementation of the ProjectService interface.
 * Handles the business logic for Project entities.
 *
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project createProject(Project project) {
        // In a real app, you might add validation or other logic here
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(String id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return project;
    }

    @Override
    public Project updateProject(String id, Project projectDetails) {
        Project project = projectRepository.findById(id) // Find the project first
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        // Update the found project with new details
        project.setName(projectDetails.getName());
        project.setDescription(projectDetails.getDescription());
        project.setImageUrl(projectDetails.getImageUrl());
        project.setCategory(projectDetails.getCategory()); // This line was missing; I've added it back.

        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(String id) {
        Project project = projectRepository.findById(id) // Check if project exists before deleting
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        projectRepository.delete(project);
        // No return statement needed for void method
    }

}