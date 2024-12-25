package io.github.co_mmer.aaamockmvc.ej.test.web.asserts;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert1Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestAssertContent;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.custom.TestAssertCustom;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status.TestAssert1Status;

/**
 * This interface defines a contract for performing various assertions on HTTP response results.
 *
 * @since 1.0.0
 */
public interface TestAssert {

  TestAssert1Status assertStatus();

  /**
   * Asserts that the HTTP response is valid for content assertions.
   *
   * <p>This method returns an instance of {@code TestAssertContent} for asserting the content of
   * the HTTP response. It allows various validations of response content, including checks for
   * emptiness and matching expected values.
   *
   * @return an instance of {@code TestAssertContent} for further assertions
   * @since 1.0.0
   */
  TestAssertContent assertContent();

  TestAssert1Collection assertCollection();

  /**
   * Asserts that the HTTP response is valid for a HEAD request.
   *
   * <p>This method returns an instance of {@code TestAssertHead} for asserting the headers of the
   * HTTP response. It allows various validations of response headers, such as checking for the
   * presence or absence of specific headers and comparing header values.
   *
   * @return an instance of {@code TestAssertHead} for further assertions on headers
   * @since 1.0.0
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
