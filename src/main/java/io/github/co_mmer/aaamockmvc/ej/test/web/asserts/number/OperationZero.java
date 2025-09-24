package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number;

interface OperationZero {

  /**
   * Asserts that the number is numerically zero.
   *
   * <p>Both {@code +0.0} and {@code -0.0} are considered zero.
   *
   * @return the next step in the fluent assertion chain
   * @since 2.0.0
   */
  TestAssertLNumber isZero();
}
