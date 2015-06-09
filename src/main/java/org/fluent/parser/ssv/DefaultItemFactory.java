package org.fluent.parser.ssv;

import org.fluent.parser.ssv.exception.SsvException;

/**
 * Created by strifan on 04.06.2015.
 */
public class DefaultItemFactory<T> implements ItemFactory<T> {

	private Class<T> typeClass;

	public DefaultItemFactory(Class<T> typeClass) {
		this.typeClass = typeClass;
		create();
	}

	public T create() {
		try {
			return typeClass.newInstance();
		} catch (Exception e) {
			throw new SsvException("Cannot make a new instance with default constructor for class " + typeClass, e);
		}
	}
}
