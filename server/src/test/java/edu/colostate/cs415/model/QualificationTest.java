package edu.colostate.cs415.model;

import static org.junit.Assert.assertEquals;

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

	@Test
	public void test_validDescription_toString() {
		Qualification q = new Qualification("Valid Description");
		assertEquals("Valid Description", q.toString());
	}

	@Test
	public void test_emptyString_toString() {
		Qualification q = new Qualification("");
		assertEquals("", q.toString());
	}

}
