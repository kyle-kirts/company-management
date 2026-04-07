package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import edu.colostate.cs415.dto.QualificationDTO;

public class Qualification {

	private String description;
	private Set<Worker> workers;

	public Qualification(String description) {
		if (validateDescription(description)) {
			throw new IllegalArgumentException("Null is not a valid description");
		}

		this.description = description;
		this.workers = new HashSet<Worker>();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Qualification)) {
			return false;
		}

		Qualification otherQ = (Qualification) other;
	return this.description.trim().equals(otherQ.description.trim());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.description);
	}

	@Override
	public String toString() {
		return this.description;
	}

	public Set<Worker> getWorkers() {
		return this.workers;
	}

	public void addWorker(Worker worker) {
		if(worker == null) throw new IllegalArgumentException("Worker can't be null");
		workers.add(worker);
	}

	public void removeWorker(Worker worker) {
		if(worker == null) throw new IllegalArgumentException("Worker can't be null");
		if (!(workers.isEmpty())) {
			workers.remove(worker);
		}
	}

	public QualificationDTO toDTO() {
		String[] workerStrings = workers.stream()
										.map(Worker::getName)
										.toArray(String[]::new);
		
		QualificationDTO dto = new QualificationDTO(description, workerStrings);
		return dto;

	}

	private boolean validateDescription(String description) {
		if (description == null) {
			return true;
		}
		if (description.trim().length() == 0) {
			return true;
		}

		return false;
	}
}
