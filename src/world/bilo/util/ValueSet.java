/*
 * Copyright 2017 Urs Fässler
 * SPDX-License-Identifier: Apache-2.0
 */

package world.bilo.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import world.bilo.util.functional.MapFunction;
import world.bilo.util.functional.ReduceFunction;

public class ValueSet<T> implements Iterable<T> {
	final private Set<T> items = new HashSet<>();

	public ValueSet() {
	}

	public ValueSet(Collection<T> items) {
		this.items.addAll(items);
	}

	public int size() {
		return items.size();
	}

	public boolean empty() {
		return items.isEmpty();
	}

	public boolean contains(T vector) {
		return items.contains(vector);
	}

	public ValueSet<T> union(ValueSet<T> other) {
		Set<T> result = new HashSet<>();
		result.addAll(this.items);
		result.addAll(other.items);
		return new ValueSet<T>(result);
	}

	public ValueSet<T> intersection(ValueSet<T> other) {
		Set<T> result = new HashSet<>();
		result.addAll(this.items);
		result.retainAll(other.items);
		return new ValueSet<T>(result);
	}

	public ValueSet<T> minus(ValueSet<T> other) {
		Set<T> items = new HashSet<>(this.items);
		items.removeAll(other.items);
		return new ValueSet<T>(items);
	}

	public <R> ValueSet<R> map(MapFunction<R, T> function) {
		Collection<R> result = new ArrayList<>();
		for (T item : items) {
			result.add(function.execute(item));
		}
		return new ValueSet<>(result);
	}

	public T reduce(T start, ReduceFunction<T> function) {
		for (T item : items) {
			start = function.execute(start, item);
		}
		return start;
	}

	@Override
	public Iterator<T> iterator() {
		return items.iterator();
	}

	@Override
	public String toString() {
		String result = "{";
		boolean first = true;
		for (T item : items) {
			if (first) {
				first = false;
			} else {
				result += ", ";
			}
			result += item;
		}
		result += "}";
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValueSet<T> other = (ValueSet<T>) obj;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}

}
