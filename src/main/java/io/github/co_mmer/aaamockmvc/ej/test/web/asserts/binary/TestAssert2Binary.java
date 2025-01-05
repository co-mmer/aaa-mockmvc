package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.binary;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;

public interface TestAssert2Binary {

  /**
   * Asserts that the byte array content of the HTTP response matches the expected byte array.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedByte the expected byte array content (must not be {@code null})
   * @return the current instance of {@code TestAssertBinary} for method chaining
   * @throws NullPointerException if the {@code expectedByte} is {@code null}
   * @since 1.4.0
   */
  TestAssertLBinary assertBinaryByteEquals(byte[] expectedByte);

  /**
   * Asserts that the HTTP response is valid for a HEAD request.
   *
   * <p>This method returns an instance of {@code TestAssertHead} for asserting the headers of the
   * HTTP response. It allows various validations of response headers, such as checking for the
   * presence or absence of specific headers and comparing header values.
   *
   * @return an instance of {@code TestAssertHead} for further assertions on headers
   * @since 1.4.0
   */
  TestAssertHead assertHead();
}
