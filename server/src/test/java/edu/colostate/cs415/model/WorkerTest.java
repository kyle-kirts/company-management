package edu.colostate.cs415.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class WorkerTest {
	@Test
	public void test() {
		assert (true);
	}
	
	@Test
	public void test_validWorker_Worker() {
		Set<Qualification> qs = new HashSet<>();
		Qualification q = new Qualification("Qualification");
		qs.add(q);
		Worker worker = new Worker("Bob B", qs, 1.00);

		assertTrue(worker.getQualifications().contains(q));
		/*assertTrue(worker.getName().contains("Bob B")) */
		assertEquals(1.00, worker.getSalary(), 0.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_emptyName_Worker() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Qualification"));
		new Worker("", qs, 1.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_whitespaceName_Worker() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Qualification"));
		new Worker("   ", qs, 1.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nullName_Worker() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Qualification"));
		new Worker(null, qs, 1.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_negativeSalary_Worker() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Qualification"));
		new Worker("Bob B", qs, -1.00);
	}

	@Test
	public void test_zeroSalary_Worker() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Qualification"));
		Worker worker = new Worker("Bob B", qs, 0.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nullQsSet_Worker() {
		new Worker("Bob B", null, 1.00);
	}

	@Test
	public void test_emptyQsSet_Worker() {
		Set<Qualification> qs = new HashSet<>();
		Worker worker = new Worker("Bob B", qs, 1.00);

		assertTrue(worker.getQualifications().isEmpty());
	}

	@Test
	public void test_nonemptyQsSet_Worker() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Qualification"));
		Worker worker = new Worker("Bob B", qs, 1.00);

		assertTrue(!(worker.getQualifications().isEmpty()));
	}

	@Test
	public void test_emptyQualifications_getQualifications() {
		Set<Qualification> qs = new HashSet<>();
		Worker worker = new Worker("Bob B", qs,1.00);
		assertTrue(worker.getQualifications().isEmpty());
	}

	@Test
	public void test_multipleQualifications_getQualifications() {
		Set<Qualification> qs = new HashSet<>();
		Qualification q1 = new Qualification("Qualification1");
		Qualification q2 = new Qualification("Qualification2");
		qs.add(q1);
		qs.add(q2);
		Worker worker = new Worker("Bob B", qs, 1.00);

		assertTrue(worker.getQualifications().contains(q1));
		assertTrue(worker.getQualifications().contains(q2));
	}

	@Test()
	public void test_validNameHash_hashCode() {
		int hashInteger = -307059209;
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Qualification"));
		Worker worker = new Worker("valid description", qs, 1.00);

		assertEquals("Hashcode should match hashInteger", worker.hashCode(), hashInteger);


	}

	@Test
	public void test_zeroSalary_getSalary() {
		Set<Qualification> qs = new HashSet<>();
		Worker worker = new Worker("Bob B", qs, 0.00);

		assertEquals(0.00, worker.getSalary(), 0.0);
	}

	@Test
	public void test_positiveSalary_getSalary() {
		Set<Qualification> qs = new HashSet<>();
		Worker worker = new Worker("Bob B", qs, 1000.00);

		assertEquals(1000.00, worker.getSalary(), 0.0);
	}

}
