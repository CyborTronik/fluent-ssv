package org.fluent.parser.csv.exception;

/**
 * Created by strifan on 03.06.2015.
 */
public class CsvException extends RuntimeException {

	public CsvException(String message) {
		super(message);
	}

	public CsvException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
