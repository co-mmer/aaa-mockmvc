package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringJoiner;

@Since("2.1.0")
public record AAAAssertDetail(TestStepDto step, Object expected, Object actual, String reason) {

  @Since("2.1.0")
  public static AAAAssertDetail bodyAbsent(TestStepDto step, Object expected, Object actual) {
    return new AAAAssertDetail(step, expected, actual, "The response body was absent.");
  }

  @Since("2.1.0")
  public static AAAAssertDetail bodyPresent(TestStepDto step, Object expected, Object actual) {
    return new AAAAssertDetail(step, expected, actual, "The response body was present.");
  }

  @Since("2.1.0")
  public static AAAAssertDetail bodyNotEmpty(TestStepDto step, Object expected, Object actual) {
    return new AAAAssertDetail(step, expected, actual, "The response body was not empty.");
  }

  @Since("2.1.0")
  public static AAAAssertDetail bodyEmpty(TestStepDto step, Object expected, Object actual) {
    return new AAAAssertDetail(step, expected, actual, "The response body was empty.");
  }

  @Since("2.1.0")
  public static AAAAssertDetail dimensionDiffered(
      TestStepDto step, Object expected, Object actual, String dimension) {
    return new AAAAssertDetail(
        step, expected, actual, "The response body had a different " + dimension + ".");
  }

  @Since("2.1.0")
  public static AAAAssertDetail valueDiffered(TestStepDto step, Object expected, Object actual) {
    return new AAAAssertDetail(
        step, expected, actual, "The response body differed from the expected value.");
  }

  @Since("2.1.0")
  public static AAAAssertDetail typeDiffered(TestStepDto step, Object expected, Object actual) {
    return new AAAAssertDetail(step, expected, actual, "The response body had a different type.");
  }

  @Since("2.1.0")
  public static AAAAssertDetail elementsMissing(TestStepDto step, Object expected, Object actual) {
    return new AAAAssertDetail(
        step, expected, actual, "The response body did not contain every expected element.");
  }

  @Since("2.1.0")
  public static AAAAssertDetail unexpectedElement(
      TestStepDto step, Object expected, Object actual) {
    return new AAAAssertDetail(
        step, expected, actual, "The response body contained an unexpected element.");
  }

  @Since("2.1.0")
  public static AAAAssertDetail elementsDiffered(TestStepDto step, Object expected, Object actual) {
    return new AAAAssertDetail(
        step, expected, actual, "The response body contained different elements.");
  }

  @Since("2.1.0")
  public static AAAAssertDetail notAllMatched(TestStepDto step, Object expected, Object actual) {
    return new AAAAssertDetail(
        step, expected, actual, "At least one value did not match every condition.");
  }

  @Since("2.1.0")
  public static AAAAssertDetail noneMatched(TestStepDto step, Object expected, Object actual) {
    return new AAAAssertDetail(step, expected, actual, "No value matched any condition.");
  }

  @Since("2.1.0")
  public static AAAAssertDetail conditionMatched(TestStepDto step, Object expected, Object actual) {
    return new AAAAssertDetail(step, expected, actual, "At least one value matched a condition.");
  }

  @Since("2.1.0")
  public static AAAAssertDetail headerKeyMissing(TestStepDto step, Object expected, Object actual) {
    return new AAAAssertDetail(
        step, expected, actual, "The response headers did not contain the expected key.");
  }

  @Since("2.1.0")
  public static AAAAssertDetail unexpectedHeaderKey(
      TestStepDto step, Object expected, Object actual) {
    return new AAAAssertDetail(
        step, expected, actual, "The response headers contained the unexpected key.");
  }

  @Since("2.1.0")
  public static AAAAssertDetail headerValueMissing(
      TestStepDto step, Object expected, Object actual) {
    return new AAAAssertDetail(
        step, expected, actual, "The response header did not contain the expected value.");
  }

  @Since("2.1.0")
  public static AAAAssertDetail headerValuesDiffered(
      TestStepDto step, Object expected, Object actual) {
    return new AAAAssertDetail(
        step, expected, actual, "The response header contained different values.");
  }

  @Since("2.1.0")
  public static AAAAssertDetail statusDiffered(TestStepDto step, Object expected, Object actual) {
    return new AAAAssertDetail(
        step, expected, actual, "The response status differed from the expected status.");
  }

  @Since("2.1.0")
  public static AAAAssertDetail statusOutOfRange(TestStepDto step, Object expected, Object actual) {
    return new AAAAssertDetail(
        step, expected, actual, "The response status was outside the expected range.");
  }

  @Since("2.1.0")
  public String formatMessage() {
    var separator = System.lineSeparator();
    var message = new StringJoiner(separator, separator, "");

    if (hasStepName()) {
      message.add("Step:     " + step.name().strip());
    }

    message.add("Expected: " + formatValue(expected));
    message.add("Actual:   " + formatValue(actual));
    message.add("Reason:   " + reason);

    return message.toString();
  }

  private boolean hasStepName() {
    return step != null && StringUtils.isNotBlank(step.name());
  }

  private static String formatValue(Object value) {
    if (value == null) {
      return "null";
    }

    if (value instanceof CharSequence text && text.isEmpty()) {
      return "\"\"";
    }

    if (value instanceof byte[] bytes) {
      return "byte[" + bytes.length + "] " + Arrays.toString(bytes);
    }

    if (value.getClass().isArray()) {
      var values = new StringJoiner(", ", "[", "]");

      for (var index = 0; index < Array.getLength(value); index++) {
        values.add(formatValue(Array.get(value, index)));
      }

      return values.toString();
    }

    return String.valueOf(value);
  }
}
