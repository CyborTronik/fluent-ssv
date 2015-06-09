package org.fluent.parser.csv.exception;

/**
 * Created by strifan on 03.06.2015.
 */
public class InvalidDefaultCsvHeaderException extends CsvException {
	public InvalidDefaultCsvHeaderException() {
		super("Be default CSV header must have at least one column name");
	}
}
