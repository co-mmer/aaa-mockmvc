package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content;

import lombok.NonNull;

/**
 * Provides methods for asserting HTTP response content in tests.
 *
 * <ul>
 *   <li>{@link #assertContentNotEmpty()}: Asserts that the content of the HTTP response is not
 *       empty.
 *   <li>{@link #assertContentEmpty()}: Asserts that the content of the HTTP response is empty.
 *   <li>{@link #assertContentLength(int)}: Asserts that the length of the response content matches
 *       the specified length.
 *   <li>{@link #assertContentEquals(String)}: Asserts that the content of the HTTP response matches
 *       the expected string.
 * </ul>
 *
 * @since 1.0.0
 */
public interface TestAssert1Content {

  /**
   * Asserts that the string content of the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  TestAssert2Content assertContentNotEmpty();

  /**
   * Asserts that the string content of the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  TestAssertLContent assertContentEmpty();

  /**
   * Asserts that the length of the string content of the HTTP response matches the specified value.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param length the expected length of the HTTP response content
   * @return the current instance of {@code TestAssert2Content} for method chaining
   * @since 1.4.0
   */
  TestAssert2Content assertContentLength(int length);

  /**
   * Asserts that the string content of the HTTP response matches the expected string.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * <p>As of version 1.3.0, both the actual and expected response content are normalized using
   * Unicode Normalization Form C (NFC) to ensure consistent text representation across different
   * Unicode formats.
   *
   * @param expectedString the expected content of the response (must not be {@code null})
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @throws NullPointerException if the {@code expectedString} is {@code null}
   * @since 1.0.0
   */
  TestAssertLContent assertContentEquals(@NonNull String expectedString);
}
