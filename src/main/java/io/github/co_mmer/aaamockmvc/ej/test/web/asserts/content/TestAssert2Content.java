package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import lombok.NonNull;

/**
 * Provides methods for asserting HTTP response content in tests.
 *
 * <p>This interface enables various assertions on the content of HTTP responses, such as verifying
 * that the content matches specific string values, comparing against expected objects, or accessing
 * additional methods for validating response headers.
 *
 * @since 1.0.0
 */
public interface TestAssert2Content {

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
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param expectedResponse the expected object (must not be {@code null})
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  <T> TestAssertLContent assertContentEquals(
      @NonNull Class<T> expectedClass, @NonNull T expectedResponse);

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
