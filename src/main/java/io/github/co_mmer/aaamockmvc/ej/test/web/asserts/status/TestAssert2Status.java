package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestAssertContent;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.custom.TestAssertCustom;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;

/**
 * This interface defines a set of assertions for validating the status of HTTP responses in a
 * testing context. It allows various checks on the response status, including specific status code
 * assertions and range validations.
 *
 * @since 1.1.0
 */
public interface TestAssert2Status {

  /**
   * Asserts that the HTTP response is valid for content assertions.
   *
   * <p>This method returns an instance of {@code TestAssertContent} for asserting the content of
   * the HTTP response. It allows various validations of response content, including checks for
   * emptiness and matching expected values.
   *
   * @return an instance of {@code TestAssertContent} for further assertions
   * @since 1.1.0
   */
  TestAssertContent assertContent();

  /**
   * Asserts that the HTTP response is valid for a HEAD request.
   *
   * <p>This method returns an instance of {@code TestAssertHead} for asserting the headers of the
   * HTTP response. It allows various validations of response headers, such as checking for the
   * presence or absence of specific headers and comparing header values.
   *
   * @return an instance of {@code TestAssertHead} for further assertions on headers
   * @since 1.1.0
   */
  TestAssertHead assertHead();

  /**
   * Asserts that the HTTP response matches custom validation logic.
   *
   * <p>This method returns an instance of {@code TestAssertCustom} for asserting custom validations
   * on the HTTP response. It allows users to define their own result matchers or custom logic for
   * validating the response, giving flexibility beyond standard status, content, and header
   * assertions.
   *
   * @return an instance of {@code TestAssertCustom} for custom assertions on the response
   * @since 1.1.0
   */
  TestAssertCustom assertCustom();
}
