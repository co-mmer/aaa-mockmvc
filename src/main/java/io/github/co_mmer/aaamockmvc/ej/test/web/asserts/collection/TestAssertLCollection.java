package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;

/**
 * Provides assertion methods for validating HTTP response collections.
 *
 * <ul>
 *   <li>{@link #assertHead()}: Asserting properties of response headers for HTTP HEAD requests.
 * </ul>
 *
 * @since 1.4.0
 */
public interface TestAssertLCollection {

  /**
   * Asserts that the HTTP response is valid for a HEAD request.
   *
   * <p>This method returns an instance of {@code TestAssertHead} for asserting the headers of the
   * HTTP response. It allows various validations of response headers, such as checking for the
   * presence or absence of specific headers and comparing header values.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.4.0
   */
  TestAssertHead assertHead();
}
