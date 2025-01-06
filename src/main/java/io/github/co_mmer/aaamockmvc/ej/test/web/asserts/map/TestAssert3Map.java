package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import java.util.Map;
import lombok.NonNull;

/**
 * Provides assertion methods for validating HTTP response maps.
 *
 * <ul>
 *   <li>{@link #assertMapEquals(Class, Class, Map)}: Asserts that the map in the HTTP response matches the expected map of key-value pairs.</li>
 *   <li>{@link #assertHead()}: Asserting properties of response headers for HTTP HEAD requests.</li>
 * </ul>
 *
 * @since 1.4.0
 */
public interface TestAssert3Map {

  /**
   * Asserts that the map in the HTTP response matches the expected map of key-value pairs.
   *
   * <p>Both maps are normalized before comparison to ensure consistent results.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param keyClass    the class of the keys in the map (must not be {@code null})
   * @param valueClass  the class of the values in the map (must not be {@code null})
   * @param expectedMap the expected map of key-value pairs (must not be {@code null})
   * @param <K>         the type of the keys in the map
   * @param <V>         the type of the values in the map
   * @return the current instance of {@code TestAssertCollection} for further assertions
   * @throws AssertionError if the maps do not match
   * @since 1.4.0
   */
  <K, V> TestAssertLMap assertMapEquals(
      @NonNull Class<K> keyClass, @NonNull Class<V> valueClass, @NonNull Map<K, V> expectedMap);

  /**
   * Asserts that the HTTP response is valid for a HEAD request.
   *
   * <p>This method returns an instance of {@code TestAssertHead} for asserting the headers of the
   * HTTP response. It allows various validations of response headers, such as checking for the
   * presence or absence of specific headers and comparing header values.
   *
   * @return an instance of {@code TestAssertHead} for further assertions on headers
   * @since 1.4.0
   */
  TestAssertHead assertHead();
}
