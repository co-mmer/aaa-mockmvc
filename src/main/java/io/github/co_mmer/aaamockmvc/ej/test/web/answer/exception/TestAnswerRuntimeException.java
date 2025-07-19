package io.github.co_mmer.aaamockmvc.ej.test.web.answer.exception;

/**
 * This class represents an error that is thrown when an error occurs during the processing of a
 * test answer operation. It serves to encapsulate underlying exceptions that may arise from various
 * issues in handling HTTP responses or related tasks.
 *
 * @since 1.2.0
 */
public final class TestAnswerRuntimeException extends RuntimeException {

  /**
   * Constructs a new {@code TestAnswerRuntimeException} with the specified cause.
   *
   * <p>This constructor allows the wrapping of another throwable, enabling better error handling
   * and debugging by preserving the original error information.
   *
   * @param cause the underlying cause of the error (must not be {@code null})
   * @since 1.2.0
   */
  public TestAnswerRuntimeException(Throwable cause) {
    super(cause);
  }
}
