package io.github.co_mmer.aaamockmvc.ej.test.web.act;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.error.TestActFailedError;

/**
 * Executes the arranged HTTP request (the <b>actPerform</b> phase) and captures an immutable
 * response snapshot for subsequent assertions.
 *
 * <p><b>What it does:</b> Runs the request built during the arrange phase against the underlying
 * test engine (configured MockMvc) and stores status, headers, and body (bytes/string) in the
 * arrange. Later calls to {@code asserts()} / {@code answer()} reuse this snapshot—no additional
 * I/O occurs.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *  .get("/api/items");
 *
 * actPerform()
 *  .perform();
 *
 * asserts()
 *  .status()
 *  .isOk();
 * }</pre>
 *
 * <p><b>Preconditions:</b> A valid request has been configured in the arrange phase.
 *
 * <p><b>Postconditions:</b> A response snapshot is available to assertion/answer steps (empty body
 * → string {@code ""}, bytes length {@code 0}).
 *
 * @since 1.0.0
 */
public interface TestAct {

  /**
   * Executes the arranged request and records the response snapshot.
   *
   * <p>Honors any actPerform-level configuration (e.g., logging, timing, repeat). HTTP error
   * statuses (4xx/5xx) are <em>not</em> thrown as exceptions; they are captured in the snapshot and
   * should be asserted in the status/content steps.
   *
   * <p><b>Example failure message (on infrastructure/runtime error):</b>
   *
   * <pre>{@code
   * ACT failed: POST http://localhost/api/items?verbose=true
   * TestArrangeResult: POST http://localhost/api/items?verbose=true | query: verbose=true
   * Headers: accepts=application/json | content-type=application/json;charset=UTF-8 | key-value={Authorization=[Bearer token-123], X-Trace-Id=[1234]}
   * Body: 67 bytes | content-type=application/json;charset=UTF-8 | preview: {"name":"A","price":12.34,"tags":["new","sale"]}
   * Cause: NestedServletException: TestArrangeResult processing failed: java.lang.NullPointerException
   * }</pre>
   *
   * @return the actPerform step for fluent chaining based on the current state.
   * @throws TestActFailedError if the request cannot be executed or the response snapshot cannot be
   *     created (e.g., client/setup failure, serialization/encoding error, or transport error).
   *     This does not represent HTTP error status codes.
   * @since 1.0.0
   */
  TestAct perform() throws TestActFailedError;
}
