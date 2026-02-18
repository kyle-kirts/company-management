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
		assertTrue(worker.getName().contains("Bob B"));
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

		assertEquals(0.00, worker.getSalary(), 0.00 );
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nanSalary_Worker() {
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Qualification"));
		new Worker("Bob B", qs, Double.NaN);
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

	public void test_noProjects_LongQualifications_normalSalary_toString(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Synkronized"));
		qs.add(new Qualification("Dynamite"));
		Worker worker = new Worker("Jamiroquai", qs, 123456);
		assertEquals("this should work", worker.toString(), "Jamiroquai:0:2:123456");
	}

	@Test
	public void test_LongProjects_LongQualifications_normalSalary_toString(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Synkronized"));
		qs.add(new Qualification("Dynamite"));
		//Must come back when addProjects mutator is implemented. For now, is a copy of base case test
		Worker worker = new Worker("Jamiroquai", qs, 123456);
		assertEquals(worker.toString(), "Jamiroquai:0:2:123456");
	}

	@Test
	public void test_noProjects_NoQualifications_normalSalary_toString(){
		Set<Qualification> qs = new HashSet<>();
		Worker worker = new Worker("Jamiroquai", qs, 123456);
		assertEquals(worker.toString(), "Jamiroquai:0:0:123456");
	}

	@Test
	public void test_noProjects_LongQualifications_noSalary_toString(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Synkronized"));
		qs.add(new Qualification("Dynamite"));
		Worker worker = new Worker("Jamiroquai", qs, 0);
		assertEquals(worker.toString(), "Jamiroquai:0:2:0");
	}

	@Test
	public void test_noProjects_LongQualifications_hugeSalary_toString(){
		Set<Qualification> qs = new HashSet<>();
		qs.add(new Qualification("Synkronized"));
		qs.add(new Qualification("Dynamite"));
		Worker worker = new Worker("Jamiroquai", qs, Integer.MAX_VALUE+100.0);
		assertEquals(worker.toString(), "Jamiroquai:0:2:2147483747");
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

	@Test
	public void test_validName_getName() {
		Set<Qualification> qs = new HashSet<>();
		Worker worker = new Worker("Bob B", qs, 1000.00);

		assertEquals("Bob B", worker.getName());
	}

	@Test
	public void test_positiveSalary_setSalary() {
		Set<Qualification> qs = new HashSet<>();
		Worker worker = new Worker("Bob B", qs, 1000.00);
		worker.setSalary(100.00);

		assertEquals(100.00, worker.getSalary(), 0.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_negativeSalary_setSalary() {
		Set<Qualification> qs = new HashSet<>();
		Worker worker = new Worker("Bob B", qs, 0.00);
		worker.setSalary(-100.00);
	}

	@Test
	public void test_zeroSalary_setSalary() {
		Set<Qualification> qs = new HashSet<>();
		Worker worker = new Worker("Bob B", qs, 1000.00);
		worker.setSalary(0.00);

		assertEquals(0.00, worker.getSalary(), 0.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nanSalary_setSalary() {
		Set<Qualification> qs = new HashSet<>();
		Worker worker = new Worker("Bob B", qs, 1000.00);
		worker.setSalary(Double.NaN);
	}
}
