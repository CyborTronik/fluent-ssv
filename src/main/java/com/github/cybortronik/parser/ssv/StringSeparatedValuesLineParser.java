package com.github.cybortronik.parser.ssv;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import com.github.cybortronik.parser.ssv.exception.SsvException;

/**
 * Created by strifan on 02.06.2015.
 */
public class StringSeparatedValuesLineParser implements LineParser {

	public static final String EMPTY_SEPARATOR_EXCEPTION = "Cannot init a values separator parser for null or empty separated values";
	public static final String EMPTY_ELEMENT = "";
	private String valuesSeparator = ",";

	public StringSeparatedValuesLineParser() {
	}

	public StringSeparatedValuesLineParser(String valuesSeparator) {
		if (StringUtils.isEmpty(valuesSeparator))
			throw new SsvException(EMPTY_SEPARATOR_EXCEPTION);
		this.valuesSeparator = valuesSeparator;
	}

	public String[] parse(String line) {
		String[] values = line.split(valuesSeparator);
		if (line.endsWith(valuesSeparator))
			values = ArrayUtils.add(values, EMPTY_ELEMENT);
		return values;
	}
}
