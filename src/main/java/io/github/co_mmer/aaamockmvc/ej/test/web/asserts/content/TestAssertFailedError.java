package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content;

import org.opentest4j.AssertionFailedError;

public class TestAssertFailedError extends AssertionFailedError {

  public TestAssertFailedError(
      String stepName, String content, String simpleName, Throwable cause) {
    super(createMessage(stepName, content, simpleName), cause);
  }

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
