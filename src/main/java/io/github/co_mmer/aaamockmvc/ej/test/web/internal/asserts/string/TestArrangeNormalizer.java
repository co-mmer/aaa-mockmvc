package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.string;

import static java.text.Normalizer.normalize;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import java.text.Normalizer.Form;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Since("1.3.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestArrangeNormalizer {

  @Since("1.3.0")
  public static <T> String normalizeObject(@NonNull T actual) {
    return normalize(actual.toString(), Form.NFC);
  }

  @Since("1.4.0")
  public static <T> List<String> normalizeCollection(@NonNull Collection<T> actual) {
    return actual.stream().map(element -> normalizeObject(element.toString())).toList();
  }

  @Since("1.4.0")
  public static <T> List<Object> normalizeAsObjects(@NonNull Collection<T> actual) {
    return actual.stream().map(element -> normalizAsObject(element.toString())).toList();
  }

  private static <T> Object normalizAsObject(T actual) {
    return normalize(actual.toString(), Form.NFC);
  }

  @Since("1.3.0")
  public static <K, V> Map<String, String> normalizeMap(@NonNull Map<K, V> actual) {
    return actual.entrySet().stream()
        .collect(
            Collectors.toMap(
                entry -> normalizeObject(entry.getKey().toString()),
                entry -> normalizeObject(entry.getValue().toString())));
  }
}
