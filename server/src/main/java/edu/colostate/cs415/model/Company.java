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
		if (!(other instanceof Company)) return false;
		Company otherC = (Company) other;
		if (this.name.equals(otherC.getName())) return true;
		return false;
	}

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	@Override
	public String toString() {
		return this.name + ":" + this.available.size() + ":" + this.projects.size();
	}

	public String getName() {
		return this.name;
	}

	public Set<Worker> getEmployedWorkers() {
		return new HashSet<>(employees);
	}

	public Set<Worker> getAvailableWorkers() {
		return new HashSet<>(available);
	}

	public Set<Worker> getUnavailableWorkers() {
		return null;
	}

	public Set<Worker> getAssignedWorkers() {
    return new HashSet<>(assigned);
    }

	public Set<Worker> getUnassignedWorkers() {
	return new HashSet<>(employees);
    }

	public Set<Project> getProjects() {
		return this.projects;
	}

	public Set<Qualification> getQualifications() {
		return this.qualifications;
	}

	public Worker createWorker(String name, Set<Qualification> qualifications, double salary) {
		if (name == null || name.trim().length() == 0) {
			return null;
		}
		if (qualifications == null || qualifications.isEmpty()) {
			return null;
		}
		if (salary < 0 || Double.isNaN(salary)) {
			return null;
		}
		if (!this.qualifications.containsAll(qualifications)) {
			return null;
		}

		Worker w = new Worker(name, new HashSet<>(qualifications), salary);
		this.employees.add(w);
		this.available.add(w);

		for (Qualification q : qualifications) {
			q.addWorker(w);
		}

		return w;
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
		// All integrity constraints for variables are caught using the Project constructor
		Project p = new Project(name, qualifications, size);
		this.projects.add(p);
		return p;
	}

	public void start(Project project) {
		if (project == null || !this.projects.contains(project)) {
			throw new IllegalArgumentException();
		}

		ProjectStatus status = project.getStatus();

		if ((status == ProjectStatus.PLANNED || status == ProjectStatus.SUSPENDED)
				&& project.getMissingQualifications().isEmpty()) {
			project.setStatus(ProjectStatus.ACTIVE);
		}
	}

	public void finish(Project project) {
	}

	public void assign(Worker worker, Project project) {
	}

	public void unassign(Worker worker, Project project) {
		if (worker == null || project == null) {
			throw new IllegalArgumentException();
		}
		if (!this.employees.contains(worker) || !this.projects.contains(project)) {
			throw new IllegalArgumentException();
		}

		if (!project.getWorkers().contains(worker) || !worker.getProjects().contains(project)) {
			return;
		}

		project.removeWorker(worker);
		worker.removeProject(project);

		if (worker.getProjects().isEmpty()) {
			this.assigned.remove(worker);
		}

		if (worker.isAvailable()) {
			this.available.add(worker);
		} else {
			this.available.remove(worker);
		}

		if (project.getStatus() == ProjectStatus.ACTIVE
				&& !project.getMissingQualifications().isEmpty()) {
			project.setStatus(ProjectStatus.SUSPENDED);
		}
	}

	public void unassignAll(Worker worker) {
		if (worker == null || !this.employees.contains(worker)) {
		throw new IllegalArgumentException();
		}

		Set<Project> workerProjects = new HashSet<>(worker.getProjects());

		for (Project project : workerProjects) {
			if (this.projects.contains(project)) {
				this.unassign(worker, project);
			}
		}

		this.assigned.remove(worker);

		if (worker.isAvailable()) {
			this.available.add(worker);
		} else {
			this.available.remove(worker);
		}
	}
}