package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer.normalizeCollection;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer.normalizeMap;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer.normalizeMapList;

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

  private final A actualValue;
  private final N normalizedValue;

  private static <A, N> AssertOperand<A, N> normalize(
      A actual, Function<? super A, ? extends N> normalizer) {

    N normalized = actual == null ? null : normalizer.apply(actual);
    return new AssertOperand<>(actual, normalized);
  }

  private AssertOperand(A actual, N normalizedValue) {
    this.actualValue = actual;
    this.normalizedValue = normalizedValue;
  }

  @Since("2.1.0")
  public static <E> AssertOperand<Collection<E>, List<String>> collection(
      Collection<? extends E> value) {

    Collection<E> snapshot = value == null ? null : List.copyOf(value);
    return normalize(snapshot, actual -> List.copyOf(normalizeCollection(actual)));
  }

  @Since("2.1.0")
  public static <E> AssertOperand<List<E>, List<String>> list(List<? extends E> value) {
    List<E> snapshot = value == null ? null : List.copyOf(value);
    return normalize(snapshot, actual -> List.copyOf(normalizeCollection(actual)));
  }

  @Since("2.1.0")
  public static <E> AssertOperand<Set<E>, Set<String>> set(Set<? extends E> value) {
    Set<E> snapshot =
        value == null ? null : Collections.unmodifiableSet(new LinkedHashSet<E>(value));

    return normalize(
        snapshot,
        actual -> Collections.unmodifiableSet(new LinkedHashSet<>(normalizeCollection(actual))));
  }

  @Since("2.1.0")
  public static AssertOperand<Boolean, Boolean> bool(Boolean value) {
    return new AssertOperand<>(value, value);
  }

  @Since("2.1.0")
  public static AssertOperand<String, String> string(String value) {
    return new AssertOperand<>(value, value);
  }

  @Since("2.1.0")
  public static <C> AssertOperand<C, String> clazz(C value) {
    return normalize(value, TestArrangeNormalizer::normalizeObject);
  }

  @Since("2.1.0")
  public static AssertOperand<Integer, Integer> integer(Integer value) {
    return new AssertOperand<>(value, value);
  }

  @Since("2.1.0")
  public static AssertOperand<byte[], byte[]> bytes(byte[] value) {
    var snapshot = value == null ? null : value.clone();
    return normalize(snapshot, byte[]::clone);
  }

  @Since("2.1.0")
  public static <K, V> AssertOperand<Map<K, V>, Map<String, String>> map(
      Map<? extends K, ? extends V> value) {

    var snapshot =
        value == null ? null : Collections.unmodifiableMap(new LinkedHashMap<K, V>(value));

    return normalize(
        snapshot, actual -> Collections.unmodifiableMap(new LinkedHashMap<>(normalizeMap(actual))));
  }

  @Since("2.1.0")
  public static AssertOperand<Map<String, List<String>>, Map<String, List<String>>> headers(
      Map<String, ? extends List<String>> value) {

    var snapshot = value == null ? null : immutableHeaders(value);
    return normalize(
        snapshot,
        actual -> Collections.unmodifiableMap(new LinkedHashMap<>(normalizeMapList(actual))));
  }

  private static Map<String, List<String>> immutableHeaders(
      Map<String, ? extends List<String>> value) {

    var snapshot = new LinkedHashMap<String, List<String>>();
    value.forEach((key, values) -> snapshot.put(key, List.copyOf(values)));
    return Collections.unmodifiableMap(snapshot);
  }

  @Since("2.1.0")
  public A actual() {
    return this.actualValue;
  }

  @Since("2.1.0")
  public N normalizedValue() {
    return this.normalizedValue;
  }
}
