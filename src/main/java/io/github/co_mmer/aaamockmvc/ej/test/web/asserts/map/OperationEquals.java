package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map;

import java.util.Map;
import lombok.NonNull;

interface OperationEquals<K, V> {

  /**
   * Asserts that the map is equal to the given map (same keys and values).
   *
   * @param expectedMap the expected map; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   * based on the current state.
   * @throws AssertionError if the maps are not equal
   * @since 2.0.0
   */
  TestAssertLMap isEqualTo(@NonNull Map<K, V> expectedMap);
}
