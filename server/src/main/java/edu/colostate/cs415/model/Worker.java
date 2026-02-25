package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import edu.colostate.cs415.dto.WorkerDTO;

public class Worker {

	public static final int MAX_WORKLOAD = 12;

	private String name;
	private double salary;
	private Set<Project> projects;
	private Set<Qualification> qualifications;

	public Worker(String name, Set<Qualification> qualifications, double salary) {
		if (name == null) {
			throw new IllegalArgumentException("A Worker name cannot be null");
		}
		if (name.trim().length() == 0) {
			throw new IllegalArgumentException("A Worker name cannot be empty or only whitespaces");
		}
		if (qualifications == null) {
			throw new IllegalArgumentException("A Worker Qualification set cannot be null");
		}
		if (salary < 0 || Double.isNaN(salary)) {
			throw new IllegalArgumentException("A Worker salary cannot be negative or NaN value");
		}

		this.name = name;
		this.qualifications = qualifications;
		this.projects = new HashSet<Project>();
		this.salary = salary;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Worker)) return false;
		Worker otherw = (Worker) other;
		if(this.name.equals(otherw.getName())) return true;
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.name);
	}

	@Override
	public String toString() {
		long sal = (long)this.salary;
		return (this.name + ":" + Integer.toString(this.projects.size()) + ":" + Integer.toString(this.qualifications.size()) + ":" + sal);
	}

	public String getName() {
		return this.name;
	}

	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double salary) {
		if (salary < 0 || Double.isNaN(salary)) {
			throw new IllegalArgumentException("A Worker salary cannot be negative or NaN value");
		}
		this.salary = salary;
	}

	public Set<Qualification> getQualifications() {
		return this.qualifications;
	}

	public void addQualification(Qualification qualification) {
		qualifications.add(qualification);
	}

	public Set<Project> getProjects() {
		return this.projects;
	}

	public void addProject(Project project) {
		projects.add(project);
	}

	public void removeProject(Project project) {
		projects.remove(project);
	}

	public int getWorkload() {
		int workload = 0;
		for (Project project : this.projects) {
			if (project.getStatus() == ProjectStatus.FINISHED) {
				continue;
			}
			workload += project.getSize().getValue();
		}
		return workload;
	}

	public boolean willOverload(Project project) {
		return false;
	}

	public boolean isAvailable() {
		if (getWorkload() < 12) {
			return true;
		}
		return false;
	}

	public WorkerDTO toDTO() {
		return null;
	}
}
