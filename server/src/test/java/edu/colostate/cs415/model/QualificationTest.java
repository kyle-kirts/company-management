package edu.colostate.cs415.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class QualificationTest {
	@Test
	public void test() {
		assert (true);
	}

	@Test
	public void test_validDescription_toString() {
		Qualification q = new Qualification("Valid Description");
		assertEquals("Valid Description", q.toString());
	}

	@Test
	public void test_nullType_toString() {
		Qualification q = new Qualification(null);
		assertNull(q.toString());
	}

	@Test
	public void test_emptyString_toString() {
		Qualification q = new Qualification("");
		assertEquals("", q.toString());
	}

}
