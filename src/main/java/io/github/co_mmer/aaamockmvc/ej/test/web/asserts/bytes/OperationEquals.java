package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes;

interface OperationEquals {

  /**
   * Asserts that the raw body is exactly equal to the given byte array (byte-for-byte).
   *
   * @param expectedByte the expected payload; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the actual bytes differ from {@code expectedByte}
   * @since 2.0.0
   */
  TestAssertLByte isEqualTo(byte[] expectedByte);
}
