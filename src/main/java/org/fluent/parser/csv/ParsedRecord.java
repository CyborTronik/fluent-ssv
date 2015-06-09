package org.fluent.parser.csv;

/**
 * Created by strifan on 05.06.2015.
 */
public class ParsedRecord<T> {
	private T item;
	private boolean parsed;
	private Throwable exception;

	public ParsedRecord(T item) {
		this.item = item;
		parsed = true;
	}

	public ParsedRecord(Exception ex) {
		this.exception = ex;
		parsed = false;
	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}

	public boolean isParsed() {
		return parsed;
	}

	public void setParsed(boolean parsed) {
		this.parsed = parsed;
	}

	public Throwable getException() {
		return exception;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}
}
