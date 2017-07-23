/*
 * Copyright 2017 Urs Fässler
 * SPDX-License-Identifier: Apache-2.0
 */

package world.bilo.util.functional;

public interface BinaryFunction<Result, Left, Right> {
	public Result execute(Left left, Right right);
}
