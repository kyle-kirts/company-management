package edu.colostate.cs415.model;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class WorkerTest {
	@Test
	public void test() {
		assert (true);
	}
	
	@Test()
	public void test_validWorker_Worker() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Qualification"));
		Worker worker = new Worker("Bob B", qs, 1.00);

	}

	@Test(expected = IllegalArgumentException.class)
	public void test_emptyName_Worker() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Qualification"));
		Worker worker = new Worker("", qs, 1.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_whitespaceName_Worker() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Qualification"));
		Worker worker = new Worker("   ", qs, 1.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nullName_Worker() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Qualification"));
		Worker worker = new Worker(null, qs, 1.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_negativeSalary_Worker() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Qualification"));
		Worker worker = new Worker("Bob B", qs, -1.00);
	}

	@Test
	public void test_zeroSalary_Worker() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Qualification"));
		Worker worker = new Worker("Bob B", qs, 0.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nullQsSet_Worker() {
		Worker worker = new Worker("Bob B", null, 1.00);
	}

	@Test
	public void test_emptyQsSet_Worker() {
		Set<Qualification> qs = new HashSet<>();
		Worker worker = new Worker("Bob B", qs, 1.00);
	}

	@Test()
	public void test_validNameHash_hashCode() {
		int hashInteger = -307059209;
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Qualification"));
		Worker worker = new Worker("valid description", qs, 1.00);

		assertEquals("Hashcode should match hashInteger", worker.hashCode(), hashInteger);


	}
}
