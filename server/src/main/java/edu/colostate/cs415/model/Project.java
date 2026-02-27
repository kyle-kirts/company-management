package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import edu.colostate.cs415.dto.ProjectDTO;

public class Project {

	private String name;
	private ProjectSize size;
	private ProjectStatus status;
	private Set<Worker> workers;
	private Set<Qualification> qualifications;

	public Project(String name, Set<Qualification> qualifications, ProjectSize size) {
		if (name == null) {
			throw new IllegalArgumentException("Project name cannot be null");
		}
		if (name.trim().length() == 0) {
			throw new IllegalArgumentException("Project name cannot be empty");
		}
		if (qualifications == null) {
			throw new IllegalArgumentException("Project qualifications cannot be null");
		}
		if (size == null) {
			throw new IllegalArgumentException("Project size cannot be null");
		}
		this.name = name;
		this.size = size;
		this.status = ProjectStatus.PLANNED;
		this.workers = new HashSet<Worker>();
		this.qualifications = qualifications;
	}

	@Override
	public boolean equals(Object other) {
		if(!(other instanceof Project)) return false;
		Project otherp = (Project) other;
		if(this.name.equals(otherp.getName())) return true;
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
		return this.name;
	}

	public ProjectSize getSize() {
		return this.size;
	}

	public ProjectStatus getStatus() {
		return this.status;
	}

	public void setStatus(ProjectStatus status) {
		if(status == null) throw new IllegalArgumentException("Must be a valid ProjectStatus");
		this.status = status;
	}

	public void addWorker(Worker worker) {
		if(worker == null) throw new IllegalArgumentException("Must be a valid worker");
		this.workers.add(worker);
	}

	public void removeWorker(Worker worker) {
		if(worker == null) throw new IllegalArgumentException("Must be a valid worker");
		this.workers.remove(worker);
	}

	public Set<Worker> getWorkers() {
		return this.workers;
	}

	public void removeAllWorkers() {
	}

	public Set<Qualification> getRequiredQualifications() {
		return this.qualifications;
	}

	public void addQualification(Qualification qualification) {
	}
		

	public Set<Qualification> getMissingQualifications() {
		return null;
	}

	public boolean isHelpful(Worker worker) {
		return false;
	}

	public ProjectDTO toDTO() {
		return null;
	}
}