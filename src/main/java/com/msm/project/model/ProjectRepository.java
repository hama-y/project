package com.msm.project.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



public interface ProjectRepository 
	extends JpaRepository<Project, Integer>,
		JpaSpecificationExecutor<SearchEntity> {

}
