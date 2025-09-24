package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number;

import lombok.NonNull;

interface OperationEquals {

  /**
   * Asserts that the actual number is <em>not</em> numerically equal to {@code expected}.
   *
   * <p>Comparison is value-based (e.g., {@code 1} equals {@code 1.0}).
   *
   * @param expected the value it must not equal; must not be {@code null}
   * @return the next step in the fluent assertion chain
   * @since 2.0.0
   */
  TestAssertLNumber isNotEqualTo(@NonNull Number expected);

  /**
   * Asserts that the actual number is numerically equal to {@code expected}.
   *
   * <p>Comparison is value-based (e.g., {@code 1} equals {@code 1.0}).
   *
   * @param expected the expected value; must not be {@code null}
   * @return the next step in the fluent assertion chain
   * @since 2.0.0
   */
  TestAssertLNumber isEqualTo(@NonNull Number expected);
}
