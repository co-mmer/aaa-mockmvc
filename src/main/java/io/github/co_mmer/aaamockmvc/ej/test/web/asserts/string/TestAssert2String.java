package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import lombok.NonNull;

/**
 * Assertions for the response body as a {@link String}.
 *
 * <p><b>What it does:</b> Provides string-specific checks such as emptiness, length, and equality
 * on the cached response body produced by {@code actPerform().perform()}.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *  .get("/api/text");
 *
 * actPerform()
 *  .perform();
 *
 * asserts()
 *  .content()
 *  .asString()
 *  .isNotEmpty()
 *  .isEqualTo("aaa")
 *  .headers()
 *  ...
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code actPerform().perform()} has been executed. If no body is present,
 * the cached string representation is empty ({@code ""}).
 *
 * @since 1.0.0
 */
public interface TestAssert2String {

  /**
   * Asserts that the body string is equal to the given {@code expected} string.
   *
   * <p>Note: For predictable text comparison, both actual and expected values are normalized using
   * Unicode Normalization Form C (NFC) where applicable.
   *
   * @param expectedString the expected string; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the strings are not equal
   * @since 2.0.0
   */
  TestAssertLString isEqualTo(@NonNull String expectedString);

  /**
   * Switches to HTTP header assertions for the same response snapshot.
   *
   * <p>Use this to continue the assertion chain on headers (e.g. {@code containsKey}, {@code
   * containsEntry}). No additional I/O is performed; the headers captured during {@code
   * actPerform().perform()} are reused.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @since 2.0.0
   */
  TestAssertHead headers();
}
