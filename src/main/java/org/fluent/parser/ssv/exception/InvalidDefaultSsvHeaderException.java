package org.fluent.parser.ssv.exception;

/**
 * Created by strifan on 03.06.2015.
 */
public class InvalidDefaultSsvHeaderException extends SsvException {
	public InvalidDefaultSsvHeaderException() {
		super("Be default CSV header must have at least one column name");
	}
}
