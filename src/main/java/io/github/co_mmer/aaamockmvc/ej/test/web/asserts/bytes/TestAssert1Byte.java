package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes;

/**
 * Entry point for raw <b>byte[]</b> content assertions.
 *
 * <p><b>What it does:</b> Exposes assertions for the HTTP response body at the byte level
 * (emptiness, length, exact equality). Use this when you want to verify the raw payload rather than
 * a deserialized representation.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *  .get("/api/binary");
 *
 * actPerform()
 *  .perform();
 *
 * asserts()
 *  .content()
 *  .asBytes()
 *  .isNotEmpty()
 *  .hasLength(128);
 *
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code actPerform().perform()} has been executed. Content assertions
 * apply only if the response contains a body. If no body is present, the cached string
 * representation is empty ({@code ""}) and the byte representation has length {@code 0}.
 *
 * <p><b>Note:</b> {@link #isEmpty()} in this byte-assertion arrange treats both a truly empty body
 * (length {@code 0}) <i>and</i> the canonical empty JSON forms {@code "[]"} or {@code "{}"} (after
 * trim) as empty. If you want semantic JSON checks (e.g., an empty list regardless of whitespace or
 * ordering), prefer {@code content().asCollection(...).isEmpty()} or {@code content().asMap(...)}.
 *
 * @since 1.4.0
 */
public interface TestAssert1Byte extends OperationEquals {

  /**
   * Asserts that the raw body is not empty (i.e., byte length {@code > 0}).
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   * based on the current state.
   * @throws AssertionError if the body is empty
   * @since 2.0.0
   */
  TestAssert2Byte isNotEmpty();

  /**
   * Asserts that the body is empty. <br> In this arrange, “empty” means either:
   *
   * <ul>
   *   <li>no bytes at all (length {@code 0}), or
   *   <li>a trimmed textual body equal to {@code "[]"} or {@code "{}"}.
   * </ul>
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   * based on the current state.
   * @throws AssertionError if the body is not considered empty
   * @since 2.0.0
   */
  TestAssertLByte isEmpty();

  /**
   * Asserts that the raw body has the given <b>byte</b> length.
   *
   * @param expectedLength the expected number of bytes
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   * based on the current state.
   * @throws AssertionError if the actual byte length differs from {@code expectedLength}
   * @since 2.0.0
   */
  TestAssert2Byte hasLength(int expectedLength);
}
