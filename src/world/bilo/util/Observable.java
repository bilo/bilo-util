/*
 * Copyright 2017 Urs Fässler
 * SPDX-License-Identifier: Apache-2.0
 */

package world.bilo.util;

public interface Observable<T> {

	public void addListener(T listener);

	public void removeListener(T listener);

}
