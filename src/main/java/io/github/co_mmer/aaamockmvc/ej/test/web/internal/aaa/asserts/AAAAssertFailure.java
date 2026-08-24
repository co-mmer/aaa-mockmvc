package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringJoiner;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Since("2.1.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
final class AAAAssertFailure {

  static String create(TestStepDto step, Object expected, Object actual, String reason) {

    var lineSeparator = System.lineSeparator();
    var message = new StringBuilder(lineSeparator);

    if (step != null && step.name() != null && !step.name().isBlank()) {
      message.append("Step:     ").append(step.name().strip()).append(lineSeparator);
    }

    return message
        .append("Expected: ")
        .append(formatValue(expected))
        .append(lineSeparator)
        .append("Actual:   ")
        .append(formatValue(actual))
        .append(lineSeparator)
        .append("Reason:   ")
        .append(reason)
        .toString();
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
      var result = new StringJoiner(", ", "[", "]");

      for (var index = 0; index < Array.getLength(value); index++) {
        result.add(formatValue(Array.get(value, index)));
      }

      return result.toString();
    }

    return String.valueOf(value);
  }
}
