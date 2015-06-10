package com.github.cybortronik.parser.ssv.exception;

/**
 * Created by strifan on 03.06.2015.
 */
public class SsvException extends RuntimeException {

	public SsvException(String message) {
		super(message);
	}

	public SsvException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
