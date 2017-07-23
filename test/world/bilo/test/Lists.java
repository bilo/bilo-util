/*
 * Copyright 2017 Urs Fässler
 * SPDX-License-Identifier: Apache-2.0
 */

package world.bilo.test;

import java.util.ArrayList;
import java.util.List;

public class Lists {

  static public <T> List<T> list(Class<T> kind) {
    List<T> result = new ArrayList<>();
    return result;
  }

  static public <T> List<T> list(T a0) {
    List<T> result = new ArrayList<>();
    result.add(a0);
    return result;
  }

  static public <T> List<T> list(T a0, T a1) {
    List<T> result = list(a0);
    result.add(a1);
    return result;
  }

  static public <T> List<T> list(T a0, T a1, T a2) {
    List<T> result = list(a0, a1);
    result.add(a2);
    return result;
  }

  static public <T> List<T> list(T a0, T a1, T a2, T a3) {
    List<T> result = list(a0, a1, a2);
    result.add(a3);
    return result;
  }

  static public <T> List<T> list(T a0, T a1, T a2, T a3, T a4, T a5, T a6, T a7) {
    List<T> result = list(a0, a1, a2, a3);
    result.add(a4);
    result.add(a5);
    result.add(a6);
    result.add(a7);
    return result;
  }

}
