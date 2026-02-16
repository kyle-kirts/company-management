package edu.colostate.cs415.model;

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
		if (salary < 0) {
			throw new IllegalArgumentException("A Worker salary cannot be negative");
		}

		this.name = name;
		this.qualifications = qualifications;
		this.salary = salary;
	}

	@Override
	public boolean equals(Object other) {
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.name);
	}

	@Override
	public String toString() {
		return null;
	}

	public String getName() {
		return null;
	}

	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double salary) {
	}

	public Set<Qualification> getQualifications() {
		return this.qualifications;
	}

	public void addQualification(Qualification qualification) {
	}

	public Set<Project> getProjects() {
		return null;
	}

	public void addProject(Project project) {
	}

	public void removeProject(Project project) {
	}

	public int getWorkload() {
		return 0;
	}

	public boolean willOverload(Project project) {
		return false;
	}

	public boolean isAvailable() {
		return false;
	}

	public WorkerDTO toDTO() {
		return null;
	}
}
