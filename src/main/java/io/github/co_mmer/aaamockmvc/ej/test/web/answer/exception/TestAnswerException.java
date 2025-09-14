package io.github.co_mmer.aaamockmvc.ej.test.web.answer.exception;

/**
 * This class represents an error that is thrown when an error occurs during the processing of a
 * test answer operation. It serves to encapsulate underlying exceptions that may arise from various
 * issues in handling HTTP responses or related tasks.
 *
 * @since 2.0.0
 */
public final class TestAnswerException extends RuntimeException {

  /**
   * Constructs a new {@code TestAnswerException} with the specified cause.
   *
   * <p>This constructor allows the wrapping of another throwable, enabling better error handling
   * and debugging by preserving the original error information.
   *
   * @param stepName human-readable step name shown in failures
   * @param content assertion entry point label (e.g. {@code "asMap"})
   * @param simpleName simple type name of the intended target (e.g. {@code "User"})
   * @param cause the underlying mapping/parse exception
   * @since 2.0.0
   */
  public TestAnswerException(String stepName, String content, String simpleName, Throwable cause) {
    super(createMessage(stepName, content, simpleName), cause);
  }

  /**
   * Creates a failure for map-target mappings (e.g. {@code asMap(String.class, Integer.class)}).
   *
   * @param stepName human-readable step name shown in failures
   * @param content assertion entry point label (e.g. {@code "asMap"})
   * @param keySimpleName simple type name of the map key (e.g. {@code "String"})
   * @param valueSimpleName simple type name of the map value (e.g. {@code "Integer"})
   * @param cause the underlying mapping/parse exception
   * @since 2.0.0
   */
  public TestAnswerException(
      String stepName,
      String content,
      String keySimpleName,
      String valueSimpleName,
      Throwable cause) {
    super(createMessage(stepName, content, keySimpleName, valueSimpleName), cause);
  }

  private static String createMessage(String stepName, String content, String simpleName) {
    return "Answer step '"
        + stepName
        + "("
        + content
        + ")' failed: unable to map response body to "
        + simpleName
        + ".";
  }

  private static String createMessage(
      String stepName, String content, String keySimpleName, String valueSimpleName) {
    return "Answer step '"
        + stepName
        + "("
        + content
        + ")' failed: unable to map response body to Map<"
        + keySimpleName
        + ", "
        + valueSimpleName
        + ">.";
  }
}
