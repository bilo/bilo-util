/*
 * Copyright 2017 Urs Fässler
 * SPDX-License-Identifier: Apache-2.0
 */

package world.bilo.util;

import static world.bilo.test.Lists.list;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SetChangeDetector_Test {
  private final ValueSet<Integer> originalVoxel = new ValueSet<Integer>(list(new Integer(1)));
  final private SetChangeDetector<Integer> testee = new SetChangeDetector<Integer>(originalVoxel);
  final private SetChangeListener<Integer> listener = mock(SetChangeListener.class);
  private final ValueSet<Integer> noVoxel = new ValueSet<Integer>(new ArrayList<Integer>());
  private final ValueSet<Integer> twoVoxel = new ValueSet<Integer>(list(new Integer(1), new Integer(2)));
  private final ValueSet<Integer> addedVoxel = new ValueSet<Integer>(list(new Integer(2)));
  private final ValueSet<Integer> removedVoxel = new ValueSet<Integer>(list(new Integer(1)));

  @Before
  public void setUp() {
    testee.addListener(listener);
  }

  @Test
  public void notifies_listener_when_voxels_are_added() {
    testee.changed(twoVoxel);

    verify(listener).added(addedVoxel);
    verify(listener).changed(twoVoxel);
  }

  @Test
  public void does_not_notify_if_nothing_was_added() {
    testee.changed(originalVoxel);

    verify(listener, never()).added(any(ValueSet.class));
    verify(listener, never()).changed(originalVoxel);
  }

  @Test
  public void notifies_listener_when_voxels_are_removed() {
    testee.changed(noVoxel);

    verify(listener).removed(removedVoxel);
    verify(listener).changed(noVoxel);
  }

  @Test
  public void does_not_notify_if_nothing_was_removed() {
    testee.changed(originalVoxel);

    verify(listener, never()).removed(any(ValueSet.class));
    verify(listener, never()).changed(originalVoxel);
  }

  @Test
  public void keeps_new_voxels_for_next_change() {
    testee.changed(twoVoxel);

    testee.changed(twoVoxel);

    verify(listener, times(1)).added(addedVoxel);
  }

}
