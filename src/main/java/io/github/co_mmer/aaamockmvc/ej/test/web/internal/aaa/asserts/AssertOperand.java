package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer.normalizeCollection;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer.normalizeMap;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer.normalizeMapList;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Note;
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

  private AssertOperand(A actual, Function<? super A, ? extends N> normalizer) {
    this.actualValue = actual;
    this.normalizedValue = normalizer.apply(actual);
  }

  private AssertOperand(A actual, N normalizedValue) {
    this.actualValue = actual;
    this.normalizedValue = normalizedValue;
  }

  @Since("2.1.0")
  public static <E> AssertOperand<Collection<E>, List<String>> collection(
      Collection<? extends E> value) {

    if (value == null) {
      return new AssertOperand<>(null, (List<String>) null);
    }

    Collection<E> snapshot = List.copyOf(value);
    return new AssertOperand<>(snapshot, actual -> List.copyOf(normalizeCollection(actual)));
  }

  @Since("2.1.0")
  public static <E> AssertOperand<List<E>, List<String>> list(List<? extends E> value) {
    if (value == null) {
      return new AssertOperand<>(null, (List<String>) null);
    }

    List<E> snapshot = List.copyOf(value);
    return new AssertOperand<>(snapshot, actual -> List.copyOf(normalizeCollection(actual)));
  }

  @Since("2.1.0")
  public static <E> AssertOperand<Set<E>, Set<String>> set(Set<? extends E> value) {
    if (value == null) {
      return new AssertOperand<>(null, (Set<String>) null);
    }

    Set<E> snapshot = Collections.unmodifiableSet(new LinkedHashSet<E>(value));
    return new AssertOperand<>(
        snapshot,
        actual -> Collections.unmodifiableSet(new LinkedHashSet<>(normalizeCollection(actual))));
  }

  @Since("2.1.0")
  public static AssertOperand<Boolean, Boolean> bool(Boolean value) {
    if (value == null) {
      return new AssertOperand<>(null, (Boolean) null);
    }
    return new AssertOperand<>(value, actual -> actual);
  }

  @Since("2.1.0")
  public static AssertOperand<String, String> string(String value) {
    if (value == null) {
      return new AssertOperand<>(null, (String) null);
    }
    return new AssertOperand<>(value, actual -> actual);
  }

  @Since("2.1.0")
  public static <C> AssertOperand<C, String> clazz(C value) {
    if (value == null) {
      return new AssertOperand<>(null, (String) null);
    }

    return new AssertOperand<>(value, TestArrangeNormalizer::normalizeObject);
  }

  @Since("2.1.0")
  public static AssertOperand<Integer, Integer> integer(Integer value) {
    if (value == null) {
      return new AssertOperand<>(null, (Integer) null);
    }
    return new AssertOperand<>(value, value);
  }

  @Since("2.1.0")
  public static AssertOperand<byte[], byte[]> bytes(byte[] value) {
    if (value == null) {
      return new AssertOperand<>(null, (byte[]) null);
    }
    var snapshot = value.clone();
    return new AssertOperand<>(snapshot, byte[]::clone);
  }

  @Since("2.1.0")
  @Note("2.1.0 value nullable, 2.2.0 notNull")
  public static <K, V> AssertOperand<Map<K, V>, Map<String, String>> map(
      Map<? extends K, ? extends V> value) {

    if (value == null) {
      return new AssertOperand<>(null, (Map<String, String>) null);
    }

    Map<K, V> snapshot = Collections.unmodifiableMap(new LinkedHashMap<>(value));
    return new AssertOperand<>(
        snapshot, actual -> Collections.unmodifiableMap(new LinkedHashMap<>(normalizeMap(actual))));
  }

  @Since("2.1.0")
  public static AssertOperand<Map<String, List<String>>, Map<String, List<String>>> headers(
      Map<String, ? extends List<String>> value) {

    if (value == null) {
      return new AssertOperand<>(null, (Map<String, List<String>>) null);
    }

    Map<String, List<String>> snapshot = new LinkedHashMap<>();
    value.forEach((key, values) -> snapshot.put(key, List.copyOf(values)));
    var immutableSnapshot = Collections.unmodifiableMap(snapshot);

    return new AssertOperand<>(
        immutableSnapshot,
        actual -> Collections.unmodifiableMap(new LinkedHashMap<>(normalizeMapList(actual))));
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
