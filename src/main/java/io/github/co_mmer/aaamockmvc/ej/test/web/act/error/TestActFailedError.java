package io.github.co_mmer.aaamockmvc.ej.test.web.act.error;

import org.opentest4j.AssertionFailedError;

/**
 * Failure type for the ACT phase. The human-readable message is assembled internally by the
 * framework; this class just transports that message plus the original cause.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *  .post("/api/items")
 *  .headers()
 *  .auth("Bearer token-123");
 *
 * actPerform()
 * .perform(); // on infrastructure/runtime failure, the framework throws TestActFailedError
 * }</pre>
 *
 * <p><b>Example message:</b>
 *
 * <pre>{@code
 * ACT failed: POST http://localhost/api/items?verbose=true
 * TestArrangeResult: POST http://localhost/api/items?verbose=true | query: verbose=true
 * Headers: accepts=application/json | content-type=application/json;charset=UTF-8 | key-value={Authorization=[Bearer token-123], X-Trace-Id=[1234]}
 * Body: 67 bytes | content-type=application/json;charset=UTF-8 | preview: {"name":"A","price":12.34,"tags":["new","sale"]}
 * Cause: NestedServletException: TestArrangeResult processing failed: java.lang.NullPointerException
 * }</pre>
 *
 * @since 2.0.0
 */
public final class TestActFailedError extends AssertionFailedError {

  /**
   * Creates a new failure with the given, already-assembled message and the original cause.
   *
   * <p>The framework composes the message via an internal builder based on the current request
   * snapshot and passes it here; external callers should not compose this message themselves.
   *
   * @param message human-readable failure message with request arrange
   * @since 2.0.0
   */
  public TestActFailedError(String message) {
    super(message);
  }
}
