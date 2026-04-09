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
		if (qualifications == null || qualifications.isEmpty()) {
			throw new IllegalArgumentException("Project qualifications cannot be null or empty");
		}
		if (size == null) {
			throw new IllegalArgumentException("Project size cannot be null");
		}
		this.name = name;
		this.size = size;
		this.status = ProjectStatus.PLANNED;
		this.workers = new HashSet<Worker>();
		this.qualifications = new HashSet<>(qualifications);
		
		
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
		return this.name + ":" + this.workers.size() + ":" + this.status;
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
		return new HashSet<>(workers);
	}

	public void removeAllWorkers() {
		this.workers.clear();
	}

	public Set<Qualification> getRequiredQualifications() {
		return new HashSet<>(qualifications);
	}

	public void addQualification(Qualification qualification) {
		if (qualification == null) {
		throw new IllegalArgumentException("Must be a valid qualification");
		}
		if (this.status == ProjectStatus.ACTIVE || this.status == ProjectStatus.FINISHED) {
        throw new IllegalArgumentException("Cannot add qualification to an active or finished project");
    	}
		this.qualifications.add(qualification);
	}

	public Set<Qualification> getMissingQualifications() {
		Set<Qualification> missingQ = new HashSet<>(this.qualifications);

		for (Worker worker : this.workers) {
			missingQ.removeAll(worker.getQualifications());
		}

		return missingQ;
	}

	public boolean isHelpful(Worker worker) {
		if (worker == null) {
			return false;
		}

		Set<Qualification> missing = this.getMissingQualifications();

		for (Qualification q : worker.getQualifications()) {
			if (missing.contains(q)) {
				return true;
			}
		}

		return false;
	}

	public ProjectDTO toDTO() {
		String[] workerStrings = workers.stream()
										.map(Worker::getName)
										.toArray(String[]::new);

		String[] qualificationsDTO = this.qualifications.stream()
										.map(Qualification::toString)
										.toArray(String[]::new);

		String[] missingQualificationsString = getMissingQualifications().stream()
										.map(Qualification::toString)
										.toArray(String[]::new);

		ProjectDTO dto = new ProjectDTO(name, size, status, workerStrings, qualificationsDTO, missingQualificationsString);

		return dto;
	}
}
