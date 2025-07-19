package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestAssertContent;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;

/**
 * Assertions for the HTTP response status code.
 *
 * <p><b>What it does:</b> Provides exact and range-based checks for the status captured during
 * {@code actPerform().perform()}. Use this to verify success, redirects, client/server errors, or
 * specific codes like 200/201/404.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *  .get("/api/users/42");
 *
 * actPerform()
 *  .perform();
 *
 * asserts()
 *  .status()
 *  .isOk()
 *  .content()
 *  ...
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code actPerform().perform()} has been executed; assertions operate on
 * the stored snapshot.
 *
 * @since 1.1.0
 */
public interface TestAssert2Status {

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

  /**
   * Switches to content assertions for the same response snapshot.
   *
   * <p>Use this to assert the response body as string/bytes or via deserialization (e.g., {@code
   * asString()}, {@code asBytes()}, {@code asClass(..)}, {@code asCollection(..)}, {@code
   * asMap(..)}). No additional I/O is performed; the body captured during {@code
   * actPerform().perform()} is reused.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @since 2.0.0
   */
  TestAssertContent content();
}
