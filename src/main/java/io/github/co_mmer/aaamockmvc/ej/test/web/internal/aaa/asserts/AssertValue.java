package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer.normalizeCollection;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer.normalizeMap;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer.normalizeObject;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.DomainValidation;
import java.text.Normalizer.Form;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import org.springframework.http.HttpStatus;

@Since("2.1.0")
public final class AssertValue<T, N> {

  private static final Form NORMALIZATION_FORM = Form.NFC;

  private static final String NULL_EXPECTED_BOOLEAN_MESSAGE = "Expected boolean must not be null";
  private static final String NULL_EXPECTED_BYTES_MESSAGE = "Expected byte must not be null";

  private static final String NULL_EXPECTED_RESPONSE_MESSAGE = "Expected response must not be null";

  private static final String NULL_EXPECTED_STRING_MESSAGE = "Expected string must not be null";

  private static final String NULL_EXPECTED_COLLECTION_MESSAGE =
      "Expected collection must not be null";

  private static final String NULL_EXPECTED_ELEMENTS_MESSAGE = "Expected elements must not be null";

  private static final String NULL_EXPECTED_ELEMENT_MESSAGE =
      "Expected element at position %d must not be null";

  private static final String NULL_UNEXPECTED_ELEMENTS_MESSAGE =
      "Unexpected elements must not be null";

  private static final String NULL_UNEXPECTED_ELEMENT_MESSAGE =
      "Unexpected element at position %d must not be null";

  private static final String NULL_EXPECTED_MAP_MESSAGE = "Expected map must not be null";

  private static final String NULL_EXPECTED_STATUS_MESSAGE =
      "Expected HTTP status must not be null";

  private static final String NULL_EXPECTED_HEADER_NAME_MESSAGE =
      "Expected header name must not be null";

  private static final String NULL_UNEXPECTED_HEADER_NAME_MESSAGE =
      "Unexpected header name must not be null";

  private static final String NULL_EXPECTED_HEADER_VALUE_MESSAGE =
      "Expected header value must not be null";

  private static final String NULL_EXPECTED_HEADER_VALUES_MESSAGE =
      "Expected header values must not be null";

  private static final String NULL_EXPECTED_HEADER_VALUE_AT_POSITION_MESSAGE =
      "Expected header value at position %d must not be null";

  private static final String NULL_MATCH_CONDITIONS_MESSAGE = "Match conditions must not be null";

  private static final String NULL_MATCH_CONDITION_AT_POSITION_MESSAGE =
      "Match condition at position %d must not be null";
  
  private static final String ACTUAL_COLLECTION_TYPE_MESSAGE = "Actual value must be a collection";

  private static final String ACTUAL_ELEMENTS_TYPE_MESSAGE =
      "Actual value must be a collection or an object array";

  private static final String ACTUAL_MAP_TYPE_MESSAGE = "Actual value must be a map";

  private final T value;

  private final N normalizedValue;

  private final Function<Object, N> actualNormalizer;

  private AssertValue(T value, N normalizedValue, Function<Object, N> actualNormalizer) {
    this.value = value;
    this.normalizedValue = normalizedValue;
    this.actualNormalizer = actualNormalizer;
  }

  @Since("2.1.0")
  public static AssertValue<Boolean, Boolean> expectedBoolean(Boolean value) {
    return requireNonNull(value, NULL_EXPECTED_BOOLEAN_MESSAGE);
  }

  public static AssertValue<byte[], byte[]> expectedBytes(byte[] value) {
    var snapshot = value.clone();
    return requireNonNull(snapshot, NULL_EXPECTED_BYTES_MESSAGE);
  }

  @Since("2.1.0")
  public static <T> AssertValue<T, String> expectedResponse(T value) {
    return requireNormalizedObject(value, NULL_EXPECTED_RESPONSE_MESSAGE);
  }

  @Since("2.1.0")
  public static AssertValue<String, String> expectedString(String value) {
    return requireNormalizedObject(value, NULL_EXPECTED_STRING_MESSAGE);
  }

  @Since("2.1.0")
  public static <E> AssertValue<Collection<E>, List<String>> expectedCollection(
      Collection<E> value) {

    return requireNormalizedCollection(value, NULL_EXPECTED_COLLECTION_MESSAGE);
  }

  @SafeVarargs
  @Since("2.1.0")
  public static <E> AssertValue<E[], List<String>> expectedElements(E... values) {
    return requireNormalizedElements(
        values, NULL_EXPECTED_ELEMENTS_MESSAGE, NULL_EXPECTED_ELEMENT_MESSAGE);
  }

  @Since("2.1.0")
  public static <E> AssertValue<Collection<E>, List<String>> unexpectedElements(
      Collection<E> values) {

    return requireNormalizedCollection(values, NULL_UNEXPECTED_ELEMENTS_MESSAGE);
  }

  @SafeVarargs
  @Since("2.1.0")
  public static <E> AssertValue<E[], List<String>> unexpectedElements(E... values) {
    return requireNormalizedElements(
        values, NULL_UNEXPECTED_ELEMENTS_MESSAGE, NULL_UNEXPECTED_ELEMENT_MESSAGE);
  }

  @Since("2.1.0")
  public static <K, V> AssertValue<Map<K, V>, Map<String, String>> expectedMap(Map<K, V> value) {
    return requireNormalizedMap(value);
  }

  @Since("2.1.0")
  public static AssertValue<HttpStatus, Integer> expectedStatus(HttpStatus value) {
    DomainValidation.requireNonNull(value, NULL_EXPECTED_STATUS_MESSAGE);

    return new AssertValue<>(value, value.value(), AssertValue::cast);
  }

  @Since("2.1.0")
  public static AssertValue<Integer, Integer> expectedStatus(Integer value) {
    return requireNonNull(value, NULL_EXPECTED_STATUS_MESSAGE);
  }

  @Since("2.1.0")
  public static AssertValue<String, String> expectedHeaderName(String value) {
    return requireNormalizedObject(value, NULL_EXPECTED_HEADER_NAME_MESSAGE);
  }

  @Since("2.1.0")
  public static AssertValue<String, String> unexpectedHeaderName(String value) {
    return requireNormalizedObject(value, NULL_UNEXPECTED_HEADER_NAME_MESSAGE);
  }

  @Since("2.1.0")
  public static AssertValue<String, String> expectedHeaderValue(String value) {
    return requireNormalizedObject(value, NULL_EXPECTED_HEADER_VALUE_MESSAGE);
  }

  @Since("2.1.0")
  public static AssertValue<String[], List<String>> expectedHeaderValues(String... values) {

    return requireNormalizedElements(
        values,
        NULL_EXPECTED_HEADER_VALUES_MESSAGE,
        NULL_EXPECTED_HEADER_VALUE_AT_POSITION_MESSAGE);
  }

  @SafeVarargs
  @Since("2.1.0")
  public static <T> AssertValue<Predicate<T>[], Predicate<T>[]> matchConditions(
      Predicate<T>... conditions) {

    return requireElements(conditions);
  }

  @Since("2.1.0")
  public T value() {
    return value;
  }

  @Since("2.1.0")
  public N normalizedValue() {
    return normalizedValue;
  }

  private static <T> AssertValue<T, T> requireNonNull(T value, String message) {

    DomainValidation.requireNonNull(value, message);

    return new AssertValue<>(value, value, AssertValue::cast);
  }

  private static <T> AssertValue<T, String> requireNormalizedObject(
      T value, String nullValueMessage) {

    DomainValidation.requireNonNull(value, nullValueMessage);

    return new AssertValue<>(
        value,
        normalizeObject(value, NORMALIZATION_FORM),
        actual -> normalizeObject(actual, NORMALIZATION_FORM));
  }

  private static <E> AssertValue<Collection<E>, List<String>> requireNormalizedCollection(
      Collection<E> value, String nullValueMessage) {

    DomainValidation.requireNonNull(value, nullValueMessage);

    return new AssertValue<>(
        value,
        normalizeCollection(value, NORMALIZATION_FORM),
        actual -> normalizeCollection(requireCollection(actual), NORMALIZATION_FORM));
  }

  private static <E> AssertValue<E[], List<String>> requireNormalizedElements(
      E[] values, String nullValuesMessage, String nullElementMessage) {

    requireElementsNotNull(values, nullValuesMessage, nullElementMessage);

    return new AssertValue<>(
        values, normalizeArray(values), AssertValue::normalizeCollectionOrArray);
  }

  private static <K, V> AssertValue<Map<K, V>, Map<String, String>> requireNormalizedMap(
      Map<K, V> value) {

    DomainValidation.requireNonNull(value, AssertValue.NULL_EXPECTED_MAP_MESSAGE);

    return new AssertValue<>(
        value, normalizeMapSnapshot(value), actual -> normalizeMapSnapshot(requireMap(actual)));
  }

  private static <T> AssertValue<T[], T[]> requireElements(T[] values) {

    requireElementsNotNull(
        values,
        AssertValue.NULL_MATCH_CONDITIONS_MESSAGE,
        AssertValue.NULL_MATCH_CONDITION_AT_POSITION_MESSAGE);

    return new AssertValue<>(values, values, AssertValue::cast);
  }

  private static void requireElementsNotNull(
      Object[] values, String nullValuesMessage, String nullElementMessage) {

    DomainValidation.requireNonNull(values, nullValuesMessage);

    for (var i = 0; i < values.length; i++) {
      DomainValidation.requireNonNull(values[i], nullElementMessage.formatted(i + 1));
    }
  }

  private static Collection<?> requireCollection(Object actual) {
    if (actual instanceof Collection<?> collection) {
      return collection;
    }

    throw new IllegalArgumentException(ACTUAL_COLLECTION_TYPE_MESSAGE);
  }

  private static Map<?, ?> requireMap(Object actual) {
    if (actual instanceof Map<?, ?> map) {
      return map;
    }

    throw new IllegalArgumentException(ACTUAL_MAP_TYPE_MESSAGE);
  }

  private static List<String> normalizeCollectionOrArray(Object actual) {
    if (actual instanceof Collection<?> collection) {
      return normalizeCollection(collection, NORMALIZATION_FORM);
    }

    if (actual instanceof Object[] array) {
      return normalizeArray(array);
    }

    throw new IllegalArgumentException(ACTUAL_ELEMENTS_TYPE_MESSAGE);
  }

  private static List<String> normalizeArray(Object[] values) {
    return Arrays.stream(values).map(value -> normalizeObject(value, NORMALIZATION_FORM)).toList();
  }

  private static Map<String, String> normalizeMapSnapshot(Map<?, ?> value) {
    var normalized = normalizeMap(value, NORMALIZATION_FORM);

    return Collections.unmodifiableMap(new LinkedHashMap<>(normalized));
  }

  @SuppressWarnings("unchecked")
  private static <T> T cast(Object value) {
    return (T) value;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    return other instanceof AssertValue<?, ?> target
        && normalizedValue.equals(target.normalizedValue);
  }

  @Override
  public int hashCode() {
    return normalizedValue.hashCode();
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
