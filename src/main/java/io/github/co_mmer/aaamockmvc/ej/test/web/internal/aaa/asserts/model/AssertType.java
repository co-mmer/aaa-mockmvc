package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.AAAType;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.DomainValidation;

@Since("2.1.0")
public final class AssertType<T> implements AAAType<T> {

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

  private final Class<T> type;

  private AssertType(Class<T> type, String nullMessage) {
    DomainValidation.requireNonNull(type, nullMessage);
    this.type = type;
  }

  @Since("2.1.0")
  public static <T> AssertType<T> resultClass(Class<T> clazz) {
    return new AssertType<>(clazz, NULL_EXPECTED_CLASS_MESSAGE);
  }

  @Since("2.1.0")
  public static <E> AssertType<E> collectionElementClass(Class<E> clazz) {
    return new AssertType<>(clazz, NULL_COLLECTION_ELEMENT_CLASS_MESSAGE);
  }

  @Since("2.1.0")
  public static <E> AssertType<E> listElementClass(Class<E> clazz) {
    return new AssertType<>(clazz, NULL_LIST_ELEMENT_CLASS_MESSAGE);
  }

  @Since("2.1.0")
  public static <E> AssertType<E> setElementClass(Class<E> clazz) {
    return new AssertType<>(clazz, NULL_SET_ELEMENT_CLASS_MESSAGE);
  }

  @Since("2.1.0")
  public static <K> AssertType<K> mapKeyClass(Class<K> keyClass) {
    return new AssertType<>(keyClass, NULL_MAP_KEY_CLASS_MESSAGE);
  }

  @Since("2.1.0")
  public static <V> AssertType<V> mapValueClass(Class<V> valueClass) {
    return new AssertType<>(valueClass, NULL_MAP_VALUE_CLASS_MESSAGE);
  }

  @Override
  @Since("2.1.0")
  public Class<T> type() {
    return type;
  }
}
