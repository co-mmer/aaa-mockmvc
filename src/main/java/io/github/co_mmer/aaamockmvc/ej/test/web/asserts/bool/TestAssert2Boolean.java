package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool;

/**
 * Entry point for raw <b>Boolean</b> content assertions.
 *
 * @since 2.0.0
 */
public interface TestAssert2Boolean {

  /**
   * Asserts that the actual value is {@code Boolean.TRUE}.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if the value is not {@code true}
   * @since 2.0.0
   */
  TestAssert4Boolean isTrue();

  /**
   * Asserts that the actual value is {@code Boolean.FALSE}.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if the value is not {@code false}
   * @since 2.0.0
   */
  TestAssert4Boolean isFalse();
}
