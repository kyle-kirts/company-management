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
}