package com.yash.projectManagement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.yash.projectManagement.entity.Project;
import com.yash.projectManagement.serviceimpl.ProjectServiceImpl;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	@Autowired
	ProjectServiceImpl projectService;

	public static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@PostMapping(value = "/")
	public ResponseEntity<?> addProject(@RequestBody Project project, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", project);

		if (projectService.isUserExist(project)) {
			logger.error("Unable to create. A User with name {} already exist", project.getName());
			return new ResponseEntity("Unable to create. A User with name " + project.getName() + " already exist.",
					HttpStatus.CONFLICT);
		}
		projectService.add(project);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/project/{id}").buildAndExpand(project.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getProject(@PathVariable int id) {

		logger.info("Fetching User with id {}", id);
		Project project = projectService.get(id);
		if (project == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity("User with id " + id + " not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Project>(project, HttpStatus.OK);

	}

	@GetMapping(value = "/")
	public ResponseEntity<List<Project>> getAllProject() {
		List<Project> projects = projectService.getAll();
		if (projects.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);

		}
		return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateProject(@RequestBody Project project, @PathVariable int id) {
		logger.info("Updating User with id {}", id);

		Project currentProject = projectService.get(id);

		if (currentProject == null) {
			logger.error("Unable to update. User with id {} not found.", id);

			return new ResponseEntity("Unable to upate. User with id " + id + " not found.", HttpStatus.NOT_FOUND);
		}
		currentProject.setId(id);
		currentProject.setName(project.getName());
		currentProject.setDescription(project.getDescription());
		currentProject.setStartOn(project.getStartOn());
		currentProject.setEstimatedEnddate(project.getEstimatedEnddate());
		projectService.update(currentProject, id);
		return new ResponseEntity<Project>(currentProject, HttpStatus.OK);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable int id) {
		logger.info("Fetching & Deleting User with id {}", id);

		Project project = projectService.get(id);

		if (project == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity("Unable to delete. User with id " + id + " not found.", HttpStatus.NOT_FOUND);
		}
		projectService.delete(id);
		return new ResponseEntity<Project>(HttpStatus.NO_CONTENT);

	}
}
