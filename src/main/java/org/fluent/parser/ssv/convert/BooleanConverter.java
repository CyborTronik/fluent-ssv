package org.fluent.parser.ssv.convert;

/**
 * Created by strifan on 09.06.2015.
 */
public class BooleanConverter implements ValueConverter<Boolean> {
	@Override
	public Class<Boolean> getConverterClass() {
		return Boolean.class;
	}

	@Override
	public Boolean convert(String value) {
		return "TRUE".equalsIgnoreCase(value);
	}
}
