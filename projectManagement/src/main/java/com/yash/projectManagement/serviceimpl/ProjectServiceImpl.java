package com.yash.projectManagement.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.projectManagement.entity.Project;
import com.yash.projectManagement.repository.ProjectRepository;
import com.yash.projectManagement.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

@Autowired
ProjectRepository projectRepo;
	
	@Override
	public void add(Project project) {
		try {
			 projectRepo.save(project);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

	@Override
	public Project get(int id) {
		try {
			return projectRepo.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Project> getAll() {

		return  projectRepo.findAll();
	}

	@Override
	public void update(Project currentProject,int id) {
		
		projectRepo.save(currentProject);
		
	}

	@Override
	public void delete(int id) {
		projectRepo.deleteById(id);
	}

	public boolean isUserExist(Project project) {
		Project proj=projectRepo.findByName(project.getName());
	
		for(Project projec : projectRepo.findAll()){
			if(proj==null){
				return false;
			}else 
		
			if(projec.getName().equalsIgnoreCase(proj.getName())){
				return true;
			}
		}
		return false;
		
	}
}
