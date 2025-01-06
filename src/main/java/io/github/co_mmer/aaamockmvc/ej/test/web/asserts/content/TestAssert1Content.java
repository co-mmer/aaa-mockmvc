package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content;

import lombok.NonNull;

/**
 * Provides methods for asserting HTTP response content in tests.
 *
 * <ul>
 *   <li>{@link #assertContentNotEmpty()}: Asserts that the content of the HTTP response is not empty.</li>
 *   <li>{@link #assertContentEmpty()}: Asserts that the content of the HTTP response is empty.</li>
 *   <li>{@link #assertContentLength(int)}: Asserts that the length of the response content matches the specified length.</li>
 *   <li>{@link #assertContentEquals(String)}: Asserts that the content of the HTTP response matches the expected string.</li>
 *   <li>{@link #assertContentEquals(Class, Object)}: Asserts that the content of the HTTP response matches the expected object.</li>
 * </ul>
 *
 * @since 1.0.0
 */
public interface TestAssert1Content {

  /**
   * Asserts that the string content of the HTTP response is not empty.
   *
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  TestAssert2Content assertContentNotEmpty();

  /**
   * Asserts that the string content of the HTTP response is empty.
   *
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  TestAssertLContent assertContentEmpty();

  /**
   * Asserts that the length of the string content of the HTTP response matches the specified
   * value.
   *
   * @param length the expected length of the HTTP response content
   * @return the current instance of {@code TestAssert2Content} for method chaining
   * @since 1.4.0
   */
  TestAssert2Content assertContentLength(int length);

  /**
   * Asserts that the string content of the HTTP response matches the expected string.
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

  /**
   * Asserts that the object content of the HTTP response matches the expected object, using the
   * provided deserializer(s).
   *
   * <p>As of version 1.3.0, both the actual and expected response content are normalized using
   * Unicode Normalization Form C (NFC) to ensure consistent text representation across different
   * Unicode formats.
   *
   * @param <T>              the type of the expected response object
   * @param expectedClass    the class of the expected response object (must not be {@code null})
   * @param expectedResponse the expected object (must not be {@code null})
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  <T> TestAssertLContent assertContentEquals(
      @NonNull Class<T> expectedClass, @NonNull T expectedResponse);
}
