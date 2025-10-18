package io.github.co_mmer.aaamockmvc.ej.test.web.answer.exception;

/**
 * This class represents an error that is thrown when an error occurs during the processing of a
 * test answer operation. It serves to encapsulate underlying exceptions that may arise from various
 * issues in handling HTTP responses or related tasks.
 *
 * @since 2.0.0
 */
public final class TestAnswerFailed extends RuntimeException {

  /**
   * Constructs a new {@code TestAnswerFailed} with the specified cause.
   *
   * <p>The framework composes the message via an internal builder based on the current request
   * snapshot and passes it here; external callers should not compose this message themselves.
   *
   * @param message human-readable failure message with request answer
   * @since 2.0.0
   */
  public TestAnswerFailed(String message) {
    super(message);
  }
}
