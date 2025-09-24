package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import java.util.Collection;
import lombok.NonNull;

interface OperationEquals<E> {

  /**
   * Asserts that the collection is equal to the given collection (same elements and order).
   *
   * <p>Note: For predictable text comparison, both actual and expected values are normalized using
   * Unicode Normalization Form C (NFC) where applicable.
   *
   * @param expectedCollection the expected collection; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the collections are not equal
   * @since 2.0.0
   */
  TestAssertLCollection isEqualTo(@NonNull Collection<E> expectedCollection);
}
