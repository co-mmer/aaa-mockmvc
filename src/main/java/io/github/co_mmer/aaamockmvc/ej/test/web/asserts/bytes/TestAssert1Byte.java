package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes;

/**
 * Provides methods for asserting HTTP response byte content in tests.
 *
 * <ul>
 *   <li>{@link #assertByteNotEmpty()}: Asserts that the byte array content of the HTTP response is
 *       not empty.
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
