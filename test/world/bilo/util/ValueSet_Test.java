/*
 * Copyright 2017 Urs Fässler
 * SPDX-License-Identifier: Apache-2.0
 */

package world.bilo.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static world.bilo.test.Lists.list;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

public class ValueSet_Test {
  private final Integer item = 42;
  private final Collection<Integer> items = new ArrayList<>();

  @Test
  public void create_with_given_items() {
    items.add(item);

    ValueSet<Integer> testee = new ValueSet<Integer>(items);

    assertEquals(1, testee.size());
    assertTrue(testee.contains(item));
  }

  @Test
  public void can_not_change_object_when_createing_with_given_items() {
    items.add(item);
    ValueSet<Integer> testee = new ValueSet<Integer>(items);

    items.clear();

    assertEquals(1, testee.size());
    assertTrue(testee.contains(item));
  }

  @Test
  public void duplicated_items_are_removed() {
    Integer voxel1 = new Integer(1);
    Integer voxel2 = new Integer(1);
    items.add(voxel1);
    items.add(voxel2);

    ValueSet<Integer> testee = new ValueSet<Integer>(items);

    assertEquals(1, testee.size());
    assertTrue(testee.contains(voxel1));
    assertTrue(testee.contains(voxel2));
  }

  @Test
  public void has_meaningful_string_output() {
    Integer voxel1 = new Integer(1);
    Integer voxel2 = new Integer(2);
    items.add(voxel1);
    items.add(voxel2);

    ValueSet<Integer> testee = new ValueSet<Integer>(items);

    assertEquals("{1, 2}", testee.toString());
  }

  @Test
  public void can_check_for_emptyness() {
    ValueSet<Integer> noVoxel = new ValueSet<Integer>(list(Integer.class));
    ValueSet<Integer> oneVoxel = new ValueSet<Integer>(list(new Integer(0)));

    assertTrue(noVoxel.empty());
    assertFalse(oneVoxel.empty());
  }

  @Test
  public void are_equal_by_value() {
    ValueSet<Integer> voxelsA = new ValueSet<Integer>(list(new Integer(1)));
    ValueSet<Integer> voxelsB = new ValueSet<Integer>(list(new Integer(1)));
    ValueSet<Integer> voxelsC = new ValueSet<Integer>(list(new Integer(2)));

    assertTrue(voxelsA.equals(voxelsB));
    assertFalse(voxelsA.equals(voxelsC));
    assertEquals(voxelsA.hashCode(), voxelsB.hashCode());
  }

}
