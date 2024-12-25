package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content;

import static java.text.Normalizer.normalize;

import java.text.Normalizer.Form;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Utility class that provides normalization methods for various object types to ensure consistent
 * text representation, particularly useful in test contexts where normalized string comparisons are
 * required.
 *
 * <p>The normalization process uses Unicode Normalization Form C (NFC), which can be beneficial
 * when comparing strings that might have different Unicode representations but should be treated as
 * equivalent.
 *
 * <p>This class supports the normalization of individual objects, lists, sets, and maps, allowing
 * for flexible normalization strategies in testing and validation scenarios.
 *
 * @since 1.3.0
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestArrangeNormalizer {

  /**
   * Normalizes the string representation of a given object to ensure consistent text format.
   *
   * @param actual the object to be normalized (must not be {@code null})
   * @param <T> the type of the object being normalized
   * @return a normalized {@code String} representation of the object
   * @throws NullPointerException if {@code actual} is {@code null}
   * @since 1.3.0
   */
  public static <T> String normalizeObject(@NonNull T actual) {
    return normalize(actual.toString(), Form.NFC);
  }

  /**
   * Normalizes the string representations of elements in the provided list, ensuring all elements
   * are in a consistent format.
   *
   * @param actual the collection of objects to be normalized (must not be {@code null})
   * @param <T> the type of the objects in the list
   * @return a list of normalized {@code String} representations of the objects
   * @throws NullPointerException if {@code actual} is {@code null}
   * @since 1.4.0
   */
  public static <T> List<String> normalizeCollection(@NonNull Collection<T> actual) {
    return actual.stream().map(element -> normalizeObject(element.toString())).toList();
  }

  /**
   * Normalizes the string representations of elements in the provided set, ensuring a consistent
   * text format for each element.
   *
   * @param actual the set of objects to be normalized (must not be {@code null})
   * @param <T> the type of the objects in the set
   * @return a set of normalized {@code String} representations of the objects
   * @throws NullPointerException if {@code actual} is {@code null}
   * @since 1.3.0
   */
  public static <T> Set<String> normalizeSet(@NonNull Set<T> actual) {
    return actual.stream()
        .map(element -> normalizeObject(element.toString()))
        .collect(Collectors.toSet());
  }

  /**
   * Normalizes the keys and values in the provided map, ensuring that both keys and values are in a
   * consistent string format.
   *
   * @param actual the map containing key-value pairs to be normalized (must not be {@code null})
   * @param <K> the type of the keys in the map
   * @param <V> the type of the values in the map
   * @return a map of normalized {@code String} keys and values
   * @throws NullPointerException if {@code actual} is {@code null}
   * @since 1.3.0
   */
  public static <K, V> Map<String, String> normalizeMap(@NonNull Map<K, V> actual) {
    return actual.entrySet().stream()
        .collect(
            Collectors.toMap(
                entry -> normalizeObject(entry.getKey().toString()),
                entry -> normalizeObject(entry.getValue().toString())));
  }
}
