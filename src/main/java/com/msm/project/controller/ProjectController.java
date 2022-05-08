package com.msm.project.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.msm.project.model.Project;
import com.msm.project.model.ProjectForm;
import com.msm.project.model.ProjectRepository;
import com.msm.project.model.SearchConditions;
import com.msm.project.model.SearchModel;



@RestController
public class ProjectController<T> {
	
	@Autowired
	ProjectRepository repository;
	@Autowired
	SearchConditions searchConditions;
	
	// Project全取得
	@RequestMapping(value = "/api/projects", method = RequestMethod.GET)
	public List<Project> getProjects() {
		List<Project> projectList = repository.findAll();

		return projectList;
	}
	
	// Project１つ取得
	@RequestMapping(value = "/api/projects/{projectId}", method = RequestMethod.GET)
	public Project getProject(@PathVariable int projectId) {
		Project proj = repository.getById(projectId);
		
		return proj;
	}
	
	// Project作成
	@RequestMapping(value = "/api/projects", method = RequestMethod.POST)
	public void createProject(@RequestBody ProjectForm form) {
		Project project = new Project();
		
		project.setCreated_by(form.getCreated_by());
		project.setUpdated_by(form.getUpdated_by());
		project.setTitle(form.getTitle());
		project.setContent(form.getContent());
		project.setIs_deleted(false);
		project.setCreated_at(new Timestamp(System.currentTimeMillis()));
		project.setUpdated_at(new Timestamp(System.currentTimeMillis()));
		
		repository.save(project);
	}
	
	// Project更新
	@RequestMapping(value = "/api/projects/{projectId}", method = RequestMethod.POST)
	public void createProject(@RequestBody ProjectForm form, @PathVariable int projectId) {
		Project project = repository.getById(projectId);
		
		project.setUpdated_by(form.getUpdated_by());
		project.setTitle(form.getTitle());
		project.setContent(form.getContent());
		project.setUpdated_at(new Timestamp(System.currentTimeMillis()));
		
		repository.save(project);
	}
	
	// Project論理削除
	@RequestMapping(value = "/api/projects/{projectId}/delete")
	public void deleteProject(@PathVariable int projectId) {
		Project project = repository.getById(projectId);
		
		project.setIs_deleted(true);
		
		repository.save(project);
	}
	//検索機能
	@RequestMapping(path = "api/projects/search", method = RequestMethod.GET)
	public  List<T> searchResult(SearchModel target) {
		return (List<T>)searchConditions.getSearchReservations(target); 
		
	}
	

}
