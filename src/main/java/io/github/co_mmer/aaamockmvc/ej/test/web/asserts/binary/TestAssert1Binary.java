package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.binary;

public interface TestAssert1Binary {

  /**
   * Asserts that the byte array content of the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertBinary} for method chaining
   * @since 1.0.0
   */
  TestAssert2Binary assertBinaryByteNotEmpty();

  /**
   * Asserts that the byte array content of the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertBinary} for method chaining
   * @since 1.0.0
   */
  TestAssertLBinary assertBinaryByteEmpty();

  /**
   * Asserts that the byte array content of the HTTP response matches the expected byte array.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedByte the expected byte array content (must not be {@code null})
   * @return the current instance of {@code TestAssertBinary} for method chaining
   * @throws NullPointerException if the {@code expectedByte} is {@code null}
   * @since 1.0.0
   */
  TestAssertLBinary assertBinaryByteEquals(byte[] expectedByte);
}
