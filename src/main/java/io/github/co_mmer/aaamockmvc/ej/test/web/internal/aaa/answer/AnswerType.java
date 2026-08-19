package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.answer;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.AAAType;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.DomainValidation;

@Since("2.1.0")
public final class AnswerType<T> implements AAAType<T> {

  private static final String NULL_RESULT_TYPE_MESSAGE = "Result type must not be null";

  private static final String NULL_COLLECTION_ELEMENT_TYPE_MESSAGE =
      "Collection element type must not be null";

  private static final String NULL_LIST_ELEMENT_TYPE_MESSAGE = "List element type must not be null";

  private static final String NULL_SET_ELEMENT_TYPE_MESSAGE = "Set element type must not be null";

  private static final String NULL_MAP_KEY_TYPE_MESSAGE = "Map key type must not be null";

  private static final String NULL_MAP_VALUE_TYPE_MESSAGE = "Map value type must not be null";

  private final Class<T> type;

  private AnswerType(Class<T> type, String nullMessage) {
    DomainValidation.requireNonNull(type, nullMessage);
    this.type = type;
  }

  @Since("2.1.0")
  public static <T> AnswerType<T> result(Class<T> resultType) {
    return new AnswerType<>(resultType, NULL_RESULT_TYPE_MESSAGE);
  }

  @Since("2.1.0")
  public static <E> AnswerType<E> collectionElement(Class<E> elementType) {
    return new AnswerType<>(elementType, NULL_COLLECTION_ELEMENT_TYPE_MESSAGE);
  }

  @Since("2.1.0")
  public static <E> AnswerType<E> listElement(Class<E> elementType) {
    return new AnswerType<>(elementType, NULL_LIST_ELEMENT_TYPE_MESSAGE);
  }

  @Since("2.1.0")
  public static <E> AnswerType<E> setElement(Class<E> elementType) {
    return new AnswerType<>(elementType, NULL_SET_ELEMENT_TYPE_MESSAGE);
  }

  @Since("2.1.0")
  public static <K> AnswerType<K> mapKey(Class<K> keyType) {
    return new AnswerType<>(keyType, NULL_MAP_KEY_TYPE_MESSAGE);
  }

  @Since("2.1.0")
  public static <V> AnswerType<V> mapValue(Class<V> valueType) {
    return new AnswerType<>(valueType, NULL_MAP_VALUE_TYPE_MESSAGE);
  }

  @Since("2.1.0")
  public Class<T> type() {
    return type;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    return other instanceof AnswerType<?> target && type.equals(target.type);
  }

  @Override
  public int hashCode() {
    return type.hashCode();
  }

  @Override
  public String toString() {
    return type.getTypeName();
  }
}
