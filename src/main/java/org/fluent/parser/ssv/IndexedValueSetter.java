package org.fluent.parser.ssv;

import org.fluent.parser.ssv.convert.TypeConverterMap;
import org.fluent.parser.ssv.convert.ValueConverter;
import org.fluent.parser.ssv.exception.SsvException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by strifan on 04.06.2015.
 */
public class IndexedValueSetter {
	private IndexedProperties indexedProperties;
	private TypeConverterMap converterMap;

	public IndexedValueSetter(IndexedProperties indexedProperties, TypeConverterMap converterMap) {
		this.indexedProperties = indexedProperties;
		this.converterMap = converterMap;
	}

	public void set(int index, String value, Object item) {
		try {
			setValueByIndex(index, value, item);
		} catch (Exception ex) {
			throw new SsvException(ex.getMessage() + " at index " + index + " for value " + value, ex);
		}
	}

	private void setValueByIndex(int index, String value, Object item) throws IllegalAccessException, InvocationTargetException {
		String setterName = indexedProperties.getSetterName(index);
		Method method = findMonadicMethodByName(item, setterName);
		Class<?> argumentClass = method.getParameterTypes()[0];
		String trimmedValue = value.trim();
		Object convertedValue = convertValue(trimmedValue, argumentClass);
		method.invoke(item, convertedValue);
	}

	private Object convertValue(String value, Class<?> argumentClass) {
		ValueConverter valueConverter = converterMap.get(argumentClass);
		return valueConverter.convert(value);
	}

	private Method findMonadicMethodByName(Object item, String setterName) {
		Class<?> itemClass = item.getClass();
		for (Method method : itemClass.getMethods())
			if (method.getName().equalsIgnoreCase(setterName) && method.getParameterTypes().length == 1)
				return method;
		throw new SsvException(itemClass + " doesn't contain a monadic(single argument) method called " + setterName);
	}
}
