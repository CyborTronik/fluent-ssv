package org.fluent.parser.csv.convert;

/**
 * Created by strifan on 04.06.2015.
 */
public class DefaultConverter implements ValueConverter {
	public Class getConverterClass() {
		return Object.class;
	}

	public Object convert(String value) {
		return value;
	}
}
