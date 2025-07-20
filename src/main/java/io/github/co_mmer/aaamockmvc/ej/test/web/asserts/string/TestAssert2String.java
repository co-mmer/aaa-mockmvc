package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import lombok.NonNull;

/**
 * Provides methods for asserting HTTP response content in tests.
 *
 * <ul>
 *   <li>{@link #assertContentEquals(String)}: Asserts that the content of the HTTP
 *       response matches the expected string.
 *   <li>{@link #assertHead()}: Provides assertion methods for validating the HTTP response headers.
 * </ul>
 *
 * @since 1.0.0
 */
public interface TestAssert2String {

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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   * based on the current state.
   * @throws NullPointerException if the {@code expectedString} is {@code null}
   * @since 1.0.0
   * @deprecated Use {@link #assertContentEquals(String)}} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  TestAssertLString assertStringEquals(@NonNull String expectedString);

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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   * based on the current state.
   * @throws NullPointerException if the {@code expectedString} is {@code null}
   * @since 1.6.0
   */
  TestAssertLString assertContentEquals(@NonNull String expectedString);

  /**
   * Provides assertion methods for validating the HTTP response headers.
   *
   * <p>This method returns an instance of {@code TestAssertHead}, which provides assertion methods
   * for validating the headers of the HTTP response, such as checking for the presence or absence
   * of specific headers and comparing header values.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   * based on the current state.
   * @since 1.0.0
   */
  TestAssertHead assertHead();
}
