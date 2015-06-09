package org.fluent.parser.ssv;

/**
 * Created by strifan on 04.06.2015.
 */
public interface ItemMapper<T> {

	T map(String line);
}
