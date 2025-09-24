package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number;

interface OperationParity {

  /**
   * Asserts that the actual number is <em>even</em>.
   *
   * <p>Requires an integer value (no fractional part). Non-integer values cause the assertion to
   * fail. Examples: {@code 2}, {@code 2.0} pass; {@code 3}, {@code 2.5} fail.
   *
   * @return the next step in the fluent assertion chain
   * @since 2.0.0
   */
  TestAssert7Number isEven();

  /**
   * Asserts that the actual number is <em>odd</em>.
   *
   * <p>Requires an integer value (no fractional part). Non-integer values cause the assertion to
   * fail. Examples: {@code 3} passes; {@code 2}, {@code 3.1} fail.
   *
   * @return the next step in the fluent assertion chain
   * @since 2.0.0
   */
  TestAssert7Number isOdd();
}
