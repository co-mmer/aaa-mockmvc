package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;

/**
 * Assertions for a deserialized {@link java.util.Map} with keys {@code K} and values {@code V}.
 *
 * <p><b>What it does:</b> Provides (non-)emptiness, size, and equality checks on the map produced
 * by a prior {@code content().asMap(K,V)} step. The response body has already been deserialized
 * once using the configured mapper and is cached for all subsequent assertions.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *  .get("/api/stats");
 *
 * actPerform()
 *  .perform();
 *
 * asserts()
 *  .content()
 *  .asMap(String.class, Integer.class)
 *  .isNotEmpty()
 *  .hasSize(3)
 *  .isEqualTo(Map.of("count", 5, "errors", 0, "warnings", 1))
 *  .headers()
 *  ...
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code actPerform().perform()} has been executed and {@code
 * content().asMap(K,V)} successfully deserialized a JSON object into a Java map.
 *
 * @since 1.4.0
 */
public interface TestAssertLMap {

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
