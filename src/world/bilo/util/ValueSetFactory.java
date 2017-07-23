/*
 * Copyright 2017 Urs Fässler
 * SPDX-License-Identifier: Apache-2.0
 */

package world.bilo.util;

public interface ValueSetFactory<T> {
	public ValueSet<T> items();
}
