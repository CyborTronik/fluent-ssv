package com.github.cybortronik.parser.ssv;

import com.github.cybortronik.parser.ssv.exception.InvalidDefaultSsvHeaderException;
import com.github.cybortronik.parser.ssv.exception.InvalidSetPropertyIndexRequestException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by strifan on 04.06.2015.
 */
public class DefaultIndexedPropertiesTest {

	@Test(expected = InvalidDefaultSsvHeaderException.class)
	public void createWithEmpty() {
		new DefaultIndexedProperties();
	}

	@Test(expected = InvalidDefaultSsvHeaderException.class)
	public void createWithNull() {
		new DefaultIndexedProperties(null);
	}

	@Test
	public void SetOneProperty() {
		IndexedProperties headerProperties = new DefaultIndexedProperties("This is a mega test");
		String setterName = headerProperties.getSetterName(0);
		assertEquals("setThisIsAMegaTest", setterName);
	}

	@Test
	public void SetOnePropertyWithSpecialSigns() {
		IndexedProperties headerProperties = new DefaultIndexedProperties("This is a mega test.");
		String setterName = headerProperties.getSetterName(0);
		assertEquals("setThisIsAMegaTest", setterName);
	}

	@Test
	public void SetTwoProperty() {
		IndexedProperties headerProperties = new DefaultIndexedProperties("This is a mega test", "Another test");
		String setterName = headerProperties.getSetterName(0);
		assertEquals("setThisIsAMegaTest", setterName);

		setterName = headerProperties.getSetterName(1);
		assertEquals("setAnotherTest", setterName);
	}

	@Test(expected = InvalidSetPropertyIndexRequestException.class)
	public void tryGetPropertyForIndexBelowZero() {
		IndexedProperties headerProperties = new DefaultIndexedProperties("This is a mega test");
		headerProperties.getSetterName(-1);
	}

	@Test(expected = InvalidSetPropertyIndexRequestException.class)
	public void tryGetPropertyForIndexMoreThanAvailable() {
		IndexedProperties headerProperties = new DefaultIndexedProperties("This is a mega test");
		headerProperties.getSetterName(2);
	}

}