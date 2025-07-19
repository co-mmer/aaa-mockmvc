package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;

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
 * @since 1.4.0
 */
public interface TestAssertLByte {

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
