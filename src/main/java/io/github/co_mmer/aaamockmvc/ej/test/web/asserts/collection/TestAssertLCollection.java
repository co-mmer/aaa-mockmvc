package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;

/**
 * Provides methods for validating HTTP HEAD responses and their headers.
 *
 * <p>This interface enables seamless assertion of header properties, ensuring compliance with
 * expected HTTP response behavior.
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
   * @return an instance of {@code TestAssertHead} for further assertions on headers
   * @since 1.4.0
   */
  TestAssertHead assertHead();

  /**
   * Retrieves the {@link TestAnswer} instance for the executed HTTP request.
   *
   * <p>This method provides access to the response content and other aspects of the request's
   * outcome, enabling further validation and examination of the HTTP response.
   *
   * @return a {@code TestAnswer} instance for accessing the result of the request
   * @since 1.4.0
   */
  TestAnswer answer();
}
