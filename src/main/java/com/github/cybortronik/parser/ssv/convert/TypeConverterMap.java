package com.github.cybortronik.parser.ssv.convert;

import com.github.cybortronik.parser.ssv.exception.SsvException;

import java.util.HashMap;

/**
 * Created by strifan on 04.06.2015.
 */
public class TypeConverterMap extends HashMap<Class, ValueConverter> {

	public TypeConverterMap() {
		add(new DefaultConverter());
		add(new DoubleConverter());
		add(new IntegerConverter());
		add(new StringConverter());
		add(new BooleanConverter());
	}

	public void add(ValueConverter valueConverter) {
		if (valueConverter == null)
			throw new SsvException("Impossible to convert a value without or with 'null' converter.");
		put(valueConverter.getConverterClass(), valueConverter);
	}

	@Override
	public ValueConverter get(Object key) {
		if (containsKey(key))
			return super.get(key);

		throw new SsvException("Converter doesn't exists for " + key);
	}
}
