package com.github.cybortronik.parser.ssv;

import org.apache.commons.lang3.text.WordUtils;
import com.github.cybortronik.parser.ssv.exception.InvalidDefaultSsvHeaderException;
import com.github.cybortronik.parser.ssv.exception.InvalidSetPropertyIndexRequestException;

/**
 * Created by strifan on 02.06.2015.
 */
public class DefaultIndexedProperties implements IndexedProperties {
	public static final String INDEX_BELOW_0_EXCEPTION = "Invalid business request for index below 0";
	public static final String INDEX_HIGHER_THAN_AVAILABLE_EXCEPTION = "Requested index is higher than available";
	public static final String SPACE_TEXT = " ";
	public static final String SPECIAL_TEXT = ".";
	public static final String NO_TEXT = "";
	private String[] setPropertyNames;

	public DefaultIndexedProperties(String... header) {
		if (header == null || header.length == 0)
			throw new InvalidDefaultSsvHeaderException();

		initSetPropertyNames(header);
	}

	private void initSetPropertyNames(String... header) {
		this.setPropertyNames = new String[header.length];
		for (int i = 0; i < header.length; i++)
			this.setPropertyNames[i] = toSetPropertyName(header[i]);
	}

	private String toSetPropertyName(String head) {
		String capitalizedWords = WordUtils.capitalizeFully(head);
		String removedSpaces = capitalizedWords.replace(SPACE_TEXT, NO_TEXT);
		return "set" + removedSpaces.replace(SPECIAL_TEXT, NO_TEXT);
	}

	public String getSetterName(int index) {
		if (index < 0)
			throw new InvalidSetPropertyIndexRequestException(INDEX_BELOW_0_EXCEPTION);
		if (index >= setPropertyNames.length)
			throw new InvalidSetPropertyIndexRequestException(INDEX_HIGHER_THAN_AVAILABLE_EXCEPTION);

		return setPropertyNames[index];
	}
}

