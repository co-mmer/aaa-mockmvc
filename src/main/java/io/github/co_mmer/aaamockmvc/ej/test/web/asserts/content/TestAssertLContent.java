package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;

/**
 * Provides assertion methods for validating HTTP response headers.
 *
 * <p>This interface offers a set of methods to assert specific properties of an HTTP response,
 * including the validation of response headers.
 *
 * @since 1.0.0
 */
public interface TestAssertLContent {

  /**
   * Provides assertion methods for validating the HTTP response headers.
   *
   * <p>This method returns an instance of {@code TestAssertHead}, which provides assertion methods
   * for validating the headers of the HTTP response, such as checking for the presence or absence
   * of specific headers and comparing header values.
   *
   * @return an instance of {@code TestAssertHead} for asserting the response headers
   * @since 1.0.0
   */
  TestAssertHead assertHead();
}
