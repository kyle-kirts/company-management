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
		Set<Worker> unavailable = new HashSet<Worker>();
		for(Worker w : this.employees){
			if(!w.isAvailable()) unavailable.add(w);
		}
		return unavailable;
	}

	public Set<Worker> getAssignedWorkers() {
    return new HashSet<>(assigned);
    }

	public Set<Worker> getUnassignedWorkers() {
	Set<Worker> result = new HashSet<>(employees);
	result.removeAll(assigned);
	return result;
}

	public Set<Project> getProjects() {
		return new HashSet<>(projects);
	}

	public Set<Qualification> getQualifications() {
		return new HashSet<>(qualifications);
	}

	public Worker createWorker(String name, Set<Qualification> qualifications, double salary) {
		if (name == null || name.trim().length() == 0) {
			return null;
		}
		if (qualifications == null || qualifications.isEmpty()) {
			return null;
		}
		if (salary < 0 || Double.isNaN(salary) || Double.isInfinite(salary)) {
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
		if (name == null || qualifications == null || size == null) {
			return null;
		}
		if (qualifications.isEmpty()) {
			return null;
		}
		if (!this.qualifications.containsAll(qualifications)) {
			return null;
		}
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
		if (project == null || !this.projects.contains(project)) {
			throw new IllegalArgumentException("Cannot finish a null project or one that does not belong to company");
		}
		if (project.getStatus() == ProjectStatus.ACTIVE) {
			Set<Worker> assigned_workers = new HashSet<>(project.getWorkers());
			for (Worker worker : assigned_workers) {
				unassign(worker, project);
			}
			project.setStatus(ProjectStatus.FINISHED);
		}
	}

	public void assign(Worker worker, Project project) {
		if (worker == null || project == null) {
        	throw new IllegalArgumentException("Cannot assign a null worker or project");
    	}

		if (!this.getEmployedWorkers().contains(worker) || !this.getProjects().contains(project)) {
			throw new IllegalArgumentException("Worker and Project must belong to the company");
		}
		if (unableToAssign(worker, project)) {
			return;
		}

		if (!this.getAssignedWorkers().contains(worker)) {
			this.assigned.add(worker);
		}
		project.addWorker(worker);
		worker.addProject(project);
		if (!worker.isAvailable()) {
			this.available.remove(worker);
		}
	}

	private boolean unableToAssign(Worker worker, Project project) {
		if (!this.available.contains(worker)) {
			return true;
		}
		if (project.getWorkers().contains(worker)) {
			return true;
		}
		if (project.getStatus() == ProjectStatus.ACTIVE || project.getStatus() == ProjectStatus.FINISHED) {
			return true;
		}
		if (worker.willOverload(project)) {
			return true;
		}
		if (!project.isHelpful(worker)) {
			return true;
		}
		return false;
	}

	public void unassign(Worker worker, Project project) {
		if (worker == null || project == null) {
			throw new IllegalArgumentException("Worker or Project cannot be null");
		}
		if (!this.employees.contains(worker) || !this.projects.contains(project)) {
			throw new IllegalArgumentException("Worker and Project must belong to Company");
		}

		if (!project.getWorkers().contains(worker) || !worker.getProjects().contains(project)) {
			throw new IllegalArgumentException("Worker must belong to Project and Worker must be assigned Project");
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