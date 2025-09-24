package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number;

interface OperationNegative {

  /**
   * Asserts that the number is strictly less than zero ({@code < 0}).
   *
   * <p>Note: {@code -0.0} is treated as zero and does <em>not</em> count as negative.
   *
   * @return the next step in the fluent assertion chain
   * @since 2.0.0
   */
  TestAssert3Number isNegative();
}
