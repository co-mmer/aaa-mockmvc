package io.github.co_mmer.aaamockmvc.test.web.act.exception;

/**
 * This class represents an exception that occurs during the execution of HTTP request actions.
 *
 * <p>It serves as a wrapper for exceptions that may arise during request processing, providing a
 * consistent way to handle errors in the context of HTTP action execution.
 *
 * @since 1.0.0
 */
public final class TestActException extends Exception {

  /**
   * Constructs a new {@code TestActException} with the specified cause.
   *
   * @param cause the cause of the exception (must not be {@code null})
   * @since 1.0.0
   */
  public TestActException(Throwable cause) {
    super(cause);
  }
}
