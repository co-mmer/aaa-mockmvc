package io.github.co_mmer.aaamockmvc.ej.test.web.act;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.exception.TestActException;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.TestAssert;

/**
 * This interface represents a contract for performing actions and obtaining results from HTTP
 * requests.
 *
 * <p>It provides methods to assert the results of an HTTP request and retrieve response data,
 * allowing for verification of the request's outcome.
 *
 * @since 1.0.0
 */
public interface TestAct2 {

  /**
   * Retrieves the {@link TestAssert} instance for asserting the response of the HTTP request.
   *
   * <p>This method allows for further validation of the result using various assertion methods,
   * enabling comprehensive checks on the HTTP response to ensure it meets expected criteria.
   *
   * @return a {@code TestAssert} instance for asserting the result of the request
   * @throws TestActException if an error occurs while performing the request
   * @since 1.0.0
   */
  TestAssert asserts() throws TestActException;

  /**
   * Retrieves the {@link TestAnswer} instance for the executed HTTP request.
   *
   * <p>This method provides access to the response content and other aspects of the request's
   * outcome, enabling further validation and examination of the HTTP response.
   *
   * @return a {@code TestAnswer} instance for accessing the result of the request
   * @since 1.2.0
   */
  TestAnswer answer();
}
