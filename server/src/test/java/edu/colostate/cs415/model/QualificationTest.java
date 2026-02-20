package edu.colostate.cs415.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class QualificationTest {
	@Test
	public void test() {
		assert (true);
	}

	 @Test(expected = IllegalArgumentException.class)
    public void test_nullDescription_Constructor() {
        new Qualification(null);
    }

	@Test(expected = IllegalArgumentException.class)
	public void test_emptyString_Constructor() {
		new Qualification("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_whiteSpaces_Constructor() {
		new Qualification("    ");
	}

	@Test
	public void test_validDescription_toString() {
		Qualification q = new Qualification("Valid Description");
		assertEquals("Valid Description", q.toString());
	}

	@Test
	public void test_equalOther_equals() {
		Qualification other = new Qualification("valid description");
		Qualification q = new Qualification("valid description");

		assertTrue(q.equals(other));
	}

	@Test
	public void test_unequalOther_equals() {
		Qualification other = new Qualification(" valid description ");
		Qualification q = new Qualification("valid description");

		assertFalse(q.equals(other));
	}

	@Test
	public void test_nullOther_equals() {
		Qualification q = new Qualification("valid description");

		assertFalse(q.equals(null));
	}

	@Test
	public void test_invalidType_equals() {
		Integer other = 10;
		Qualification q = new Qualification("valid description");

		assertFalse(q.equals(other));
	} 

	@Test
	public void test_validDescriptionHash_hashCode() {
		int hashInteger = -307059209;
		Qualification q = new Qualification("valid description");

		assertEquals("Hashcode should match hashInteger", q.hashCode(), hashInteger);
	}

	@Test
	public void test_validWorkerObject_addWorker() {
		Qualification q = new Qualification("valid description");
		Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(q);
		Set<Worker> workers = new HashSet<Worker>();
		Worker worker1 = new Worker("Worker1", qualifications, 0);
		workers.add(worker1);
		q.addWorker(worker1);

		assertEquals(workers, q.getWorkers());
	}

	@Test
	public void test_emptyWorkerSet_getWorkers() {
		Qualification q = new Qualification("valid description");

		assertTrue(q.getWorkers().isEmpty());
	}

	@Test
	public void test_nonNullWorker_removeWorker() {
		Qualification q = new Qualification("valid description");
		Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(q);
		Set<Worker> workers = new HashSet<Worker>();
		Worker worker1 = new Worker("Worker1", qualifications, 0);
		Worker worker2 = new Worker("Worker2", qualifications, 0);
		workers.add(worker1);
		q.addWorker(worker1);
		q.addWorker(worker2);

		q.removeWorker(worker2);
		assertEquals(workers, q.getWorkers());
	}

	@Test
	public void test_nullWorker_removeWorker() {
		Qualification q = new Qualification("valid description");
		Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(q);
		Set<Worker> workers = new HashSet<Worker>();
		Worker worker1 = new Worker("Worker1", qualifications, 0);
		Worker worker2 = new Worker("Worker2", qualifications, 0);
		workers.add(worker1);
		workers.add(worker2);
		q.addWorker(worker1);
		q.addWorker(worker2);

		q.removeWorker(null);
		assertEquals(workers, q.getWorkers());
	}

	@Test
	public void test_nonNullWorkerNotInSet_removeWorker() {
		Qualification q = new Qualification("valid description");
		Set<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(q);
		Set<Worker> workers = new HashSet<Worker>();
		Worker worker1 = new Worker("Worker1", qualifications, 0);
		q.removeWorker(worker1);
	
		assertEquals(workers, q.getWorkers());
	}

}
