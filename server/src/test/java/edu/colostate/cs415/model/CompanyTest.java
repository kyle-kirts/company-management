package edu.colostate.cs415.model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class CompanyTest {

	@Test
	public void test_validName_Constructor() {
		Company c = new Company("TestCo");
		assertNotNull(c);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nullName_Constructor() {
		new Company(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_emptyName_Constructor() {
		new Company("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_whitespaceName_Constructor() {
		new Company(" ");
	}

	@Test
	public void test_validDescription_createQualification() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("Java");

		assertNotNull(q);
		assertEquals("Java", q.toString());
		assertEquals(1, c.getQualifications().size());
		assertTrue(c.getQualifications().contains(q));
	}

	@Test
	public void test_nullDescription_createQualification() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification(null);

		assertNull(q);
		assertEquals(0, c.getQualifications().size());
	}

	@Test
	public void test_emptyDescription_createQualification() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("");

		assertNull(q);
		assertEquals(0, c.getQualifications().size());
	}

	@Test
	public void test_validWorker_createWorker() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("Java");
		Set<Qualification> qs = new HashSet<>();
		qs.add(q);

		Worker w = c.createWorker("Bob", qs, 1000.0);

		assertNotNull(w);
		assertEquals("Bob", w.getName());
		assertEquals(1000.0, w.getSalary(), 0.0);

		assertEquals(1, c.getEmployedWorkers().size());
		assertEquals(1, c.getAvailableWorkers().size());
		assertTrue(c.getEmployedWorkers().contains(w));
		assertTrue(c.getAvailableWorkers().contains(w));

		assertTrue(q.getWorkers().contains(w));
	}

	@Test
	public void test_nullName_createWorker() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("Java");
		Set<Qualification> qs = new HashSet<>();
		qs.add(q);

		Worker w = c.createWorker(null, qs, 1000.0);

		assertNull(w);
		assertEquals(0, c.getEmployedWorkers().size());
		assertEquals(0, c.getAvailableWorkers().size());
	}

	@Test
	public void test_emptyQualifications_createWorker() {
		Company c = new Company("ABC");

		Set<Qualification> qs = new HashSet<>();

		Worker w = c.createWorker("Bob", qs, 1000.0);

		assertNull(w);
		assertEquals(0, c.getEmployedWorkers().size());
		assertEquals(0, c.getAvailableWorkers().size());
	}

	@Test
	public void test_nonCompanyQualification_createWorker() {
		Company c = new Company("ABC");

		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));

		Worker w = c.createWorker("Bob", qs, 1000.0);

		assertNull(w);
		assertEquals(0, c.getEmployedWorkers().size());
		assertEquals(0, c.getAvailableWorkers().size());
	}

	@Test
	public void test_negativeSalary_createWorker() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("Java");
		Set<Qualification> qs = new HashSet<>();
		qs.add(q);

		Worker w = c.createWorker("Bob", qs, -1.0);

		assertNull(w);
		assertEquals(0, c.getEmployedWorkers().size());
		assertEquals(0, c.getAvailableWorkers().size());
		assertFalse(q.getWorkers().contains(w));
	}

	@Test
	public void test_nanSalary_createWorker() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("Java");
		Set<Qualification> qs = new HashSet<>();
		qs.add(q);

		Worker w = c.createWorker("Bob", qs, Double.NaN);

		assertNull(w);
		assertEquals(0, c.getEmployedWorkers().size());
		assertEquals(0, c.getAvailableWorkers().size());
	}

	@Test
	public void test_noQualifications_getQualifications() {
		Company c = new Company("ABC");

		assertNotNull(c.getQualifications());
		assertEquals(0, c.getQualifications().size());
		assertTrue(c.getQualifications().isEmpty());
	}

	@Test
	public void test_oneQualification_getQualifications() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("Java");

		assertNotNull(q);
		assertNotNull(c.getQualifications());
		assertEquals(1, c.getQualifications().size());
		assertTrue(c.getQualifications().contains(q));
	}

	@Test
	public void test_multipleQualifications_getQualifications() {
		Company c = new Company("ABC");

		Qualification q1 = c.createQualification("Java");
		Qualification q2 = c.createQualification("SQL");

		assertNotNull(q1);
		assertNotNull(q2);
		assertNotNull(c.getQualifications());
		assertEquals(2, c.getQualifications().size());
		assertTrue(c.getQualifications().contains(q1));
		assertTrue(c.getQualifications().contains(q2));
	}

	@Test
	public void test_sameName_hashCode() {
		Company c1 = new Company("ABC");
		Company c2 = new Company("ABC");

		assertEquals(c1.hashCode(), c2.hashCode());
	}

	@Test
	public void test_validName_hashCode() {
		Company c = new Company("ABC");

		assertEquals("ABC".hashCode(), c.hashCode());
	}

	@Test
	public void test_validName_getName() {
		Company c = new Company("ABC");

		assertEquals("ABC", c.getName());
	}

	@Test
	public void test_anotherValidName_getName() {
		Company c = new Company("XYZ");

		assertEquals("XYZ", c.getName());
	}

	@Test
	public void test_isCompany_notequalCompanies_equals() {
		Company c = new Company("Nvidia");
		Company f = new Company("AMD");

		assertFalse(c.equals(f));
	}

	@Test
	public void test_nullCompany_notequalCompanies_equals() {
		Company c = new Company("Nvidia");

		assertFalse(c.equals(null));
	}

	@Test
	public void test_notCompany_notequalCompanies_equals() {
		Company c = new Company("Nvidia");

		assertFalse(c.equals("notaCompany"));
	}

	@Test
	public void test_isCompany_equalCompanies_equals() {
		Company c = new Company("Nvidia");
		Company f = new Company("Nvidia");

		assertTrue(c.equals(f));
	}

	@Test
	public void test_noWorkers_getAvailableWorkers() {
		Company c = new Company("ABC");

		assertNotNull(c.getAvailableWorkers());
		assertEquals(0, c.getAvailableWorkers().size());
		assertTrue(c.getAvailableWorkers().isEmpty());
	}

	@Test
	public void test_oneWorker_getAvailableWorkers() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("Java");
		Set<Qualification> qs = new HashSet<>();
		qs.add(q);

		Worker w = c.createWorker("Bob", qs, 1000.0);

		assertNotNull(w);
		assertEquals(1, c.getAvailableWorkers().size());
		assertTrue(c.getAvailableWorkers().contains(w));
	}

	@Test
	public void test_twoWorkers_getAvailableWorkers() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("Java");
		Set<Qualification> qs = new HashSet<>();
		qs.add(q);

		Worker w1 = c.createWorker("Bob", qs, 1000.0);
		Worker w2 = c.createWorker("Susan", qs, 2000.0);

		assertNotNull(w1);
		assertNotNull(w2);
		assertEquals(2, c.getAvailableWorkers().size());
		assertTrue(c.getAvailableWorkers().contains(w1));
		assertTrue(c.getAvailableWorkers().contains(w2));
	}

	@Test
	public void test_getEmployedWorkers_emptyAtStart() {
		Company c = new Company("TestCo");

		assertNotNull(c.getEmployedWorkers());
		assertEquals(0, c.getEmployedWorkers().size());
		assertTrue(c.getEmployedWorkers().isEmpty());
	}

	@Test
	public void test_twoWorkers_getEmployedWorkers() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("Java");
		Set<Qualification> qs = new HashSet<>();
		qs.add(q);

		Worker w1 = c.createWorker("Bob", qs, 1000.0);
		Worker w2 = c.createWorker("Susan", qs, 2000.0);

		assertNotNull(w1);
		assertNotNull(w2);
		assertEquals(2, c.getEmployedWorkers().size());
		assertTrue(c.getEmployedWorkers().contains(w1));
		assertTrue(c.getEmployedWorkers().contains(w2));
	}

	@Test
	public void test_getEmployedWorkers_defensiveCopy() {
		Company c = new Company("TestCo");

		try {
			java.lang.reflect.Field f = Company.class.getDeclaredField("employees");
			f.setAccessible(true);

			@SuppressWarnings("unchecked")
			Set<Worker> employees = (Set<Worker>) f.get(c);

			Set<Qualification> qs = new HashSet<>();
			qs.add(new Qualification("Java"));
			Worker w = new Worker("Alice", qs, 50000);
			employees.add(w);

			Set<Worker> copy = c.getEmployedWorkers();
			assertEquals(1, copy.size());

			copy.clear();

			assertEquals(1, c.getEmployedWorkers().size());

		} catch (Exception e) {
			fail("Reflection failed: " + e.getMessage());
		}
	}

	@Test
	public void test_nonEmptyName_hasQualifications_validEnum_createProject() {
		Company c = new Company("AMD");
		c.createQualification("design");
		c.createQualification("walking");

		Set<Qualification> qs = new HashSet<Qualification>();
		qs.add(new Qualification("design"));

		assertEquals(0, c.getProjects().size());

		c.createProject("Project Runway", qs, ProjectSize.MEDIUM);

		assertEquals(1, c.getProjects().size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nullName_hasQualifications_validEnum_createProject() {
		Company c = new Company("AMD");
		c.createQualification("design");
		c.createQualification("walking");

		Set<Qualification> qs = new HashSet<Qualification>();
		qs.add(new Qualification("design"));

		c.createProject(null, qs, ProjectSize.MEDIUM);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_emptyName_hasQualifications_validEnum_createProject() {
		Company c = new Company("AMD");
		c.createQualification("design");
		c.createQualification("walking");

		Set<Qualification> qs = new HashSet<Qualification>();
		qs.add(new Qualification("design"));

		c.createProject("", qs, ProjectSize.MEDIUM);
	}

	@Test
	public void test_nonEmptyName_noQualifications_validEnum_createProject() {
		Company c = new Company("AMD");
		c.createQualification("design");
		c.createQualification("walking");

		assertEquals(0, c.getProjects().size());

		Set<Qualification> qs = new HashSet<Qualification>();
		c.createProject("Project Runway", qs, ProjectSize.MEDIUM);

		assertEquals(1, c.getProjects().size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nonEmptyName_hasQualifications_nullEnum_createProject() {
		Company c = new Company("AMD");
		c.createQualification("design");
		c.createQualification("walking");

		Set<Qualification> qs = new HashSet<Qualification>();
		qs.add(new Qualification("design"));

		c.createProject("Project Runway", qs, null);
	}

	@Test
	public void test_notEmpty_getProjects() {
		Company c = new Company("AMD");

		Set<Qualification> qs = new HashSet<Qualification>();
		qs.add(new Qualification("design"));

		c.createProject("Project Runway", qs, ProjectSize.MEDIUM);

		assertEquals(1, c.getProjects().size());
	}

	@Test
	public void test_empty_getProjects() {
		Company c = new Company("AMD");

		assertEquals(0, c.getProjects().size());
	}

	@Test
	public void test_normalString_hasWorkers_hasProjects_toString() {
		Company c = new Company("Nvidia");
		c.createQualification("useless");
		Set<Qualification> qs = new HashSet<Qualification>();
		qs.add(new Qualification("useless"));
		c.createWorker("Bob b", qs, 10450.5);
		c.createWorker("Bettie Boop", qs, 79050.5);
		c.createProject("Project Runway", qs, ProjectSize.MEDIUM);

		assertEquals("Nvidia:2:1", c.toString());
	}

	@Test
	public void test_normalString_noWorkers_hasProjects_toString() {
		Company c = new Company("Nvidia");
		Set<Qualification> qs = new HashSet<Qualification>();
		c.createProject("Project Runway", qs, ProjectSize.MEDIUM);

		assertEquals("Nvidia:0:1", c.toString());
	}

	@Test
	public void test_normalString_hasWorkers_noProjects_toString() {
		Company c = new Company("Nvidia");
		c.createQualification("useless");
		Set<Qualification> qs = new HashSet<Qualification>();
		qs.add(new Qualification("useless"));
		c.createWorker("Bob b", qs, 10450.5);
		c.createWorker("Bettie Boop", qs, 79050.5);

		assertEquals("Nvidia:2:0", c.toString());
	}

	@Test
	public void test_plannedNoMissing_start() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("Java");
		Set<Qualification> projectQs = new HashSet<>();
		projectQs.add(q);

		Project p = c.createProject("Project Runway", projectQs, ProjectSize.MEDIUM);

		Set<Qualification> workerQs = new HashSet<>();
		workerQs.add(q);
		Worker w = c.createWorker("Bob", workerQs, 1000.0);

		p.addWorker(w);

		c.start(p);

		assertEquals(ProjectStatus.ACTIVE, p.getStatus());
	}

	@Test
	public void test_plannedMissingQualifications_start() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("Java");
		Set<Qualification> projectQs = new HashSet<>();
		projectQs.add(q);

		Project p = c.createProject("Project Runway", projectQs, ProjectSize.MEDIUM);

		c.start(p);

		assertEquals(ProjectStatus.PLANNED, p.getStatus());
	}

	@Test
	public void test_suspendedNoMissing_start() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("Java");
		Set<Qualification> projectQs = new HashSet<>();
		projectQs.add(q);

		Project p = c.createProject("Project Runway", projectQs, ProjectSize.MEDIUM);
		p.setStatus(ProjectStatus.SUSPENDED);

		Set<Qualification> workerQs = new HashSet<>();
		workerQs.add(q);
		Worker w = c.createWorker("Bob", workerQs, 1000.0);

		p.addWorker(w);

		c.start(p);

		assertEquals(ProjectStatus.ACTIVE, p.getStatus());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nullProject_start() {
		Company c = new Company("ABC");

		c.start(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_projectNotInCompany_start() {
		Company c = new Company("ABC");

		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Java"));
		Project p = new Project("Project Runway", qs, ProjectSize.MEDIUM);

		c.start(p);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nullWorker_unassign() {
		Company c = new Company("ABC");

		Set<Qualification> qs = new HashSet<>();
		qs.add(c.createQualification("Java"));
		Project p = c.createProject("Project Runway", qs, ProjectSize.MEDIUM);

		c.unassign(null, p);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nullProject_unassign() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("Java");
		Set<Qualification> qs = new HashSet<>();
		qs.add(q);
		Worker w = c.createWorker("Bob", qs, 1000.0);

		c.unassign(w, null);
	}

	@Test
	public void test_removesWorkerFromProjectAndWorker_unassign() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("Java");
		Set<Qualification> qs = new HashSet<>();
		qs.add(q);

		Worker w = c.createWorker("Bob", qs, 1000.0);
		Project p = c.createProject("Project Runway", qs, ProjectSize.MEDIUM);

		p.addWorker(w);
		w.addProject(p);

		c.unassign(w, p);

		assertFalse(p.getWorkers().contains(w));
		assertFalse(w.getProjects().contains(p));
	}
	@Test(expected = IllegalArgumentException.class)
	public void test_nullWorker_unassignAll() {
		Company c = new Company("ABC");

		c.unassignAll(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nonEmployee_unassignAll() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("Java");
		Set<Qualification> qs = new HashSet<>();
		qs.add(q);

		Worker w = new Worker("Bob", qs, 1000.0); // not created by company

		c.unassignAll(w);
	}

	@Test
	public void test_noProjects_unassignAll() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("Java");
		Set<Qualification> qs = new HashSet<>();
		qs.add(q);

		Worker w = c.createWorker("Bob", qs, 1000.0);

		assertTrue(w.getProjects().isEmpty());
		assertTrue(c.getAvailableWorkers().contains(w));

		c.unassignAll(w);

		assertTrue(w.getProjects().isEmpty());
		assertTrue(c.getAvailableWorkers().contains(w));
	}

	@Test
	public void test_companyProjectRemovedAndActiveProjectSuspended_unassignAll() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("Java");
		Set<Qualification> qs = new HashSet<>();
		qs.add(q);

		Worker w = c.createWorker("Bob", qs, 1000.0);
		Project p = c.createProject("Project Runway", qs, ProjectSize.MEDIUM);

		p.addWorker(w);
		w.addProject(p);
		p.setStatus(ProjectStatus.ACTIVE);

		assertTrue(p.getWorkers().contains(w));
		assertTrue(w.getProjects().contains(p));

		c.unassignAll(w);

		assertFalse(p.getWorkers().contains(w));
		assertFalse(w.getProjects().contains(p));
		assertEquals(ProjectStatus.SUSPENDED, p.getStatus());
		assertTrue(c.getAvailableWorkers().contains(w));
	}

	@Test
	public void test_nonCompanyProjectsSkippedAndWorkerBecomesUnavailable_unassignAll() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("Java");
		Set<Qualification> qs = new HashSet<>();
		qs.add(q);

		Worker w = c.createWorker("Bob", qs, 1000.0);

		Project p1 = new Project("P1", qs, ProjectSize.BIG);
		Project p2 = new Project("P2", qs, ProjectSize.BIG);
		Project p3 = new Project("P3", qs, ProjectSize.BIG);
		Project p4 = new Project("P4", qs, ProjectSize.BIG);

		w.addProject(p1);
		w.addProject(p2);
		w.addProject(p3);
		w.addProject(p4);

		assertFalse(w.isAvailable());
		assertTrue(c.getAvailableWorkers().contains(w)); 

		c.unassignAll(w);

		assertEquals(4, w.getProjects().size());

		assertFalse(c.getAvailableWorkers().contains(w));
	}

	@Test
	public void test_multipleCompanyProjects_unassignAll() {
		Company c = new Company("ABC");

		Qualification q = c.createQualification("Java");
		Set<Qualification> qs = new HashSet<>();
		qs.add(q);

		Worker w = c.createWorker("Bob", qs, 1000.0);
		Project p1 = c.createProject("Project One", qs, ProjectSize.SMALL);
		Project p2 = c.createProject("Project Two", qs, ProjectSize.MEDIUM);

		p1.addWorker(w);
		p2.addWorker(w);
		w.addProject(p1);
		w.addProject(p2);

		assertEquals(2, w.getProjects().size());

		c.unassignAll(w);

		assertEquals(0, w.getProjects().size());
		assertFalse(p1.getWorkers().contains(w));
		assertFalse(p2.getWorkers().contains(w));
		assertTrue(c.getAvailableWorkers().contains(w));
	}
}