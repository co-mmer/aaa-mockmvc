package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number;

interface OperationNonPositive {

  /**
   * Asserts that the number is less than or equal to zero ({@code <= 0}).
   *
   * <p>Zero (including {@code +0.0} and {@code -0.0}) and negatives pass; positives fail.
   *
   * @return the next step in the fluent assertion chain
   * @since 2.0.0
   */
  TestAssert4Number isNonPositive();
}
