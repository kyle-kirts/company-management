package edu.colostate.cs415.model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import edu.colostate.cs415.dto.ProjectDTO;

public class ProjectTest {
	@Test
	public void test_nonNullName_someQualifications_mediumProject() {
		Set<Qualification> qs = new HashSet<>();
		Qualification q1 = new Qualification("Sean Kelley");
		Qualification q2 = new Qualification("Grace Kelsey");
		qs.add(q1);
		qs.add(q2);
		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);

		assertNotNull(p);
		assertEquals("Project Runway", p.getName());
		assertEquals(ProjectSize.MEDIUM, p.getSize());
		assertEquals(ProjectStatus.PLANNED, p.getStatus());
		assertEquals(0, p.getWorkers().size());
		assertEquals(2, p.getRequiredQualifications().size());
		assertTrue(p.getMissingQualifications().contains(q1));
		assertTrue(p.getMissingQualifications().contains(q2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_NullName_someQualifications_mediumProject(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Sean Kelley"));
		qs.add(new Qualification("Grace Kelsey"));
		new Project(null, qs, ProjectSize.MEDIUM);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_emptyName_someQualifications_mediumProject(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Sean Kelley"));
		qs.add(new Qualification("Grace Kelsey"));
		new Project("", qs, ProjectSize.MEDIUM);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nonNullName_nullQualifications_mediumProject(){
		new Project("Project Runway", null, ProjectSize.MEDIUM);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nonNullName_noQualifications_mediumProject(){
		Set<Qualification> qs = new HashSet<>();
		new Project("Project Runway", qs, ProjectSize.MEDIUM);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nonNullName_someQualifications_nullProject(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Sean Kelley"));
		qs.add(new Qualification("Grace Kelsey"));
		new Project("Project Runway", qs, null);
	}

	@Test
	public void test_nonNullName_someQualifications_smallProject(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Sean Kelley"));
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project("Project Runway", qs, ProjectSize.SMALL);

		assertNotNull(p);
		assertEquals(1, p.getSize().getValue());
	}

	@Test
	public void test_nonNullName_someQualifications_largeProject(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Sean Kelley"));
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project("Project Runway", qs, ProjectSize.BIG);

		assertNotNull(p);
		assertEquals(3, p.getSize().getValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_empty_name_constructor() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Qualificaiton1"));
		new Project("   ", qs, ProjectSize.SMALL);
	}

	@Test
	public void test_wrong_qualifications_constructor() {
		Qualification q1 = new Qualification("Sean Kelley");
		Qualification q2 = new Qualification("Grace Kelsey");
		Set<Qualification> qs = new HashSet<>();
		qs.add(q1);
		qs.add(q2);
		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);

		qs.clear();

		assertEquals(2, p.getRequiredQualifications().size());
		assertTrue(p.getRequiredQualifications().contains(q1));
		assertTrue(p.getRequiredQualifications().contains(q2));
	}

	@Test
	public void test_validName_getName(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Sean Kelley"));
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);
		assertEquals(p.getName(), "Project Runway");
	}

	@Test
     public void test_validNameHash_hashCode() {
     Set<Qualification> qs1 = new HashSet<>();
     qs1.add(new Qualification("Qualification"));

     Set<Qualification> qs2 = new HashSet<>();
     qs2.add(new Qualification("Qualification"));

     Project p1 = new Project("valid description", qs1, ProjectSize.MEDIUM);
     Project p2 = new Project("valid description", qs2, ProjectSize.MEDIUM);

     assertEquals(p1, p2);
     assertEquals(p1.hashCode(), p2.hashCode());
    }

	@Test
	public void test_validEnumSize_getSize(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));
		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);

		assertEquals(2, p.getSize().getValue());
		assertEquals(ProjectSize.MEDIUM, p.getSize());
	}

	@Test
	public void test_projectO_equalNames_equals(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));
		Project p = new Project("Projected", qs, ProjectSize.MEDIUM);
		Project proj = new Project("Projected", qs, ProjectSize.SMALL);

		assertTrue(p.equals(proj));
	}

	@Test
	public void test_nullO_equalNames_equals(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));
		Project p = new Project("Projected", qs, ProjectSize.MEDIUM);

		assertFalse(p.equals(null));
	}

	@Test
	 public void test_nonprojectO_equalNames_equals(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));
		Project p = new Project("Projected", qs, ProjectSize.MEDIUM);

		assertFalse(p.equals("NotAProject"));
	}

	@Test
	public void test_projecto_nonEqualNames_equals(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));
		Project p = new Project("Projected", qs, ProjectSize.MEDIUM);
		Project proj = new Project("UnProjected", qs, ProjectSize.SMALL);

		assertFalse(p.equals(proj));
	}

	@Test (expected = IllegalArgumentException.class)
	public void testNullStatus_setStatus(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));
		Project p = new Project("Projected", qs, ProjectSize.MEDIUM);

		p.setStatus(null);
	}

	@Test
	public void testNotNullStatus_setStatus(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));
		Project p = new Project("Projected", qs, ProjectSize.MEDIUM);
		p.setStatus(ProjectStatus.ACTIVE);

		assertEquals(p.getStatus(), ProjectStatus.ACTIVE);
	}

	@Test
	public void testValidEnumgetStatus(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));
		Project p = new Project("Projected", qs, ProjectSize.MEDIUM);

		assertEquals(p.getStatus(), ProjectStatus.PLANNED);
	}

	@Test
	public void test_active_getStatus() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));
		Project project = new Project("Project", qs, ProjectSize.SMALL);

		project.setStatus(ProjectStatus.ACTIVE);
		assertTrue(project.getStatus() == ProjectStatus.ACTIVE);
		assertNotNull(project);
	}

	@Test
	public void test_finished_getStatus() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));
		Project project = new Project("Project", qs, ProjectSize.SMALL);

		project.setStatus(ProjectStatus.FINISHED);
		assertTrue(project.getStatus() == ProjectStatus.FINISHED);
		assertNotNull(project);
	}

	@Test
	public void test_hasWorkers_getWorkers(){
		Set<Qualification> qs = new HashSet<>();
		Qualification q = new Qualification("Sean Kelley");
		qs.add(q);
		Worker w = new Worker("Bob b", qs, 100000.0);
		Worker s = new Worker("Jeanie J", qs, 23450.0);
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);
		p.addWorker(w);
		p.addWorker(s);

		assertEquals(2, p.getWorkers().size());
		assertTrue(p.getWorkers().contains(w));
	}

	@Test
	public void test_noWorkers_getWorkers(){
		Set<Qualification> qs = new HashSet<>();
		Qualification q = new Qualification("Sean Kelley");
		qs.add(q);
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);

		assertEquals(0, p.getWorkers().size());
	}

	@Test
	public void test_realWorker_hasWorkers_addWorker(){
		Set<Qualification> qs = new HashSet<>();
		Qualification q = new Qualification("Sean Kelley");
		qs.add(q);
		Worker w = new Worker("Bob b", qs, 100000.0);
		Worker s = new Worker("Jeanie J", qs, 23450.0);
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);
		
		p.addWorker(w);

		assertEquals(1, p.getWorkers().size());
		
		p.addWorker(s);

		assertEquals(2, p.getWorkers().size());
		assertTrue(p.getWorkers().contains(w));
	}

	@Test (expected = IllegalArgumentException.class)
	public void test_nullWorker_hasWorkers_addWorker(){
		Set<Qualification> qs = new HashSet<>();
		Qualification q = new Qualification("Sean Kelley");
		qs.add(q);
		Worker w = new Worker("Bob b", qs, 100000.0);
		Worker s = new Worker("Jeanie J", qs, 23450.0);
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);
		
		p.addWorker(w);

		assertEquals(1, p.getWorkers().size());
		p.addWorker(null);
	}

	@Test
	public void test_dupeWorker_hasWorkers_addWorker(){
		Set<Qualification> qs = new HashSet<>();
		Qualification q = new Qualification("Sean Kelley");
		qs.add(q);
		Worker w = new Worker("Bob b", qs, 100000.0);
		Worker s = new Worker("Jeanie J", qs, 23450.0);
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);
		
		p.addWorker(w);

		assertEquals(1, p.getWorkers().size());
		
		p.addWorker(w);

		assertEquals(1, p.getWorkers().size());
		assertTrue(p.getWorkers().contains(w));
	}

	@Test
	public void test_realWorker_noWorkers_addWorker(){
		Set<Qualification> qs = new HashSet<>();
		Qualification q = new Qualification("Sean Kelley");
		qs.add(q);
		Worker w = new Worker("Bob b", qs, 100000.0);
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);
		
		assertEquals(0, p.getWorkers().size());
		
		p.addWorker(w);

		assertEquals(1, p.getWorkers().size());
		assertTrue(p.getWorkers().contains(w));
	}

	@Test 
	public void test_notinList_hasWorkers_removeWorker(){
		Set<Qualification> qs = new HashSet<>();
		Qualification q = new Qualification("Sean Kelley");
		qs.add(q);
		Worker w = new Worker("Bob b", qs, 100000.0);
		Worker s = new Worker("Jeanie J", qs, 23450.0);
		Worker a = new Worker("New", qs, 0);
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);
		
		p.addWorker(w);
		p.addWorker(s);

		p.removeWorker(a);
		assertEquals(2, p.getWorkers().size());
	}

	@Test
	public void test_inList_hasWorkers_removeWorker(){
		Set<Qualification> qs = new HashSet<>();
		Qualification q = new Qualification("Sean Kelley");
		qs.add(q);
		Worker w = new Worker("Bob b", qs, 100000.0);
		Worker s = new Worker("Jeanie J", qs, 23450.0);
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);
		
		p.addWorker(w);
		p.addWorker(s);

		p.removeWorker(s);

		assertEquals(1, p.getWorkers().size());
		assertFalse(p.getWorkers().contains(s));
	}

	@Test (expected = IllegalArgumentException.class)
	public void test_nullWorker_hasWorkers_removeWorker(){
		Set<Qualification> qs = new HashSet<>();
		Qualification q = new Qualification("Sean Kelley");
		qs.add(q);
		Worker w = new Worker("Bob b", qs, 100000.0);
		Worker s = new Worker("Jeanie J", qs, 23450.0);
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);
		
		p.addWorker(w);
		p.addWorker(s);

		p.removeWorker(null);
	}

	@Test
	public void test_notinList_noWorkers_removeWorker(){
		Set<Qualification> qs = new HashSet<>();
		Qualification q = new Qualification("Sean Kelley");
		qs.add(q);
		Worker w = new Worker("Bob b", qs, 100000.0);
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);

		p.removeWorker(w);
		assertEquals(0, p.getWorkers().size());
	}

	@Test
	public void test_noWorkers_planned_toString() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));
		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);

		assertEquals("Project Runway:0:PLANNED", p.toString());
	}

	@Test
	public void test_hasWorkers_active_toString() {
		Set<Qualification> qs = new HashSet<>();
		Qualification q = new Qualification("Java");
		qs.add(q);

		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);
		Worker w = new Worker("Bob", qs, 1000.0);

		p.addWorker(w);
		p.setStatus(ProjectStatus.ACTIVE);

		assertEquals("Project Runway:1:ACTIVE", p.toString());
	}

	@Test
	public void test_twoWorker_active_toString() {
		Set<Qualification> qs = new HashSet<>();
		Qualification q = new Qualification("Sean Kelley");
		qs.add(q);

		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);
		Worker w = new Worker("Bob b", qs, 100000.0);
		Worker w2 = new Worker("Susan s", qs, 100000.0);

		p.addWorker(w);
		p.addWorker(w2);
		p.setStatus(ProjectStatus.ACTIVE);

		assertEquals("Project Runway:2:ACTIVE", p.toString());
	}

	@Test
	public void test_oneWorker_planned_toString() {
		Set<Qualification> qs = new HashSet<>();
		Qualification q = new Qualification("Java");
		qs.add(q);

		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);
		Worker w = new Worker("Bob", qs, 1000.0);

		p.addWorker(w);

		assertEquals("Project Runway:1:PLANNED", p.toString());
	}

	@Test
	public void test_noWorkers_active_toString() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));

		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);
		p.setStatus(ProjectStatus.ACTIVE);

		assertEquals("Project Runway:0:ACTIVE", p.toString());
	}

	@Test
	public void test_twoWorkers_planned_toString() {
		Set<Qualification> qs = new HashSet<>();
		Qualification q = new Qualification("Java");
		qs.add(q);

		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);
		Worker w1 = new Worker("Bob", qs, 1000.0);
		Worker w2 = new Worker("Susan", qs, 2000.0);

		p.addWorker(w1);
		p.addWorker(w2);

		assertEquals("Project Runway:2:PLANNED", p.toString());
	}

	@Test
	public void test_notHelpfulWorker_isHelpful() {
		Set<Qualification> projectQs = new HashSet<>();
		Qualification q1 = new Qualification("Java");
		Qualification q2 = new Qualification("SQL");
		Qualification q3 = new Qualification("AWS");
		projectQs.add(q1);
		projectQs.add(q2);

		Project p = new Project("Project Runway", projectQs, ProjectSize.MEDIUM);

		Set<Qualification> workerQs = new HashSet<>();
		workerQs.add(q3);
		Worker w = new Worker("Bob", workerQs, 1000.0);

		assertFalse(p.isHelpful(w));
	}

	@Test
	public void test_noMissingQualifications_isHelpful() {
		Set<Qualification> projectQs = new HashSet<>();
		Qualification q1 = new Qualification("Java");
		projectQs.add(q1);

		Project p = new Project("Project Runway", projectQs, ProjectSize.MEDIUM);

		Set<Qualification> assignedQs = new HashSet<>();
		assignedQs.add(q1);
		Worker assigned = new Worker("Alice", assignedQs, 1000.0);
		p.addWorker(assigned);

		Set<Qualification> candidateQs = new HashSet<>();
		candidateQs.add(q1);
		Worker candidate = new Worker("Bob", candidateQs, 1000.0);

		assertFalse(p.isHelpful(candidate));
	}

	@Test
	public void test_helpfulWorker_isHelpful() {
		Set<Qualification> projectQs = new HashSet<>();
		Qualification q1 = new Qualification("Java");
		Qualification q2 = new Qualification("SQL");
		projectQs.add(q1);
		projectQs.add(q2);

		Project p = new Project("Project Runway", projectQs, ProjectSize.MEDIUM);

		Set<Qualification> workerQs = new HashSet<>();
		workerQs.add(q1);
		Worker w = new Worker("Bob", workerQs, 1000.0);

		assertTrue(p.isHelpful(w));
	}

	@Test
	public void test_nullWorker_isHelpful() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));
		Project p = new Project("Project", qs, ProjectSize.BIG);
		assertFalse(p.isHelpful(null));
	}

	@Test
	public void test_someQualifications_getRequiredQualifications() {
		Set<Qualification> qs = new HashSet<>();
		Qualification q1 = new Qualification("Sean Kelley");
		Qualification q2 = new Qualification("Grace Kelsey");
		qs.add(q1);
		qs.add(q2);

		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);

		assertEquals(2, p.getRequiredQualifications().size());
		assertTrue(p.getRequiredQualifications().contains(q1));
		assertTrue(p.getRequiredQualifications().contains(q2));
	}

	@Test
	public void test_without_copy_getRequiredQualifications() {
		Set<Qualification> qs = new HashSet<>();
		Qualification q1 = new Qualification("Sean Kelley");
		Qualification q2 = new Qualification("Grace Kelsey");
		qs.add(q1);
		qs.add(q2);
		Project p = new Project("ProjeRunwayct ", qs, ProjectSize.MEDIUM);
		qs.clear();
		assertEquals(2, p.getRequiredQualifications().size());
	}

	@Test
	public void test_newQualification_addQualification() {
		Set<Qualification> qs = new HashSet<>();
		Qualification q1 = new Qualification("Sean Kelley");
		Qualification q2 = new Qualification("Grace Kelsey");
		qs.add(q1);

		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);

		assertEquals(1, p.getRequiredQualifications().size());
		assertTrue(p.getRequiredQualifications().contains(q1));

		p.addQualification(q2);

		assertEquals(2, p.getRequiredQualifications().size());
		assertTrue(p.getRequiredQualifications().contains(q1));
		assertTrue(p.getRequiredQualifications().contains(q2));
	}

	@Test
	public void test_duplicateQualification_addQualification() {
		Set<Qualification> qs = new HashSet<>();
		Qualification q1 = new Qualification("Sean Kelley");
		qs.add(q1);

		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);

		p.addQualification(q1);

		assertEquals(1, p.getRequiredQualifications().size());
		assertTrue(p.getRequiredQualifications().contains(q1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nullQualification_addQualification() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Sean Kelley"));

		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);

		p.addQualification(null);
	}

	@Test
	public void test_hasWorkers_removeAllWorkers() {
		Set<Qualification> qs = new HashSet<>();
		Qualification q = new Qualification("Java");
		qs.add(q);

		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);
		Worker w1 = new Worker("Bob", qs, 1000.0);
		Worker w2 = new Worker("Susan", qs, 2000.0);

		p.addWorker(w1);
		p.addWorker(w2);

		assertEquals(2, p.getWorkers().size());

		p.removeAllWorkers();

		assertEquals(0, p.getWorkers().size());
	}

	@Test
	public void test_noWorkers_removeAllWorkers() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));

		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);

		assertEquals(0, p.getWorkers().size());

		p.removeAllWorkers();

		assertEquals(0, p.getWorkers().size());
	}

	@Test
	public void test_noWorkers_getMissingQualifications() {
		Set<Qualification> qs = new HashSet<>();
		Qualification q1 = new Qualification("Sean Kelley");
		Qualification q2 = new Qualification("Grace Kelsey");
		qs.add(q1);
		qs.add(q2);

		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);

		Set<Qualification> missing = p.getMissingQualifications();

		assertEquals(2, missing.size());
		assertTrue(missing.contains(q1));
		assertTrue(missing.contains(q2));
	}

	@Test
	public void test_someCovered_getMissingQualifications() {
		Set<Qualification> projectQs = new HashSet<>();
		Qualification q1 = new Qualification("Sean Kelley");
		Qualification q2 = new Qualification("Grace Kelsey");
		projectQs.add(q1);
		projectQs.add(q2);

		Project p = new Project("Project Runway", projectQs, ProjectSize.MEDIUM);

		Set<Qualification> workerQs = new HashSet<>();
		workerQs.add(q1);
		Worker w = new Worker("Bob b", workerQs, 100000.0);

		p.addWorker(w);

		Set<Qualification> missing = p.getMissingQualifications();

		assertEquals(1, missing.size());
		assertTrue(missing.contains(q2));
		assertFalse(missing.contains(q1));
	}

	@Test
	public void test_allCovered_getMissingQualifications() {
		Set<Qualification> projectQs = new HashSet<>();
		Qualification q1 = new Qualification("Sean Kelley");
		Qualification q2 = new Qualification("Grace Kelsey");
		projectQs.add(q1);
		projectQs.add(q2);

		Project p = new Project("Project Runway", projectQs, ProjectSize.MEDIUM);

		Set<Qualification> workerQs = new HashSet<>();
		workerQs.add(q1);
		workerQs.add(q2);
		Worker w = new Worker("Bob b", workerQs, 100000.0);

		p.addWorker(w);

		Set<Qualification> missing = p.getMissingQualifications();

		assertNotNull(missing);
		assertEquals(0, missing.size());
	}

	@Test
	public void test_expectedState_toDTO() {
		Set<Qualification> projectQs = new HashSet<>();
		Qualification q1 = new Qualification("Bob B.");
		projectQs.add(q1);

		Project p = new Project("Project Runway", projectQs, ProjectSize.MEDIUM);

		Set<Qualification> workerQs = new HashSet<>();
		workerQs.add(q1);
		Worker w = new Worker("Bob b", workerQs, 100000.0);

		p.addWorker(w);

		ProjectDTO actualDTO = p.toDTO();
		
		assertEquals("Project Runway", actualDTO.getName());
		assertTrue(actualDTO.getMissingQualifications().length == 0);
		assertTrue(actualDTO.getQualifications().length == 1);
		assertTrue(actualDTO.getSize() == ProjectSize.MEDIUM);
		assertTrue(Arrays.asList(actualDTO.getWorkers()).contains("Bob b"));
		assertTrue(Arrays.asList(actualDTO.getQualifications()).contains("Bob B."));
	}

	@Test
	public void test_bigSize_toDTO() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));
		Project p = new Project("Project", qs, ProjectSize.BIG);
		ProjectDTO dto = p.toDTO();
		assertEquals(ProjectSize.BIG, dto.getSize());
	}

	@Test
	public void test_smallPlanned_toDTO() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));
		Project p = new Project("SmallProject", qs, ProjectSize.SMALL);
		ProjectDTO dto = p.toDTO();
		assertEquals(ProjectSize.SMALL, dto.getSize());
		assertEquals(ProjectStatus.PLANNED, dto.getStatus());
	}

	@Test
	public void test_mediumActive_toDTO() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));
		Project p = new Project("MediumProject", qs, ProjectSize.MEDIUM);
		p.setStatus(ProjectStatus.ACTIVE);
		ProjectDTO dto = p.toDTO();
		assertEquals(ProjectSize.MEDIUM, dto.getSize());
		assertEquals(ProjectStatus.ACTIVE, dto.getStatus());
	}

	@Test
	public void test_bigSuspended_toDTO() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));
		Project p = new Project("BigProject", qs, ProjectSize.BIG);
		p.setStatus(ProjectStatus.SUSPENDED);
		ProjectDTO dto = p.toDTO();
		assertEquals(ProjectSize.BIG, dto.getSize());
		assertEquals(ProjectStatus.SUSPENDED, dto.getStatus());
	}

	@Test
	public void test_smallFinished_toDTO() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));
		Project p = new Project("FinishedProject", qs, ProjectSize.SMALL);
		p.setStatus(ProjectStatus.FINISHED);
		ProjectDTO dto = p.toDTO();
		assertEquals(ProjectSize.SMALL, dto.getSize());
		assertEquals(ProjectStatus.FINISHED, dto.getStatus());
	}

	@Test
	public void test_wrong_missing_qualifications_toDTO() {
		Set<Qualification> projectQs = new HashSet<>();
		Qualification q1 = new Qualification("Bob B.");
		projectQs.add(q1);

		Project p = new Project("Project Runway", projectQs, ProjectSize.MEDIUM);

		Set<Qualification> workerQs = new HashSet<>();
		workerQs.add(new Qualification("Java"));
		Worker w = new Worker("Bob b", workerQs, 100000.0);

		p.addWorker(w);

		ProjectDTO actualDTO = p.toDTO();
		assertTrue(Arrays.asList(actualDTO.getMissingQualifications()).contains("Bob B."));
	}
}