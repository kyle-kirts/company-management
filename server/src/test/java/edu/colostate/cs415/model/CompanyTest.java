package edu.colostate.cs415.model;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

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
	public void test_isCompany_notequalCompanies_equals(){
		Company c = new Company("Nvidia");
		Company f = new Company("AMD");

		assertFalse(c.equals(f));
	}

	@Test
	public void test_nullCompany_notequalCompanies_equals(){
		Company c = new Company("Nvidia");

		assertFalse(c.equals(null));
	}

	@Test
	public void test_notCompany_notequalCompanies_equals(){
		Company c = new Company("Nvidia");

		assertFalse(c.equals("notaCompany"));
	}

	@Test
	public void test_isCompany_equalCompanies_equals(){
		Company c = new Company("Nvidia");
		Company f = new Company("Nvidia");

		assertTrue(c.equals(f));
	}
	@Test
    public void test_getEmployedWorkers_emptyAtStart() {
        Company c = new Company("TestCo");
        assertEquals(0, c.getEmployedWorkers().size());
    }

    @Test
    public void test_createWorker_addsToEmployedWorkers() {
        Company c = new Company("TestCo");

        Set<Qualification> qs = new HashSet<>();
        // We are not testing qualifications behavior here, so keep it empty.

        Worker w1 = c.createWorker("Alice", qs, 50000);
        Worker w2 = c.createWorker("Bob", qs, 60000);

        assertNotNull(w1);
        assertNotNull(w2);
        assertEquals(2, c.getEmployedWorkers().size());
        assertTrue(c.getEmployedWorkers().contains(w1));
        assertTrue(c.getEmployedWorkers().contains(w2));
    }

    @Test
    public void test_getEmployedWorkers_defensiveCopy() {
        Company c = new Company("TestCo");

        Worker w = c.createWorker("Alice", Collections.emptySet(), 50000);

        Set<Worker> employed = c.getEmployedWorkers();
        assertEquals(1, employed.size());

        // Mutate returned set
        employed.clear();

        // Company should still have the worker
        assertEquals(1, c.getEmployedWorkers().size());
        assertTrue(c.getEmployedWorkers().contains(w));
    }

   @Test
public void test_createWorker_invalidName() {
    Company c = new Company("TestCo");
    try {
        c.createWorker("   ", Collections.emptySet(), 50000);
        fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
        // expected
    }
}

@Test
public void test_createWorker_nullQualificationsSet() {
    Company c = new Company("TestCo");
    try {
        c.createWorker("Alice", null, 50000);
        fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
        // expected
    }
}

@Test
public void test_createWorker_invalidSalary() {
    Company c = new Company("TestCo");

    try {
        c.createWorker("Alice", Collections.emptySet(), 0);
        fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
        // expected
    }

    try {
        c.createWorker("Alice", Collections.emptySet(), -10);
        fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
        
    }
}