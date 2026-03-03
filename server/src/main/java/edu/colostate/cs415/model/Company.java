package edu.colostate.cs415.model;
import java.util.HashSet;
import java.util.Set;

public class Company {

	private String name;
	private Set<Worker> employees;
	private Set<Worker> available;
	private Set<Worker> assigned;
	private Set<Project> projects;
	private Set<Qualification> qualifications;

	public Company(String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		this.employees = new HashSet<>();
		this.available = new HashSet<>();
		this.assigned = new HashSet<>();
		this.projects = new HashSet<>();
		this.qualifications = new HashSet<>();
	}

	@Override
	public boolean equals(Object other) {
		if(!(other instanceof Company)) return false;
		Company otherC = (Company) other;
		if(this.name.equals(otherC.getName())) return true;
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public String toString() {
		return null;
	}

	public String getName() {
		return this.name;
	}

	public Set<Worker> getEmployedWorkers() {
		return null;
	}

	public Set<Worker> getAvailableWorkers() {
		return null;
	}

	public Set<Worker> getUnavailableWorkers() {
		return null;
	}

	public Set<Worker> getAssignedWorkers() {
		return null;
	}

	public Set<Worker> getUnassignedWorkers() {
		return null;
	}

	public Set<Project> getProjects() {
		return null;
	}

	public Set<Qualification> getQualifications() {
		return this.qualifications;
	}

	public Worker createWorker(String name, Set<Qualification> qualifications, double salary) {
		return null;
	}

	public Qualification createQualification(String description) {
		if (description == null || description.trim().length() == 0) {
		return null;
	}
		Qualification q = new Qualification(description);
		this.qualifications.add(q);
		return q;
	}

	public Project createProject(String name, Set<Qualification> qualifications, ProjectSize size) {
		return null;
	}

	public void start(Project project) {
	}

	public void finish(Project project) {
	}

	public void assign(Worker worker, Project project) {
	}

	public void unassign(Worker worker, Project project) {
	}

	public void unassignAll(Worker worker) {
	}
}
