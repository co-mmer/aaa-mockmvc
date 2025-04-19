package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes;

/**
 * Provides methods for asserting HTTP response byte content in tests.
 *
 * <ul>
 *   <li>{@link #assertByteNotEmpty()}: Asserts that the byte array content of the HTTP response is
 *       not empty.
 *   <li>{@link #assertByteLength(int)}: Asserts that the length of the byte array matches the
 *       specified length.
 *   <li>{@link #assertByteEmpty()}: Asserts that the byte array content of the HTTP response is
 *       empty.
 *   <li>{@link #assertByteEquals(byte[])}: Asserts that the byte array content of the HTTP response
 *       matches the expected byte array.
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
   * @return the current instance of {@code TestAssert2Byte} for method chaining
   * @since 1.4.0
   */
  TestAssert2Byte assertByteNotEmpty();

  /**
   * Asserts that the byte array content of the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertLByte} for method chaining
   * @since 1.4.0
   */
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
   * @param length the expected length of the HTTP response content
   * @return the current instance of {@code TestAssert2Byte} for method chaining
   * @since 1.4.0
   */
  TestAssert2Byte assertByteLength(int length);

  /**
   * Asserts that the byte array content of the HTTP response matches the expected byte array.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedByte the expected byte array content (must not be {@code null})
   * @return the current instance of {@code TestAssertLByte} for method chaining
   * @throws NullPointerException if the {@code expectedByte} is {@code null}
   * @since 1.4.0
   */
  TestAssertLByte assertByteEquals(byte[] expectedByte);
}
