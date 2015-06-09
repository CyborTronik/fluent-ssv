package org.fluent.parser.ssv;

import org.fluent.parser.ssv.convert.TypeConverterMap;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by strifan on 04.06.2015.
 */
public class IndexedValueSetterTest {

	@Test
	public void setValueByIndex() {
		IndexedProperties headerProperties = new DefaultIndexedProperties("name");
		Model model = new Model();
		assertNull(model.getName());

		IndexedValueSetter valueSetter = new IndexedValueSetter(headerProperties, new TypeConverterMap());

		valueSetter.set(0, "a value", model);
		assertEquals("a value", model.getName());
	}

	class Model {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}