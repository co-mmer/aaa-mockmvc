package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import java.util.Collection;
import java.util.Map;
import lombok.NonNull;

/**
 * Provides additional assertion methods for validating HTTP response collections and maps.
 *
 * <p>This interface focuses on verifying collection size and content equality, offering extended
 * functionality for detailed collection-based assertions.
 *
 * @since 1.4.0
 */
public interface TestAssert2Collection extends TestAssertLCollection {

  /**
   * Asserts that the size of the collection in the HTTP response matches the given size.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param size the expected size of the collection
   * @return the current instance of {@code TestAssert3Collection} for further assertions
   * @throws AssertionError if the collection size does not match the expected size
   * @since 1.4.0
   */
  TestAssert3Collection assertCollectionSize(int size);

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
