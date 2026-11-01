package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringJoiner;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Since("2.1.0")
public record AAAAssertDetail(TestStepDto step, Object expected, Object actual, String assertion) {
  
  @NonNull
  @Since("2.1.0")
  public static org.opentest4j.AssertionFailedError failure(
      @Nullable TestStepDto step,
      @NonNull String assertion,
      @Nullable Object expected,
      @Nullable Object actual) {
    return new org.opentest4j.AssertionFailedError(
        new AAAAssertDetail(step, expected, actual, assertion).formatMessage(), expected, actual);
  }

  @Since("2.1.0")
  @NonNull
  public String formatMessage() {
    var separator = System.lineSeparator();
    var message = new StringJoiner(separator, separator, "");

    if (hasStepName()) {
      message.add("Step:      " + this.step.name().strip());
    }

    message.add("Expected:  " + formatValue(this.expected));
    message.add("Actual:    " + formatValue(this.actual));
    message.add("Assertion: " + this.assertion);

    return message.toString();
  }

  private boolean hasStepName() {
    return this.step != null && StringUtils.isNotBlank(this.step.name());
  }

  @NonNull
  private static String formatValue(@Nullable Object value) {
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
