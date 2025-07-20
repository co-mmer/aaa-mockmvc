package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string;

import lombok.NonNull;

/**
 * Provides methods for asserting HTTP response content in tests.
 *
 * <ul>
 *   <li>{@link #assertContentIsNotEmpty()}: Asserts that the content of the HTTP response is not
 *       empty.
 *   <li>{@link #assertContentIsEmpty()}: Asserts that the content of the HTTP response is empty.
 *   <li>{@link #assertContentLength(int)}: Asserts that the length of the response content matches
 *       the specified length.
 *   <li>{@link #assertContentEquals(String)}: Asserts that the content of the HTTP response matches
 *       the expected string.
 * </ul>
 *
 * @since 1.0.0
 */
public interface TestAssert1String {

  /**
   * Asserts that the string content of the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   * based on the current state.
   * @since 1.0.0
   * @deprecated Use {@link #assertContentIsNotEmpty()} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  TestAssert2String assertStringNotEmpty();

  /**
   * Asserts that the string content of the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   * based on the current state.
   * @since 1.0.0
   * @deprecated Use {@link #assertContentIsEmpty()} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  TestAssertLString assertStringEmpty();

  /**
   * Asserts that the length of the string content of the HTTP response matches the specified
   * value.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedLength the expected length of the HTTP response content
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   * based on the current state.
   * @since 1.4.0
   * @deprecated Use {@link #assertContentLength(int)} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  TestAssert2String assertStringLength(int expectedLength);

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
   * Asserts that the string content of the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   * based on the current state.
   * @since 1.6.0
   */
  TestAssert2String assertContentIsNotEmpty();

  /**
   * Asserts that the string content of the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   * based on the current state.
   * @since 1.6.0
   */
  TestAssertLString assertContentIsEmpty();

  /**
   * Asserts that the length of the string content of the HTTP response matches the specified
   * value.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedLength the expected length of the HTTP response content
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   * based on the current state.
   * @since 1.6.0
   */
  TestAssert2String assertContentLength(int expectedLength);

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
}
