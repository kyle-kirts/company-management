package edu.colostate.cs415.model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ProjectTest {
	@Test
	public void test_nonNullName_someQualifications_mediumProject() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Sean Kelley"));
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);

		assertNotNull(p);
		assertEquals("Project Runway", p.getName());
		//assertEquals(2, p.getSize().getValue());
		//assertEquals(ProjectStatus.PLANNED, p.getStatus());
		//assertEquals(0, p.getWorkers.size());
		//assertEquals(2, p.getRequiredQualifications().size());
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_NullName_someQualifications_mediumProject(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Sean Kelley"));
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project(null, qs, ProjectSize.MEDIUM);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_emptyName_someQualifications_mediumProject(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Sean Kelley"));
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project("", qs, ProjectSize.MEDIUM);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nonNullName_nullQualifications_mediumProject(){
		Project p = new Project("Project Runway", null, ProjectSize.MEDIUM);
	}

	@Test
	public void test_nonNullName_noQualifications_mediumProject(){
		Set<Qualification> qs = new HashSet<>();
		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);

		assertNotNull(p);
		//assertEquals(0, p.getRequiredQualifications().size());

	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nonNullName_someQualifications_nullProject(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Sean Kelley"));
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project("Project Runway", qs, null);
	}

	@Test
	public void test_nonNullName_someQualifications_smallProject(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Sean Kelley"));
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project("Project Runway", qs, ProjectSize.SMALL);

		assertNotNull(p);
		//assertEquals(1, p.getSize().getValue());
	}

	@Test
	public void test_nonNullName_someQualifications_largeProject(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Sean Kelley"));
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project("Project Runway", qs, ProjectSize.BIG);

		assertNotNull(p);
		//assertEquals(3, p.getSize().getValue());
	}

	@Test
	public void test_validName_getName(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Sean Kelley"));
		qs.add(new Qualification("Grace Kelsey"));
		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);
		assertEquals(p.getName(), "Project Runway");
	}

	@Test()
	public void test_validNameHash_hashCode() {
		int hashInteger = -307059209;
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Qualification"));
		Project p = new Project("valid description", qs, ProjectSize.MEDIUM);

		assertEquals("Hashcode should match hashInteger", p.hashCode(), hashInteger);
	}
}
