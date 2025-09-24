package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number;

interface OperationPositive {

  /**
   * Asserts that the number is strictly greater than zero ({@code > 0}).
   *
   * <p>Examples: {@code 1}, {@code 1.0} pass; {@code 0}, {@code 0.0}, {@code -0.0}, {@code -1}
   * fail.
   *
   * @return the next step in the fluent assertion chain
   * @since 2.0.0
   */
  TestAssert3Number isPositive();
}
