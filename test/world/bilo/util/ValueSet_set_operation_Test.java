/*
 * Copyright 2017 Urs Fässler
 * SPDX-License-Identifier: Apache-2.0
 */

package world.bilo.util;

import static org.junit.Assert.assertEquals;
import static world.bilo.test.Lists.list;

import java.util.List;

import org.junit.Test;

public class ValueSet_set_operation_Test {

  @Test
  public void can_unite_with_other_set() {
    ValueSet<Integer> voxelsA = voxels(list(new Integer(0)));
    ValueSet<Integer> voxelsB = voxels(list(new Integer(1)));

    ValueSet<Integer> result = voxelsA.union(voxelsB);

    assertEquals(voxels(list(new Integer(0), new Integer(1))), result);
  }

  @Test
  public void uniteing_does_not_change_the_original_objects() {
    ValueSet<Integer> voxelsA = voxels(list(new Integer(0)));
    ValueSet<Integer> voxelsB = voxels(list(new Integer(1)));

    voxelsA.union(voxelsB);

    assertEquals(voxels(list(new Integer(0))), voxelsA);
    assertEquals(voxels(list(new Integer(1))), voxelsB);
  }

  @Test
  public void can_get_intersection_with_other_set() {
    ValueSet<Integer> voxelsA = voxels(list(new Integer(0), new Integer(1)));
    ValueSet<Integer> voxelsB = voxels(list(new Integer(1), new Integer(2)));

    ValueSet<Integer> result = voxelsA.intersection(voxelsB);

    assertEquals(voxels(list(new Integer(1))), result);
  }

  @Test
  public void intersection_does_not_change_the_original_objects() {
    ValueSet<Integer> voxelsA = voxels(list(new Integer(0), new Integer(1)));
    ValueSet<Integer> voxelsB = voxels(list(new Integer(1), new Integer(2)));

    voxelsA.intersection(voxelsB);

    assertEquals(voxels(list(new Integer(0), new Integer(1))), voxelsA);
    assertEquals(voxels(list(new Integer(1), new Integer(2))), voxelsB);
  }

  @Test
  public void subtract_other_set_does_nothing_for_empty_intersection_set() {
    ValueSet<Integer> voxelsA = voxels(list(new Integer(0)));
    ValueSet<Integer> voxelsB = voxels(list(new Integer(1)));

    ValueSet<Integer> result = voxelsA.minus(voxelsB);

    assertEquals(voxels(list(new Integer(0))), result);
  }

  @Test
  public void subtract_other_set_removes_items_that_are_in_the_minuend() {
    ValueSet<Integer> voxelsA = voxels(list(new Integer(0)));
    ValueSet<Integer> voxelsB = voxels(list(new Integer(0)));

    ValueSet<Integer> result = voxelsA.minus(voxelsB);

    assertEquals(voxels(list(Integer.class)), result);
  }

  @Test
  public void subtract_does_not_change_the_original_objects() {
    ValueSet<Integer> voxelsA = voxels(list(new Integer(0), new Integer(1)));
    ValueSet<Integer> voxelsB = voxels(list(new Integer(0), new Integer(2)));

    voxelsA.minus(voxelsB);

    assertEquals(voxels(list(new Integer(0), new Integer(1))), voxelsA);
    assertEquals(voxels(list(new Integer(0), new Integer(2))), voxelsB);
  }

  private ValueSet<Integer> voxels(List<Integer> voxels) {
    return new ValueSet<Integer>(voxels);
  }
}
