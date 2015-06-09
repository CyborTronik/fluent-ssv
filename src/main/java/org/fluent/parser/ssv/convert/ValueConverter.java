package org.fluent.parser.ssv.convert;

/**
 * Created by strifan on 04.06.2015.
 */
public interface ValueConverter<T> {
	Class<T> getConverterClass();

	T convert(String value);
}
