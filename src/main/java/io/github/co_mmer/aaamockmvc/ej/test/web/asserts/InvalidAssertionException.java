package io.github.co_mmer.aaamockmvc.ej.test.web.asserts;

/**
 * Thrown to indicate that a test assertion was constructed in an invalid or logically meaningless
 * way.
 *
 * <p>Unlike a standard {@link AssertionError}—which indicates that the production code behaved
 * incorrectly—this exception signals a "test smell" or design flaw in the test code itself. It
 * prevents false-positive "green tests" that appear to pass but do not actually verify any behavior
 * (e.g., calling {@code toContain()} with an empty collection or calling {@code notToContain()} on
 * an empty collection).
 *
 * @since 2.1.0
 */
public class InvalidAssertionException extends RuntimeException {

  /**
   * Constructs a new {@code InvalidAssertionException} with a detailed message explaining the
   * logical flaw in the assertion and providing actionable hints on how to fix it.
   *
   * @param message the detailed, formatted explanation of why the assertion is invalid
   */
  public InvalidAssertionException(String message) {
    super(message);
  }

  /**
   * Fills in the execution stack trace for this exception.
   *
   * <p>Overridden to optimize performance and prevent internal testing library stack frames from
   * cluttering the developer's test output, keeping the focus entirely on the invalid assertion
   * message.
   *
   * @return a reference to this {@code Throwable} instance
   */
  @Override
  public synchronized Throwable fillInStackTrace() {
    return super.fillInStackTrace();
  }
}
