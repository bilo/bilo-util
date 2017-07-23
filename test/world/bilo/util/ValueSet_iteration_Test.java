/*
 * Copyright 2017 Urs Fässler
 * SPDX-License-Identifier: Apache-2.0
 */

package world.bilo.util;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;

import world.bilo.util.functional.MapFunction;
import world.bilo.util.functional.ReduceFunction;

public class ValueSet_iteration_Test {

  @Test
  public void can_iterate_over_items() {
    Collection<Integer> iterated = new HashSet<>();
    Collection<Integer> provided = new HashSet<>();
    provided.add(1);
    provided.add(2);
    provided.add(3);
    ValueSet<Integer> testee = new ValueSet<Integer>(provided);

    for (Integer voxel : testee) {
      iterated.add(voxel);
    }

    assertEquals(provided, iterated);
  }

  @Test
  public void can_reduce_items() {
    Collection<Integer> provided = new HashSet<>();
    provided.add(1);
    provided.add(2);
    provided.add(3);
    ValueSet<Integer> testee = new ValueSet<Integer>(provided);
    ReduceFunction<Integer> function = new ReduceFunction<Integer>() {
      @Override
      public Integer execute(Integer previous, Integer current) {
        return previous * current;
      }
    };

    Integer result = testee.reduce(1, function);

    assertEquals(new Integer(6), result);
  }

  @Test
  public void can_map_items() {
    Collection<Integer> provided = new HashSet<>();
    provided.add(1);
    provided.add(2);
    provided.add(3);
    Collection<Boolean> expected = new HashSet<>();
    expected.add(false);
    expected.add(true);
    expected.add(false);
    ValueSet<Integer> testee = new ValueSet<Integer>(provided);
    MapFunction<Boolean, Integer> function = new MapFunction<Boolean, Integer>() {
      @Override
      public Boolean execute(Integer value) {
        return value % 2 == 0;
      }
    };

    ValueSet<Boolean> result = testee.map(function);

    assertEquals(new ValueSet<Boolean>(expected), result);
  }
}
