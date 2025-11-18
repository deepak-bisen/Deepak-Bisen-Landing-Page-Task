package com.project.backend.repository;

import com.project.backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository<EntityType, IdType>
// By extending this, we get methods like save(), findAll(), findById(), delete()
@Repository
public interface ProjectRepository extends JpaRepository<Project,String> {
}
