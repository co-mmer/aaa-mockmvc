package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head;

import org.springframework.lang.NonNull;

/**
 * Assertions for HTTP response headers.
 *
 * <p><b>What it does:</b> Provides key and value checks on the headers captured during {@code
 * actPerform().perform()}. Header names are treated case-insensitively and multi-value headers are
 * supported.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *  .get("/api/foo");
 *
 * actPerform()
 *  .perform();
 *
 * asserts()
 *  .headers()
 *  .containsKey("Content-Type")
 *  .containsEntry("Content-Type", "application/json")
 *  .doesNotContainKey("X-Debug");
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code actPerform().perform()} has been executed; no additional I/O is
 * performed, assertions operate on the stored snapshot.
 *
 * @since 1.0.0
 */
public interface TestAssertHead {

  /**
   * Asserts that a header with the given name is present (case-insensitive).
   *
   * @param expectedKey the header name; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the header is not present
   * @since 2.0.0
   */
  TestAssertHead containsKey(@NonNull String expectedKey);

  /**
   * Asserts that a header with the given name is not present.
   *
   * @param notExpectedKey the header name; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the header is present
   * @since 2.0.0
   */
  TestAssertHead doesNotContainKey(@NonNull String notExpectedKey);

  /**
   * Asserts that the header exists and that <em>one of its values</em> equals the expected value.
   * Works with multi-value headers (e.g., {@code Accept}, {@code Cache-Control}, {@code Set-Cookie}
   * as separate entries).
   *
   * @param expectedKey the header name; must not be {@code null}
   * @param expectedValue one required value for that header; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the header is missing or none of its values equals {@code
   *     expectedValue}
   * @since 2.0.0
   */
  TestAssertHead containsEntry(@NonNull String expectedKey, @NonNull String expectedValue);

  /**
   * Asserts that the header's values match <em>exactly</em> the provided list: same values, same
   * count, and in the <em>same order</em>; no extras.
   *
   * @param expectedKey the header name; must not be {@code null}
   * @param expectedValue the complete, ordered list of expected values; must contain at least one
   *     value
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the header is missing or its values differ in content, count, or
   *     order
   * @since 2.0.0
   */
  TestAssertHead containsEntryExactly(
      @NonNull String expectedKey, @NonNull String... expectedValue);
}
