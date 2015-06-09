package org.fluent.parser.csv;

import org.fluent.parser.csv.exception.CsvException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by strifan on 04.06.2015.
 */
public class DefaultItemFactoryTest {

	@Test
	public void createWithDefaultConstructor() {
		ItemFactory<DefaultItemFactoryTest> itemFactory = new DefaultItemFactory<DefaultItemFactoryTest>(DefaultItemFactoryTest.class);
		DefaultItemFactoryTest factoryTest = itemFactory.create();
		assertTrue(factoryTest instanceof DefaultItemFactoryTest);
	}

	@Test(expected = CsvException.class)
	public void createWithoutDefaultConstructor() {
		new DefaultItemFactory(DefaultItemFactory.class);
	}

}