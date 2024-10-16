package ej.aaamockmvc.test.web.asserts;

import ej.aaamockmvc.test.web.asserts.content.TestAssertContent;
import ej.aaamockmvc.test.web.asserts.head.TestAssertHead;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultMatcher;

/**
 * This interface defines a contract for performing various assertions on HTTP response results.
 *
 * @since 1.0.0
 */
public interface TestAssert {

  /**
   * Asserts that the HTTP response status matches the given {@code HttpStatus}.
   *
   * @param status the expected {@code HttpStatus} of the response (must not be {@code null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  TestAssert assertStatus(@NonNull HttpStatus status);

  /**
   * Asserts that the HTTP response status matches the given status code.
   *
   * @param status the expected status code of the response
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  TestAssert assertStatus(int status);

  /**
   * Asserts that the HTTP response matches the given {@link ResultMatcher}.
   *
   * @param matcher the {@code ResultMatcher} to be used for validation (must not be {@code null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws NullPointerException if the {@code matcher} is {@code null}
   * @since 1.0.0
   */
  TestAssert assertByResultMatcher(@NonNull ResultMatcher matcher);

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
}
