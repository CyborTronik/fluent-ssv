package org.fluent.parser.ssv.convert;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by strifan on 04.06.2015.
 */
public class IntegerConverter implements ValueConverter<Integer> {
	public Class<Integer> getConverterClass() {
		return Integer.class;
	}

	public Integer convert(String value) {
		if (StringUtils.isBlank(value))
			return null;
		return Integer.parseInt(value);
	}
}
