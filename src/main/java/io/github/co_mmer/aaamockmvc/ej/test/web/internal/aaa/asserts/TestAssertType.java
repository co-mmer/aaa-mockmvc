package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.DomainValidation;
import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import org.springframework.http.HttpStatus;

@Since("2.1.0")
public final class TestAssertType<T> {

  private static final String NULL_EXPECTED_BOOLEAN_MESSAGE = "Expected boolean must not be null";

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

  private static final String NULL_EXPECTED_CLASS_MESSAGE = "Expected class must not be null";

  private static final String NULL_COLLECTION_ELEMENT_CLASS_MESSAGE =
      "Expected collection element class must not be null";

  private static final String NULL_LIST_ELEMENT_CLASS_MESSAGE =
      "Expected list element class must not be null";

  private static final String NULL_SET_ELEMENT_CLASS_MESSAGE =
      "Expected set element class must not be null";

  private static final String NULL_MAP_KEY_CLASS_MESSAGE =
      "Expected map key class must not be null";

  private static final String NULL_MAP_VALUE_CLASS_MESSAGE =
      "Expected map value class must not be null";

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

  private final T value;

  private TestAssertType(T value) {
    this.value = value;
  }

  @Since("2.1.0")
  public static TestAssertType<Boolean> expectedBoolean(Boolean value) {
    return requireNonNull(value, NULL_EXPECTED_BOOLEAN_MESSAGE);
  }

  @Since("2.1.0")
  public static <T> TestAssertType<T> expectedResponse(T value) {
    return requireNonNull(value, NULL_EXPECTED_RESPONSE_MESSAGE);
  }

  @Since("2.1.0")
  public static TestAssertType<String> expectedString(String value) {
    return requireNonNull(value, NULL_EXPECTED_STRING_MESSAGE);
  }

  @Since("2.1.0")
  public static <E> TestAssertType<Collection<E>> expectedCollection(Collection<E> value) {
    return requireNonNull(value, NULL_EXPECTED_COLLECTION_MESSAGE);
  }

  @SafeVarargs
  @Since("2.1.0")
  public static <E> TestAssertType<E[]> expectedElements(E... values) {
    return requireElements(values, NULL_EXPECTED_ELEMENTS_MESSAGE, NULL_EXPECTED_ELEMENT_MESSAGE);
  }

  @Since("2.1.0")
  public static <E> TestAssertType<Collection<E>> unexpectedElements(Collection<E> values) {
    return requireNonNull(values, NULL_UNEXPECTED_ELEMENTS_MESSAGE);
  }

  @SafeVarargs
  @Since("2.1.0")
  public static <E> TestAssertType<E[]> unexpectedElements(E... values) {
    return requireElements(
        values, NULL_UNEXPECTED_ELEMENTS_MESSAGE, NULL_UNEXPECTED_ELEMENT_MESSAGE);
  }

  @Since("2.1.0")
  public static <K, V> TestAssertType<Map<K, V>> expectedMap(Map<K, V> value) {
    return requireNonNull(value, NULL_EXPECTED_MAP_MESSAGE);
  }

  @Since("2.1.0")
  public static <T> TestAssertType<Class<T>> expectedClass(Class<T> value) {
    return requireNonNull(value, NULL_EXPECTED_CLASS_MESSAGE);
  }

  @Since("2.1.0")
  public static <E> TestAssertType<Class<E>> collectionElementClass(Class<E> value) {
    return requireNonNull(value, NULL_COLLECTION_ELEMENT_CLASS_MESSAGE);
  }

  @Since("2.1.0")
  public static <E> TestAssertType<Class<E>> listElementClass(Class<E> value) {
    return requireNonNull(value, NULL_LIST_ELEMENT_CLASS_MESSAGE);
  }

  @Since("2.1.0")
  public static <E> TestAssertType<Class<E>> setElementClass(Class<E> value) {
    return requireNonNull(value, NULL_SET_ELEMENT_CLASS_MESSAGE);
  }

  @Since("2.1.0")
  public static <K> TestAssertType<Class<K>> mapKeyClass(Class<K> value) {
    return requireNonNull(value, NULL_MAP_KEY_CLASS_MESSAGE);
  }

  @Since("2.1.0")
  public static <V> TestAssertType<Class<V>> mapValueClass(Class<V> value) {
    return requireNonNull(value, NULL_MAP_VALUE_CLASS_MESSAGE);
  }

  @Since("2.1.0")
  public static TestAssertType<HttpStatus> expectedStatus(HttpStatus value) {
    return requireNonNull(value, NULL_EXPECTED_STATUS_MESSAGE);
  }

  @Since("2.1.0")
  public static TestAssertType<String> expectedHeaderName(String value) {
    return requireNonNull(value, NULL_EXPECTED_HEADER_NAME_MESSAGE);
  }

  @Since("2.1.0")
  public static TestAssertType<String> unexpectedHeaderName(String value) {
    return requireNonNull(value, NULL_UNEXPECTED_HEADER_NAME_MESSAGE);
  }

  @Since("2.1.0")
  public static TestAssertType<String> expectedHeaderValue(String value) {
    return requireNonNull(value, NULL_EXPECTED_HEADER_VALUE_MESSAGE);
  }

  @Since("2.1.0")
  public static TestAssertType<String[]> expectedHeaderValues(String... values) {
    return requireElements(
        values,
        NULL_EXPECTED_HEADER_VALUES_MESSAGE,
        NULL_EXPECTED_HEADER_VALUE_AT_POSITION_MESSAGE);
  }

  @SafeVarargs
  @Since("2.1.0")
  public static <T> TestAssertType<Predicate<T>[]> matchConditions(Predicate<T>... conditions) {
    return requireElements(
        conditions, NULL_MATCH_CONDITIONS_MESSAGE, NULL_MATCH_CONDITION_AT_POSITION_MESSAGE);
  }

  @Since("2.1.0")
  public T value() {
    return value;
  }

  private static <T> TestAssertType<T> requireNonNull(T value, String message) {
    DomainValidation.requireNonNull(value, message);
    return new TestAssertType<>(value);
  }

  private static <T> TestAssertType<T[]> requireElements(
      T[] values, String nullValuesMessage, String nullElementMessage) {

    DomainValidation.requireNonNull(values, nullValuesMessage);

    for (var i = 0; i < values.length; i++) {
      DomainValidation.requireNonNull(values[i], nullElementMessage.formatted(i + 1));
    }

    return new TestAssertType<>(values);
  }
}
