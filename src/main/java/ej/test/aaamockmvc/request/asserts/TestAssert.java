package ej.test.aaamockmvc.request.asserts;

import ej.test.aaamockmvc.request.act.exception.TestAssertException;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultMatcher;

/**
 * This interface defines a contract for performing various assertions on HTTP response results.
 *
 * <p>The {@code TestAssert} interface provides methods to validate the status and content of HTTP
 * responses, including assertions for status codes, string content, and byte array content.
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
   * Asserts that the string content of the HTTP response is not empty.
   *
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws TestAssertException if an error occurs when retrieving the content
   * @since 1.0.0
   */
  TestAssert assertStringContentIsNotEmpty() throws TestAssertException;

  /**
   * Asserts that the string content of the HTTP response is empty.
   *
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws TestAssertException if an error occurs when retrieving the content
   * @since 1.0.0
   */
  TestAssert assertStringContentIsEmpty() throws TestAssertException;

  /**
   * Asserts that the string content of the HTTP response matches the expected string.
   *
   * @param expectedString the expected content of the response (must not be {@code null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws TestAssertException if an error occurs when retrieving the content
   * @throws NullPointerException if the {@code expectedString} is {@code null}
   * @since 1.0.0
   */
  TestAssert assertStringContent(@NonNull String expectedString) throws TestAssertException;

  /**
   * Asserts that the byte array content of the HTTP response is not empty.
   *
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws TestAssertException if an error occurs when retrieving the content
   * @since 1.0.0
   */
  TestAssert assertByteContentIsNotEmpty() throws TestAssertException;

  /**
   * Asserts that the byte array content of the HTTP response is empty.
   *
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws TestAssertException if an error occurs when retrieving the content
   * @since 1.0.0
   */
  TestAssert assertByteContentIsEmpty() throws TestAssertException;

  /**
   * Asserts that the byte array content of the HTTP response matches the expected byte array.
   *
   * @param expectedByte the expected byte array content (must not be {@code null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws TestAssertException if an error occurs when retrieving the content
   * @throws NullPointerException if the {@code expectedByte} is {@code null}
   * @since 1.0.0
   */
  TestAssert assertByteContent(byte[] expectedByte) throws TestAssertException;

  /**
   * Asserts that the HTTP response matches the given {@link ResultMatcher}.
   *
   * @param matcher the {@code ResultMatcher} to be used for validation (must not be {@code null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws TestAssertException if an error occurs during validation
   * @throws NullPointerException if the {@code matcher} is {@code null}
   * @since 1.0.0
   */
  TestAssert assertByResultMatcher(@NonNull ResultMatcher matcher) throws TestAssertException;
}
