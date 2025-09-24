package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz;

import lombok.NonNull;

interface OperationEquals<T> {

  /**
   * Asserts that the deserialized value is equal to the given {@code expected} value.
   *
   * <p>Note: For predictable text comparison, both actual and expected values are normalized using
   * Unicode Normalization Form C (NFC) before comparison where applicable.
   *
   * @param expectedResponse the expected value; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   * based on the current state.
   * @throws AssertionError if the values are not equal
   * @since 2.0.0
   */
  TestAssertLClass isEqualTo(@NonNull T expectedResponse);
}
