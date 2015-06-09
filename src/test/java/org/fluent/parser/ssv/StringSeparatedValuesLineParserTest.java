package org.fluent.parser.ssv;

import org.fluent.parser.ssv.exception.SsvException;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by strifan on 04.06.2015.
 */
public class StringSeparatedValuesLineParserTest {

	@Test(expected = SsvException.class)
	public void initWithNullSeparator() {
		new StringSeparatedValuesLineParser(null);
	}

	@Test(expected = SsvException.class)
	public void initWithEmptySeparator() {
		new StringSeparatedValuesLineParser("");
	}

	@Test
	public void splitTwoValues() {
		StringSeparatedValuesLineParser lineParser = new StringSeparatedValuesLineParser();
		String[] parsedValues = lineParser.parse("test1,test2");
		assertArrayEquals(new String[] { "test1", "test2" }, parsedValues);
	}

	@Test
	public void splitWithFirstEmpty() {
		StringSeparatedValuesLineParser lineParser = new StringSeparatedValuesLineParser();
		String[] parsedValues = lineParser.parse(",test2");
		assertArrayEquals(new String[] { "", "test2" }, parsedValues);
	}

	@Test
	public void splitWithLastEmpty() {
		StringSeparatedValuesLineParser lineParser = new StringSeparatedValuesLineParser();
		String[] parsedValues = lineParser.parse("test2,");
		assertArrayEquals(new String[] { "test2", "" }, parsedValues);
	}
}