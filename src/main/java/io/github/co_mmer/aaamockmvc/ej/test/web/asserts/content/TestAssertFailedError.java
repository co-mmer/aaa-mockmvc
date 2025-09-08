package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content;

import org.opentest4j.AssertionFailedError;

/**
 * Failure type for the ASSERT phase when mapping/deserializing the response body fails.
 *
 * <p><b>What it does:</b> Wraps the original cause (e.g., JSON parsing error) and produces a
 * concise, human-readable message that includes the step name, the assertion entry point (e.g.
 * {@code asClass}, {@code asCollection}, {@code asMap}), and the intended target type.
 *
 * <p><b>When you see this:</b> An assertion tried to interpret the cached response body as a
 * specific type, but the content was incompatible with that type or the mapper failed.
 *
 * <p><b>Example messages:</b>
 *
 * <pre>{@code
 * Assertion step 'Create user(asClass)' failed: unable to map response body to User.
 * Assertion step 'List users(asMap)' failed: unable to map response body to Map<String, Integer>.
 * }</pre>
 *
 * <p><b>Notes:</b>
 *
 * <ul>
 *   <li>This error is about <em>deserialization/mapping</em> of the response snapshot, not about
 *       HTTP status codes or transport errors (use status assertions or ACT failures for those).
 *   <li>The message intentionally embeds only lightweight type names (simple names) to keep
 *       failures readable.
 * </ul>
 *
 * @since 2.0.0
 */
public class TestAssertFailedError extends AssertionFailedError {

  /**
   * Creates a failure for single-target mappings (e.g. {@code asClass(User.class)}, {@code
   * asList(User.class)}, {@code asSet(User.class)}).
   *
   * @param stepName human-readable step name shown in failures (e.g. {@code "Create user"}); use
   *     {@code "<top-level step>"} when no named step is active
   * @param content assertion entry point label (e.g. {@code "asClass"}, {@code "asCollection"})
   * @param simpleName simple type name of the intended target (e.g. {@code "User"})
   * @param cause the underlying mapping/parse exception
   * @since 2.0.0
   */
  public TestAssertFailedError(
      String stepName, String content, String simpleName, Throwable cause) {
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
  public TestAssertFailedError(
      String stepName,
      String content,
      String keySimpleName,
      String valueSimpleName,
      Throwable cause) {
    super(createMessage(stepName, content, keySimpleName, valueSimpleName), cause);
  }

  private static String createMessage(String stepName, String content, String simpleName) {
    return "Assertion step '"
        + stepName
        + "("
        + content
        + ")' failed: unable to map response body to "
        + simpleName
        + ".";
  }

  private static String createMessage(
      String stepName, String content, String keySimpleName, String valueSimpleName) {
    return "Assertion step '"
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
