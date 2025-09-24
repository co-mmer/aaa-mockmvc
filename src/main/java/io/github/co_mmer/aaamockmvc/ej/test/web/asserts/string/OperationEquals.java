package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string;

import lombok.NonNull;

interface OperationEquals {

  /**
   * Asserts that the body string is equal to the given {@code expected} string.
   *
   * <p>Note: For predictable text comparison, both actual and expected values are normalized using
   * Unicode Normalization Form C (NFC) where applicable.
   *
   * @param expectedString the expected string; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   * based on the current state.
   * @throws AssertionError if the strings are not equal
   * @since 2.0.0
   */
  TestAssertLString isEqualTo(@NonNull String expectedString);
}
