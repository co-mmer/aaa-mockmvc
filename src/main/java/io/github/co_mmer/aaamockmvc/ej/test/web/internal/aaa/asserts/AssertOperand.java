package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer.normalizeCollection;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer.normalizeMap;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Since("2.1.0")
public final class AssertOperand<A, N> {

  private final A actualContent;
  private final N normalizedValue;

  private AssertOperand(A actualContent, Function<? super A, ? extends N> normalizer) {

    this.actualContent = actualContent;
    this.normalizedValue = normalizer.apply(actualContent);
  }

  private AssertOperand(A actualContent, N normalizedValue) {

    this.actualContent = actualContent;
    this.normalizedValue = normalizedValue;
  }

  public static <E> AssertOperand<Collection<E>, List<String>> collection(
      Collection<? extends E> value) {

    if (value == null) {
      return new AssertOperand<>(null, (List<String>) null);
    }
    Collection<E> snapshot = List.copyOf(value);

    return new AssertOperand<>(snapshot, actual -> List.copyOf(normalizeCollection(actual)));
  }

  public static <E> AssertOperand<List<E>, List<String>> list(List<? extends E> value) {

    List<E> snapshot = List.copyOf(value);

    return new AssertOperand<>(snapshot, actual -> List.copyOf(normalizeCollection(actual)));
  }

  public static <E> AssertOperand<Set<E>, Set<String>> set(Set<? extends E> value) {

    Set<E> snapshot = Collections.unmodifiableSet(new LinkedHashSet<E>(value));

    return new AssertOperand<>(
        snapshot,
        actual -> Collections.unmodifiableSet(new LinkedHashSet<>(normalizeCollection(actual))));
  }

  public static AssertOperand<Boolean, Boolean> bool(Boolean value) {

    return new AssertOperand<>(value, actual -> actual);
  }

  public static AssertOperand<String, String> string(String value) {

    return new AssertOperand<>(value, actual -> actual);
  }

  public static <C> AssertOperand<C, String> clazz(C value) {
    if (value == null) {
      return new AssertOperand<>(null, (String) null);
    }

    return new AssertOperand<>(value, TestArrangeNormalizer::normalizeObject);
  }

  public static AssertOperand<byte[], byte[]> bytes(byte[] value) {

    var snapshot = value.clone();

    return new AssertOperand<>(snapshot, byte[]::clone);
  }

  public static <K, V> AssertOperand<Map<K, V>, Map<String, String>> map(
      Map<? extends K, ? extends V> value) {

    if (value == null) {
      return new AssertOperand<>(null, (Map<String, String>) null);
    }

    Map<K, V> snapshot = Collections.unmodifiableMap(new LinkedHashMap<>(value));

    return new AssertOperand<>(
        snapshot, actual -> Collections.unmodifiableMap(new LinkedHashMap<>(normalizeMap(actual))));
  }

  public A actual() {
    return this.actualContent;
  }

  public N normalizedValue() {
    return this.normalizedValue;
  }
}
