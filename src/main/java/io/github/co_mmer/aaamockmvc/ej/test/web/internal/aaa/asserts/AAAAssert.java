package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.InvalidAssertionException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.Predicate;

@Since("2.1.0")
public final class AAAAssert<A, N> {

  private static final String RESPONSE_BODY_ABSENT = "The response body was absent.";

  private final TestStepDto step;
  private final AssertOperand<A, N> actualValue;

  private AAAAssert(TestStepDto step, AssertOperand<A, N> actualValue) {
    this.step = step;
    this.actualValue = actualValue;
  }

  @Since("2.1.0")
  public static <A, N> AAAAssert<A, N> expect(TestStepDto step, AssertOperand<A, N> actualValue) {

    return new AAAAssert<>(step, actualValue);
  }

  @Since("2.1.0")
  public void toBePresent() {
    verify(actualValue.actual() != null, "not null", actualValue.actual(), RESPONSE_BODY_ABSENT);
  }

  @Since("2.1.0")
  public void toBeAbsent() {
    verify(
        actualValue.actual() == null,
        "null",
        actualValue.actual(),
        "The response body was present.");
  }

  @Since("2.1.0")
  public void toBeEmpty() {
    var actual = requireActual("empty");
    verify(sizeOf(actual) == 0, "empty", actual, "The response body was not empty.");
  }

  @Since("2.1.0")
  public void notToBeEmpty() {
    var actual = requireActual("not empty");
    verify(sizeOf(actual) > 0, "not empty", actual, "The response body was empty.");
  }

  @Since("2.1.0")
  public void toHaveSize(int expectedSize) {
    var expected = "size " + expectedSize;
    var actual = requireActual(expected);
    var actualSize = sizeOf(actual);

    verify(
        actualSize == expectedSize,
        expected,
        "size " + actualSize,
        "The response body had a different size.");
  }

  @Since("2.1.0")
  public void toHaveLength(int length) {
    var expected = "length " + length;
    var actual = requireActual(expected);
    var actualSize = sizeOf(actual);

    verify(
        actualSize == length,
        expected,
        "length " + actualSize,
        "The response body had a different length.");
  }

  @Since("2.1.0")
  public void toEqual(AssertValue<?, ?> expectedValue) {
    var actual = requireActual(expectedValue.value());

    verify(
        Objects.deepEquals(actualValue.normalizedValue(), expectedValue.normalizedValue()),
        expectedValue.value(),
        actual,
        "The response body differed from the expected value.");
  }

  @Since("2.1.0")
  public AAAAssert<A, N> toHaveSameTypeAndValueAs(AssertValue<?, ?> expectedValue) {
    var actual = requireActual(expectedValue.value());

    verify(
        actual.getClass().equals(expectedValue.value().getClass()),
        expectedValue.value(),
        actual,
        "The response body had a different type.");
    return this;
  }

  @Since("2.1.0")
  public void toContain(AssertValue<?, ? extends Collection<?>> expectedValue) {
    var expected = expectedValue.normalizedValue();
    var actual = normalizedCollection("contains " + expected);

    verifyElementsForToContain(expected);

    verify(
        actual.containsAll(expected),
        "contains " + expected,
        actualValue.actual(),
        "The response body did not contain every expected element.");
  }

  private static void verifyElementsForToContain(Collection<?> expected) {
    if (expected.isEmpty()) {
      throw new InvalidAssertionException(
          System.lineSeparator()
              + """
              `toContain()` was called with an empty collection.

              Reason:
              Checking whether a collection contains an empty collection is always true.
              This creates a false-positive "green test" that does not verify production behavior.

              How to fix:
              -> To verify that the collection is empty, use:
                 isEmpty()

              -> To verify the number of elements, use:
                 hasSize(expectedSize)
              """
                  .strip());
    }
  }

  @Since("2.1.0")
  public void notToContain(AssertValue<?, ? extends Collection<?>> unexpectedValue) {

    var unexpected = unexpectedValue.normalizedValue();
    var actual = normalizedCollection("does not contain " + unexpected);

    verifyElementsForNotToContain(actual);

    verify(
        unexpected.stream().noneMatch(actual::contains),
        "does not contain " + unexpected,
        actualValue.actual(),
        "The response body contained an unexpected element.");
  }

  private static void verifyElementsForNotToContain(Collection<?> actual) {
    if (actual.isEmpty()) {
      throw new InvalidAssertionException(
          System.lineSeparator()
              + """
              `notToContain()` was called for an empty response collection.

              Reason:
              Checking whether an empty response collection does not contain an element
              is always true. This creates a false-positive "green test" that does not verify
              the provided unexpected value.

              How to fix:
              -> If the response collection must contain elements, assert this first with:
                 isNotEmpty()

              -> If an empty response collection is expected, use:
                 isEmpty()
              """
                  .strip());
    }
  }

  @Since("2.1.0")
  public void toContainExactlyInAnyOrder(AssertValue<?, ? extends Collection<?>> expectedValue) {
    var expected = expectedValue.normalizedValue();
    var actual = normalizedCollection("contains in any order " + expected);

    verify(
        occurrences(actual).equals(occurrences(expected)),
        "contains in any order " + expected,
        actualValue.actual(),
        "The response body contained different elements.");
  }

  @Since("2.1.0")
  public <T> void toMatchAll(AssertValue<Predicate<T>[], Predicate<T>[]> conditions) {

    var expected = "matches all conditions";
    var actual = requireActual(expected);
    var values = valuesOf(actual);

    verifyElementsForMatchAll(values);

    var matches =
        values.stream()
            .allMatch(
                value ->
                    Arrays.stream(conditions.value())
                        .allMatch(condition -> condition.test(cast(value))));

    verify(matches, expected, actual, "At least one value did not match every condition.");
  }

  private static void verifyElementsForMatchAll(List<?> values) {
    if (values.isEmpty()) {
      throw new InvalidAssertionException(
          System.lineSeparator()
              + """
              `toMatchAll()` was called for an empty response collection.

              Reason:
              Checking whether all elements of an empty response collection match the conditions
              is always true. This creates a false-positive "green test" that does not verify
              the provided conditions.

              How to fix:
              -> If the response collection must contain elements, assert this first with:
                 isNotEmpty().toMatchAll(...);

              -> If an empty response collection is expected, use:
                 isEmpty()
              """
                  .strip());
    }
  }

  @Since("2.1.0")
  public <T> void toMatchAny(AssertValue<Predicate<T>[], Predicate<T>[]> conditions) {
    var expected = "matches any conditions";
    var actual = requireActual(expected);
    var matches =
        valuesOf(actual).stream()
            .anyMatch(
                value -> Arrays.stream(conditions.value()).anyMatch(it -> it.test(cast(value))));

    verify(matches, expected, actual, "No value matched any condition.");
  }

  @Since("2.1.0")
  public <T> void toMatchNone(AssertValue<Predicate<T>[], Predicate<T>[]> conditions) {
    var expected = "matches no conditions";
    var actual = requireActual(expected);
    var matches =
        valuesOf(actual).stream()
            .noneMatch(
                value -> Arrays.stream(conditions.value()).anyMatch(it -> it.test(cast(value))));

    verify(matches, expected, actual, "At least one value matched a condition.");
  }

  @Since("2.1.0")
  public void toContainKey(AssertValue<?, ?> expectedKey) {
    var expected = expectedKey.normalizedValue();
    var actual = normalizedMap("contains key " + expectedKey.value());

    verify(
        actual.containsKey(expected),
        expectedKey.value(),
        actualValue.actual(),
        "The response headers did not contain the expected key.");
  }

  @Since("2.1.0")
  public void notToContainKey(AssertValue<?, ?> unexpectedKey) {
    var unexpected = unexpectedKey.normalizedValue();
    var actual = normalizedMap("does not contain key " + unexpectedKey.value());

    verify(
        !actual.containsKey(unexpected),
        "does not contain key " + unexpectedKey.value(),
        actualValue.actual(),
        "The response headers contained the unexpected key.");
  }

  @Since("2.1.0")
  public void toContainEntry(AssertValue<?, ?> expectedKey, AssertValue<?, ?> expectedValue) {

    var key = expectedKey.normalizedValue();
    var expected = expectedValue.normalizedValue();
    var actual = normalizedMap("contains entry " + expectedKey.value());

    verify(
        actual.containsKey(key),
        expectedKey.value(),
        actualValue.actual(),
        "The response headers did not contain the expected key.");

    var actualEntryValue = actual.get(key);

    verify(
        actualEntryValue instanceof Collection<?> actualValues
            && actualValues.stream()
                .anyMatch(actualValue -> Objects.deepEquals(actualValue, expected)),
        expectedValue.value(),
        actualEntryValue,
        "The response header did not contain the expected value.");
  }

  @Since("2.1.0")
  public void toContainEntryExactly(
      AssertValue<?, ?> expectedKey, AssertValue<?, ? extends Collection<?>> expectedValues) {

    var key = expectedKey.normalizedValue();
    var expected = expectedValues.normalizedValue();

    verifyValuesForToContainEntryExactly(expected);

    var actual = normalizedMap("contains entry exactly " + expectedKey.value());

    verify(
        actual.containsKey(key),
        expectedKey.value(),
        actualValue.actual(),
        "The response headers did not contain the expected key.");

    var actualEntryValue = actual.get(key);

    verify(
        actualEntryValue instanceof Collection<?> actualValues
            && occurrences(actualValues).equals(occurrences(expected)),
        expectedValues.value(),
        actualEntryValue,
        "The response header contained different values.");
  }

  @Since("2.1.0")
  public void toHaveStatus(AssertValue<?, ?> expectedStatus) {
    var actualStatus = actualValue.normalizedValue();

    verify(
        Objects.equals(actualStatus, expectedStatus.normalizedValue()),
        expectedStatus,
        actualStatus,
        "The response status differed from the expected status.");
  }

  @Since("2.1.0")
  public void toBeInRange(
      AssertValue<?, Integer> expectedMinimum, AssertValue<?, Integer> expectedMaximum) {

    var minimum = expectedMinimum.normalizedValue();
    var maximum = expectedMaximum.normalizedValue();

    verifyRange(minimum, maximum);

    var expected = "in range [" + minimum + ", " + maximum + "]";
    var actual = requireActual(expected);
    var normalizedActual = actualValue.normalizedValue();

    verify(
        normalizedActual instanceof Integer actualStatus
            && actualStatus >= minimum
            && actualStatus <= maximum,
        expected,
        actual,
        "The response status was outside the expected range.");
  }

  private static void verifyRange(int minimum, int maximum) {
    if (minimum > maximum) {
      throw new InvalidAssertionException(
          System.lineSeparator()
              + """
              `isInRange()` was called with an invalid range.

              Reason:
              The minimum value must not be greater than the maximum value.

              Actual range:
              -> minimum: %d
              -> maximum: %d

              How to fix:
              -> Provide a minimum value that is less than or equal to the maximum value.
              """
                  .formatted(minimum, maximum)
                  .strip());
    }
  }

  private static void verifyValuesForToContainEntryExactly(Collection<?> expectedValues) {

    if (expectedValues.isEmpty()) {
      throw new InvalidAssertionException(
          System.lineSeparator()
              + """
              `toContainEntryExactly()` was called with an empty collection.

              Reason:
              An exact header entry assertion requires at least one expected value.
              Without an expected value, the assertion does not verify a header value.

              How to fix:
              -> Provide at least one expected header value.
              """
                  .strip());
    }
  }

  private Map<?, ?> normalizedMap(String expected) {
    requireActual(expected);
    return (Map<?, ?>) actualValue.normalizedValue();
  }

  private A requireActual(Object expected) {
    var actual = actualValue.actual();
    verify(actual != null, expected, null, RESPONSE_BODY_ABSENT);
    return actual;
  }

  private Collection<?> normalizedCollection(String expected) {
    requireActual(expected);
    return (Collection<?>) actualValue.normalizedValue();
  }

  private void verify(boolean matches, Object expected, Object actual, String reason) {
    if (!matches) {
      throw new AssertionError(failureMessage(expected, actual, reason));
    }
  }

  private String failureMessage(Object expected, Object actual, String reason) {
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

  private static int sizeOf(Object value) {
    if (value instanceof CharSequence text) {
      return text.length();
    }

    if (value instanceof Collection<?> collection) {
      return collection.size();
    }

    if (value instanceof Map<?, ?> map) {
      return map.size();
    }

    return Array.getLength(value);
  }

  private static Map<Object, Integer> occurrences(Collection<?> values) {
    Map<Object, Integer> occurrences = new HashMap<>();
    values.forEach(value -> occurrences.merge(value, 1, Integer::sum));
    return occurrences;
  }

  private static List<?> valuesOf(Object actual) {
    if (actual instanceof Collection<?> collection) {
      return new ArrayList<>(collection);
    }

    return List.of(actual);
  }

  @SuppressWarnings("unchecked")
  private static <T> T cast(Object value) {
    return (T) value;
  }
}
