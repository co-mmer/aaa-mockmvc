package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.exception;

/**
 * This class represents an exception that is thrown when an error occurs during the processing of a
 * arrange operation. It serves to encapsulate underlying exceptions that may arise from various
 * issues in handling HTTP responses or related tasks.
 *
 * @since 1.3.0
 */
public final class TestArrangeException extends Exception {

  /**
   * Constructs a new {@code TestArrangeException} with the specified cause.
   *
   * <p>This constructor allows the wrapping of another throwable, enabling better error handling
   * and debugging by preserving the original exception information.
   *
   * @param cause the underlying cause of the exception (must not be {@code null})
   * @since 1.3.0
   */
  public TestArrangeException(Throwable cause) {
    super(cause);
  }
}
