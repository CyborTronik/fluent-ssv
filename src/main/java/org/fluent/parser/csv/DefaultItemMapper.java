package org.fluent.parser.csv;

/**
 * Created by strifan on 04.06.2015.
 */
public class DefaultItemMapper<T> implements ItemMapper<T> {

	private IndexedValueSetter valueSetter;
	private ItemFactory<T> itemFactory;
	private LineParser lineParser;

	public DefaultItemMapper(IndexedValueSetter valueSetter, ItemFactory<T> itemFactory, LineParser lineParser) {
		this.valueSetter = valueSetter;
		this.itemFactory = itemFactory;
		this.lineParser = lineParser;
	}

	public T map(String line) {
		T item = itemFactory.create();
		String[] values = lineParser.parse(line);
		setValues(values, item);
		return item;
	}

	private void setValues(String[] values, T item) {
		for (int i = 0; i < values.length; i++)
			valueSetter.set(i, values[i], item);
	}
}
