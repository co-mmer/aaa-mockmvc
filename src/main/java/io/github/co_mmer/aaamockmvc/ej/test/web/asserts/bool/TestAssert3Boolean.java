package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;

/**
 * Entry point for raw <b>Boolean</b> content assertions.
 *
 * @since 2.0.0
 */
public interface TestAssert3Boolean {

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

  /**
   * Switches to HTTP header assertions for the same response snapshot.
   *
   * <p>Use this to continue the assertion chain on headers (e.g. {@code containsKey}, {@code
   * containsEntry}). No additional I/O is performed; the headers captured during {@code
   * actPerform().perform()} are reused.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @since 2.0.0
   */
  TestAssertHead headers();
}
