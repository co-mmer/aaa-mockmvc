package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import java.util.Collection;
import java.util.Map;
import lombok.NonNull;

/**
 * Provides assertion methods for validating HTTP response collections and maps.
 *
 * <p>This interface allows verifying properties such as emptiness, size, and content equality of
 * collections and maps returned in HTTP responses.
 *
 * @since 1.4.0
 */
public interface TestAssert1Collection {

  /**
   * Asserts that the collection in the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssert2Collection} for further assertions
   * @throws AssertionError if the response collection is empty or invalid
   * @since 1.4.0
   */
  TestAssert2Collection assertCollectionNotEmpty();

  /**
   * Asserts that the collection in the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertCollection} for further assertions
   * @throws AssertionError if the response collection is not empty
   * @since 1.4.0
   */
  TestAssertLCollection assertCollectionEmpty();

  /**
   * Asserts that the content of the HTTP response matches the given collection of objects.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass the class of the objects in the list (must not be {@code null})
   * @param expectedCollection the expected list of objects (must not be {@code null})
   * @param <T> the type of the objects in the expected list
   * @since 1.4.0
   */
  <T> TestAssertLCollection assertCollectionEquals(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> expectedCollection);

  /**
   * Asserts that the collection in the HTTP response matches the expected collection, ignoring
   * order.
   *
   * <p>Both collections are normalized before comparison to ensure consistent results.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass the class of the objects in the collection (must not be {@code null})
   * @param expectedCollection the expected collection of objects (must not be {@code null})
   * @param <T> the type of the objects in the collection
   * @return the current instance of {@code TestAssertCollection} for further assertions
   * @throws AssertionError if the collections do not match
   * @since 1.4.0
   */
  <T> TestAssertLCollection assertCollectionEqualsIgnoreOrder(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> expectedCollection);

  /**
   * Asserts that the map in the HTTP response matches the expected map of key-value pairs.
   *
   * <p>Both maps are normalized before comparison to ensure consistent results.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param keyClass the class of the keys in the map (must not be {@code null})
   * @param valueClass the class of the values in the map (must not be {@code null})
   * @param expectedMap the expected map of key-value pairs (must not be {@code null})
   * @param <K> the type of the keys in the map
   * @param <V> the type of the values in the map
   * @return the current instance of {@code TestAssertCollection} for further assertions
   * @throws AssertionError if the maps do not match
   * @since 1.4.0
   */
  <K, V> TestAssertLCollection assertCollectionEquals(
      @NonNull Class<K> keyClass, @NonNull Class<V> valueClass, @NonNull Map<K, V> expectedMap);
}
