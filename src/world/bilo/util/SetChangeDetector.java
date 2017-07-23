/*
 * Copyright 2017 Urs Fässler
 * SPDX-License-Identifier: Apache-2.0
 */

package world.bilo.util;

import world.bilo.util.functional.Function;

public class SetChangeDetector<T> implements ValueSetFactory<T>, Observable<SetChangeListener<T>> {
	final private UniqueOrderedList<SetChangeListener<T>> listeners = new UniqueOrderedList<>();
	private ValueSet<T> oldItems;

	public SetChangeDetector(ValueSet<T> items) {
		oldItems = items;
	}

	@Override
	public void removeListener(SetChangeListener<T> listener) {
		listeners.remove(listener);
	}

	@Override
	public void addListener(SetChangeListener<T> listener) {
		listeners.add(listener);
	}

	public void changed(ValueSet<T> newItems) {
		ValueSet<T> removed = oldItems.minus(newItems);
		ValueSet<T> added = newItems.minus(oldItems);

		oldItems = newItems;

		notifyIf(!removed.empty(), removedFunction(removed));
		notifyIf(!added.empty(), addedFunction(added));
		notifyIf(!added.empty() || !removed.empty(), changedFunction(oldItems));
	}

	private void notifyIf(boolean predicate, Function<SetChangeListener<T>> function) {
		if (predicate) {
			listeners.apply(function);
		}
	}

	private Function<SetChangeListener<T>> addedFunction(final ValueSet<T> added) {
		return new Function<SetChangeListener<T>>() {
			@Override
			public void execute(SetChangeListener<T> item) {
				item.added(added);
			}
		};
	}

	private Function<SetChangeListener<T>> removedFunction(final ValueSet<T> removed) {
		return new Function<SetChangeListener<T>>() {
			@Override
			public void execute(SetChangeListener<T> item) {
				item.removed(removed);
			}
		};
	}

	private Function<SetChangeListener<T>> changedFunction(final ValueSet<T> all) {
		return new Function<SetChangeListener<T>>() {
			@Override
			public void execute(SetChangeListener<T> item) {
				item.changed(all);
			}
		};
	}

	@Override
	public ValueSet<T> items() {
		return oldItems;
	}
}
