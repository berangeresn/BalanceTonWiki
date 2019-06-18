package com.ambre.wiki.helpers;

import java.util.ArrayList;
import java.util.Collection;

public class ConverterHelper {

	/**
	 * Convert an iterable to a collection
	 * @param <T>
	 * @param iterable
	 * @return
	 */
	public static <T> Collection<T> convertIterableToCollection(Iterable<T> iterable)
	{
		Collection<T> collection = new ArrayList<T>();
		for (T e : iterable) {
			collection.add(e);
		}
		return collection;
	}
	
}
