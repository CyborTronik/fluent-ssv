package org.fluent.parser.ssv.convert;

/**
 * Created by strifan on 04.06.2015.
 */
public class StringConverter implements ValueConverter<String> {
	public Class<String> getConverterClass() {
		return String.class;
	}

	public String convert(String value) {
		return value;
	}
}
