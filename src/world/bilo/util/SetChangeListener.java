/*
 * Copyright 2017 Urs Fässler
 * SPDX-License-Identifier: Apache-2.0
 */

package world.bilo.util;

public interface SetChangeListener<T> {

	public void added(ValueSet<T> added);

	public void removed(ValueSet<T> removed);

	public void changed(ValueSet<T> all);

}
