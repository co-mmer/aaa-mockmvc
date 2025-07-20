package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;

/**
 * Provides methods for asserting HTTP response byte content in tests.
 *
 * <ul>
 *   <li>{@link #assertContentEquals(byte[])}: Asserts that the byte array content of the HTTP
 *       response matches the expected byte array.
 *   <li>{@link #assertHead()}: Provides assertion methods for validating the HTTP response headers.
 * </ul>
 *
 * @since 1.4.0
 */
public interface TestAssert2Byte {

  /**
   * Asserts that the byte array content of the HTTP response matches the expected byte array.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedByte the expected byte array content (must not be {@code null})
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if the {@code expectedByte} is {@code null}
   * @since 1.4.0
   * @deprecated Use {@link #assertContentEquals(byte[])} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  TestAssertLByte assertByteEquals(byte[] expectedByte);

  /**
   * Asserts that the byte array content of the HTTP response matches the expected byte array.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedByte the expected byte array content (must not be {@code null})
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if the {@code expectedByte} is {@code null}
   * @since 1.6.0
   */
  TestAssertLByte assertContentEquals(byte[] expectedByte);

  /**
   * Asserts that the HTTP response is valid for a HEAD request.
   *
   * <p>This method returns an instance of {@code TestAssertHead} for asserting the headers of the
   * HTTP response. It allows various validations of response headers, such as checking for the
   * presence or absence of specific headers and comparing header values.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.4.0
   */
  TestAssertHead assertHead();
}
