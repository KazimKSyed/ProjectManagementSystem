package com.yash.projectManagement.service;

import java.util.List;

import com.yash.projectManagement.entity.Project;

public interface ProjectService {
	void add(Project project );
	Project get(int id);
	List<Project> getAll();
	void update(Project project,int id);
	void delete(int id);
}
