package com.yash.projectManagement.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private LocalDate startOn;
	private LocalDate estimatedEndDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartOn() {
		return startOn;
	}
	public void setStartOn(LocalDate startOn) {
		this.startOn = startOn;
	}

	public LocalDate getEstimatedEnddate() {
		return estimatedEndDate;
	}
	public void setEstimatedEnddate(LocalDate estimatedEnddate) {
		this.estimatedEndDate = estimatedEnddate;
	}
	
	
	public Project(int id, String name, String description, LocalDate startOn, LocalDate estimatedEndDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startOn = startOn;
		this.estimatedEndDate = estimatedEndDate;
	}
	
	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", description=" + description + ", startOn=" + startOn
				+ ", estimatedEnddate=" + estimatedEndDate + "]";
	}
	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
