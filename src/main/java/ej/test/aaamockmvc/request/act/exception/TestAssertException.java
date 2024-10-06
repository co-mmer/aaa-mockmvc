package ej.test.aaamockmvc.request.act.exception;

/**
 * Exception for handling assertion errors in test results.
 *
 * <p>This exception is thrown when an assertion on a test result fails. It wraps the underlying
 * cause of the failure.
 *
 * @since 1.0.0
 */
public final class TestAssertException extends Exception {

  /**
   * Constructs a new {@code TestAssertException} with the specified cause.
   *
   * @param cause the underlying exception that caused the assertion failure (must not be {@code
   *     null})
   * @since 1.0.0
   */
  public TestAssertException(Throwable cause) {
    super(cause);
  }
}
