/*
 * Copyright 2017 Urs Fässler
 * SPDX-License-Identifier: Apache-2.0
 */

package world.bilo.util.functional;

public interface UnaryFunction<Result, Argument> {
  public Result execute(Argument value);
}
