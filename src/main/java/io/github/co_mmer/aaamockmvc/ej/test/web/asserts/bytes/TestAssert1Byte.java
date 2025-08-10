package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes;

/**
 * Provides methods for asserting HTTP response byte content in tests.
 *
 * <ul>
 *   <li>{@link #assertContentNotEmpty()}: Asserts that the byte array content of the HTTP response
 *       is not empty.
 *   <li>{@link #assertContentEmpty()}: Asserts that the byte array content of the HTTP response is
 *       empty.
 *   <li>{@link #assertContentLength(int)}: Asserts that the length of the byte array matches the
 *       specified length.
 *   <li>{@link #assertContentEquals(byte[])}: Asserts that the byte array content of the HTTP
 *       response matches the expected byte array.
 * </ul>
 *
 * @since 1.4.0
 */
public interface TestAssert1Byte {

  /**
   * Asserts that the byte array content of the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.4.0
   * @deprecated Use {@link #assertContentNotEmpty()} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  TestAssert2Byte assertByteNotEmpty();

  /**
   * Asserts that the byte array content of the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.4.0
   * @deprecated Use {@link #assertContentEmpty()} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  TestAssertLByte assertByteEmpty();

  /**
   * Asserts that the length of the byte array content of the HTTP response matches the specified
   * value.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * <p>Note: The length refers to the number of bytes in the serialized HTTP response body using
   * UTF-8 encoding. For example, an empty JSON array {@code []} has a byte length of 2.
   *
   * @param expectedLength the expected length of the HTTP response content
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.4.0
   * @deprecated Use {@link #assertContentLength(int)} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  TestAssert2Byte assertByteLength(int expectedLength);

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
   * Asserts that the byte array content of the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.6.0
   */
  TestAssert2Byte assertContentNotEmpty();

  /**
   * Asserts that the byte array content of the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.6.0
   */
  TestAssertLByte assertContentEmpty();

  /**
   * Asserts that the length of the byte array content of the HTTP response matches the specified
   * value.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * <p>Note: The length refers to the number of bytes in the serialized HTTP response body using
   * UTF-8 encoding. For example, an empty JSON array {@code []} has a byte length of 2.
   *
   * @param expectedLength the expected length of the HTTP response content
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.6.0
   */
  TestAssert2Byte assertContentLength(int expectedLength);

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
}
