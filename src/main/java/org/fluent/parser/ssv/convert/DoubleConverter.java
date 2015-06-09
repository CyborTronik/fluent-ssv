package org.fluent.parser.ssv.convert;

/**
 * Created by strifan on 04.06.2015.
 */
public class DoubleConverter implements ValueConverter<Double> {
	public Class<Double> getConverterClass() {
		return Double.class;
	}

	public Double convert(String value) {
		return Double.parseDouble(value);
	}
}
