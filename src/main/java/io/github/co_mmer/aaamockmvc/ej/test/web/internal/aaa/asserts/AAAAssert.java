package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.InvalidAssertionException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertOperand;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue;
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
import java.util.function.Predicate;

@Since("2.1.0")
public final class AAAAssert<A, N> {

  private final TestStepDto step;
  private final AssertOperand<A, N> actualValue;

  private AAAAssert(TestStepDto step, AssertOperand<A, N> actualValue) {
    this.step = step;
    this.actualValue = actualValue;
  }

  private static InvalidAssertionException invalidAssertion(String message) {
    return new InvalidAssertionException(System.lineSeparator() + message.strip());
  }

  @Since("2.1.0")
  public static <A, N> AAAAssert<A, N> expect(TestStepDto step, AssertOperand<A, N> actualValue) {
    return new AAAAssert<>(step, actualValue);
  }

  @Since("2.1.0")
  public void toBePresent() {
    verify(
        actualValue.actual() != null,
        AAAAssertDetail.bodyAbsent(step, "not null", actualValue.actual()));
  }

  @Since("2.1.0")
  public void toBeAbsent() {
    verify(
        actualValue.actual() == null,
        AAAAssertDetail.bodyPresent(step, "null", actualValue.actual()));
  }

  @Since("2.1.0")
  public void toBeEmpty() {
    var actual = requireActual("empty");
    verify(sizeOf(actual) == 0, AAAAssertDetail.bodyNotEmpty(step, "empty", actual));
  }

  @Since("2.1.0")
  public void notToBeEmpty() {
    var actual = requireActual("not empty");
    verify(sizeOf(actual) > 0, AAAAssertDetail.bodyEmpty(step, "not empty", actual));
  }

  @Since("2.1.0")
  public void toHaveSize(AssertValue<?, Integer> expectedSize) {
    verifyDimension("size", expectedSize);
  }

  @Since("2.1.0")
  public void toHaveLength(AssertValue<?, Integer> expectedLength) {
    verifyDimension("length", expectedLength);
  }

  private void verifyDimension(String dimension, AssertValue<?, Integer> expectedValue) {
    var expected = dimension + " " + expectedValue.normalizedValue();
    var actual = requireActual(expected);
    var actualDimension = sizeOf(actual);

    verify(
        actualDimension == expectedValue.normalizedValue(),
        AAAAssertDetail.dimensionDiffered(
            step, expected, dimension + " " + actualDimension, dimension));
  }

  @Since("2.1.0")
  public void toEqual(AssertValue<?, ?> expectedValue) {
    var actual = requireActual(expectedValue.value());

    verify(
        Objects.deepEquals(actualValue.normalizedValue(), expectedValue.normalizedValue()),
        AAAAssertDetail.valueDiffered(step, expectedValue.value(), actual));
  }

  @Since("2.1.0")
  public AAAAssert<A, N> toHaveSameTypeAndValueAs(AssertValue<?, ?> expectedValue) {
    var actual = requireActual(expectedValue.value());

    verify(
        actual.getClass().equals(expectedValue.value().getClass()),
        AAAAssertDetail.typeDiffered(step, expectedValue.value(), actual));

    return this;
  }

  @Since("2.1.0")
  public void toContain(AssertValue<?, ? extends Collection<?>> expectedValue) {
    var expected = expectedValue.normalizedValue();
    var actual = normalizedCollection("contains " + expected);

    verifyElementsForToContain(expected);

    verify(
        actual.containsAll(expected),
        AAAAssertDetail.elementsMissing(step, "contains " + expected, actualValue.actual()));
  }

  private static void verifyElementsForToContain(Collection<?> expected) {
    if (expected.isEmpty()) {
      throw invalidAssertion(
          """
              `toContain()` was called with an empty collection.

              Reason:
              Checking whether a collection contains an empty collection is always true.
              This creates a false-positive "green test" that does not verify production behavior.

              How to fix:
              -> To verify that the collection is empty, use:
                 isEmpty()

              -> To verify the number of elements, use:
                 hasSize(expectedSize)
              """);
    }
  }

  @Since("2.1.0")
  public void notToContain(AssertValue<?, ? extends Collection<?>> unexpectedValue) {
    var unexpected = unexpectedValue.normalizedValue();
    var actual = normalizedCollection("does not contain " + unexpected);

    verifyElementsForNotToContain(actual);

    verify(
        unexpected.stream().noneMatch(actual::contains),
        AAAAssertDetail.unexpectedElement(
            step, "does not contain " + unexpected, actualValue.actual()));
  }

  private static void verifyElementsForNotToContain(Collection<?> actual) {
    if (actual.isEmpty()) {
      throw invalidAssertion(
          """
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
              """);
    }
  }

  @Since("2.1.0")
  public void toContainExactlyInAnyOrder(AssertValue<?, ? extends Collection<?>> expectedValue) {
    var expected = expectedValue.normalizedValue();
    var actual = normalizedCollection("contains in any order " + expected);

    verify(
        occurrences(actual).equals(occurrences(expected)),
        AAAAssertDetail.elementsDiffered(
            step, "contains in any order " + expected, actualValue.actual()));
  }

  @Since("2.1.0")
  public <T> void toMatchAll(AssertValue<Predicate<T>[], Predicate<T>[]> conditions) {
    var expected = "matches all conditions";
    var actual = requireActual(expected);
    var values = valuesOf(actual);

    verifyElementsForMatchAll(values);

    var matches = values.stream().allMatch(value -> matchesAll(value, conditions));
    verify(matches, AAAAssertDetail.notAllMatched(step, expected, actual));
  }

  private static <T> boolean matchesAll(
      Object value, AssertValue<Predicate<T>[], Predicate<T>[]> conditions) {

    return Arrays.stream(conditions.value()).allMatch(condition -> condition.test(cast(value)));
  }

  private static <T> boolean matchesAny(
      Object value, AssertValue<Predicate<T>[], Predicate<T>[]> conditions) {

    return Arrays.stream(conditions.value()).anyMatch(condition -> condition.test(cast(value)));
  }

  private static void verifyElementsForMatchAll(List<?> values) {
    if (values.isEmpty()) {
      throw invalidAssertion(
          """
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
              """);
    }
  }

  @Since("2.1.0")
  public <T> void toMatchAny(AssertValue<Predicate<T>[], Predicate<T>[]> conditions) {
    var expected = "matches any conditions";
    var actual = requireActual(expected);
    var matches = valuesOf(actual).stream().anyMatch(value -> matchesAny(value, conditions));

    verify(matches, AAAAssertDetail.noneMatched(step, expected, actual));
  }

  @Since("2.1.0")
  public <T> void toMatchNone(AssertValue<Predicate<T>[], Predicate<T>[]> conditions) {
    var expected = "matches no conditions";
    var actual = requireActual(expected);
    var matches = valuesOf(actual).stream().noneMatch(value -> matchesAny(value, conditions));

    verify(matches, AAAAssertDetail.conditionMatched(step, expected, actual));
  }

  @Since("2.1.0")
  public void toContainKey(AssertValue<?, ?> expectedKey) {
    var expected = expectedKey.normalizedValue();
    var actual = normalizedMap("contains key " + expectedKey.value());

    verify(
        actual.containsKey(expected),
        AAAAssertDetail.headerKeyMissing(step, expectedKey.value(), actualValue.actual()));
  }

  @Since("2.1.0")
  public void notToContainKey(AssertValue<?, ?> unexpectedKey) {
    var unexpected = unexpectedKey.normalizedValue();
    var actual = normalizedMap("does not contain key " + unexpectedKey.value());

    verify(
        !actual.containsKey(unexpected),
        AAAAssertDetail.unexpectedHeaderKey(
            step, "does not contain key " + unexpectedKey.value(), actualValue.actual()));
  }

  @Since("2.1.0")
  public void toContainEntry(AssertValue<?, ?> expectedKey, AssertValue<?, ?> expectedValue) {
    var key = expectedKey.normalizedValue();
    var expected = expectedValue.normalizedValue();
    var actual =
        requireMapContainingKey(key, expectedKey.value(), "contains entry " + expectedKey.value());

    var actualEntryValue = actual.get(key);

    verify(
        actualEntryValue instanceof Collection<?> actualValues
            && actualValues.stream().anyMatch(value -> Objects.deepEquals(value, expected)),
        AAAAssertDetail.headerValueMissing(step, expectedValue.value(), actualEntryValue));
  }

  private Map<?, ?> requireMapContainingKey(
      Object normalizedKey, Object displayedKey, String expectation) {

    var actual = normalizedMap(expectation);

    verify(
        actual.containsKey(normalizedKey),
        AAAAssertDetail.headerKeyMissing(step, displayedKey, actualValue.actual()));

    return actual;
  }

  @Since("2.1.0")
  public void toContainEntryExactly(
      AssertValue<?, ?> expectedKey, AssertValue<?, ? extends Collection<?>> expectedValues) {

    var key = expectedKey.normalizedValue();
    var expected = expectedValues.normalizedValue();

    verifyValuesForToContainEntryExactly(expected);

    var actual =
        requireMapContainingKey(
            key, expectedKey.value(), "contains entry exactly " + expectedKey.value());

    var actualEntryValue = actual.get(key);

    verify(
        actualEntryValue instanceof Collection<?> actualValues
            && occurrences(actualValues).equals(occurrences(expected)),
        AAAAssertDetail.headerValuesDiffered(step, expectedValues.value(), actualEntryValue));
  }

  @Since("2.1.0")
  public void toHaveStatus(AssertValue<?, ?> expectedStatus) {
    var actualStatus = actualValue.normalizedValue();

    verify(
        Objects.equals(actualStatus, expectedStatus.normalizedValue()),
        AAAAssertDetail.statusDiffered(step, expectedStatus, actualStatus));
  }

  @Since("2.1.0")
  public void toBeInRange(
      AssertValue<?, Integer> expectedMinimum, AssertValue<?, Integer> expectedMaximum) {

    var minimum = expectedMinimum.normalizedValue();
    var maximum = expectedMaximum.normalizedValue();

    verifyRange(minimum, maximum);

    var expected = "in range [" + minimum + ", " + maximum + "]";
    var actual = requireActual(expected);

    verify(
        actualValue.normalizedValue() instanceof Integer actualStatus
            && actualStatus >= minimum
            && actualStatus <= maximum,
        AAAAssertDetail.statusOutOfRange(step, expected, actual));
  }

  private static void verifyRange(int minimum, int maximum) {
    if (minimum > maximum) {
      throw invalidAssertion(
          """
              `isInRange()` was called with an invalid range.

              Reason:
              The minimum value must not be greater than the maximum value.

              Actual range:
              -> minimum: %d
              -> maximum: %d

              How to fix:
              -> Provide a minimum value that is less than or equal to the maximum value.
              """
              .formatted(minimum, maximum));
    }
  }

  private static void verifyValuesForToContainEntryExactly(Collection<?> expectedValues) {
    if (expectedValues.isEmpty()) {
      throw invalidAssertion(
          """
              `toContainEntryExactly()` was called with an empty collection.

              Reason:
              An exact header entry assertion requires at least one expected value.
              Without an expected value, the assertion does not verify a header value.

              How to fix:
              -> Provide at least one expected header value.
              """);
    }
  }

  private Map<?, ?> normalizedMap(String expected) {
    requireActual(expected);
    return (Map<?, ?>) actualValue.normalizedValue();
  }

  private A requireActual(Object expected) {
    var actual = actualValue.actual();
    verify(actual != null, AAAAssertDetail.bodyAbsent(step, expected, null));
    return actual;
  }

  private Collection<?> normalizedCollection(String expected) {
    requireActual(expected);
    return (Collection<?>) actualValue.normalizedValue();
  }

  private void verify(boolean matches, AAAAssertDetail detail) {
    if (!matches) {
      throw new AssertionError(detail.formatMessage());
    }
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
