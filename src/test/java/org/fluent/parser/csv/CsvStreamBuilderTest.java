package org.fluent.parser.csv;

import org.fluent.parser.TestModel;
import org.fluent.parser.csv.exception.CsvStreamBuilderException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by strifan on 04.06.2015.
 */
public class CsvStreamBuilderTest {

	private Stream<String> sampleInput;

	@Before
	public void setUpOnce() {
		sampleInput = new ArrayList<String>() {{
			add("Index, Name");
			add("1, test1");
			add("2, test2");
		}}.stream();
	}

	@Test(expected = CsvStreamBuilderException.class)
	public void streamDefault() {
		new CsvStreamBuilder<>().stream(sampleInput);
	}

	@Test
	public void streamValidDefault() {
		Stream<ParsedRecord<TestModel>> parsedRecordStream = new CsvStreamBuilder<TestModel>().forEntity(TestModel.class).stream(sampleInput);

		Iterator<ParsedRecord<TestModel>> iterator = parsedRecordStream.iterator();
		iterator.hasNext();
		ParsedRecord<TestModel> record = iterator.next();
		assertTrue(record.isParsed());
		TestModel item = record.getItem();
		assertNotNull(item);
		assertEquals(new Integer(1), item.getIndex());
		assertEquals("test1", item.getName());

		iterator.hasNext();
		record = iterator.next();
		assertTrue(record.isParsed());
		item = record.getItem();
		assertNotNull(item);
		assertEquals(new Integer(2), item.getIndex());
		assertEquals("test2", item.getName());

		assertFalse(iterator.hasNext());
	}

}