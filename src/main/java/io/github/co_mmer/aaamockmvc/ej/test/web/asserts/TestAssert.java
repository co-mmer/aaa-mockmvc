package io.github.co_mmer.aaamockmvc.ej.test.web.asserts;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestAssertContent;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status.TestAssert1Status;

/**
 * Root entry for assertions on the captured HTTP response.
 *
 * <p><b>What it does:</b> Provides access to status, content, and header assertions operating on
 * the immutable response snapshot produced by {@code actPerform().perform()}. No additional I/O is
 * performed; all checks reuse the stored status, headers, and body.
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
 *   .status()
 *   .isOk()
 *   .content()
 *   .asClass(User.class)
 *   .isNotNull()
 *   .headers()
 *   .containsKey("Content-Type");
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code actPerform().perform()} has been executed; assertions operate on
 * the stored snapshot.
 *
 * @since 1.0.0
 */
public interface TestAssert {

  /**
   * Switches to status assertions for the same response snapshot.
   *
   * <p>Use this to verify exact codes (e.g., {@code 200}/{@code 201}/{@code 404}) or ranges (e.g.,
   * {@code is2xxSuccessful()}, {@code isInRange(200, 299)}). No additional I/O is performed; the
   * status captured during {@code actPerform().perform()} is reused.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @since 2.0.0
   */
  TestAssert1Status status();

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
