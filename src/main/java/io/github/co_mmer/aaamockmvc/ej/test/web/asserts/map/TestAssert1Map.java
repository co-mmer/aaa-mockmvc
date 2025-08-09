package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map;

import java.util.Map;
import lombok.NonNull;

/**
 * Provides assertion methods for validating HTTP response maps.
 *
 * <ul>
 *   <li>{@link #assertContentNotEmpty()}: Asserts that the map in the HTTP response is not empty.
 *   <li>{@link #assertContentEmpty()}: Asserts that the map in the HTTP response is empty.
 *   <li>{@link #assertContentSize(int)}: Asserts that the size of the map in the HTTP response
 *       matches the given size.
 *   <li>{@link #assertContentEquals(Class, Class, Map)}: Asserts that the map in the HTTP response
 *       matches the expected map of key-value pairs.
 * </ul>
 *
 * @since 1.4.0
 */
public interface TestAssert1Map {

  /**
   * Asserts that the map in the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssert2Map} for further assertions
   * @throws AssertionError if the response collection is empty or invalid
   * @since 1.4.0
   * @deprecated Use {@link #assertContentNotEmpty()} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  TestAssert2Map assertMapNotEmpty();

  /**
   * Asserts that the map in the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertLMap} for further assertions
   * @throws AssertionError if the response collection is not empty
   * @since 1.4.0
   * @deprecated Use {@link #assertContentEmpty()} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  TestAssertLMap assertMapEmpty();

  /**
   * Asserts that the size of the map in the HTTP response matches the given size.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param size the expected size of the collection
   * @return the current instance of {@code TestAssert3Map} for further assertions
   * @throws AssertionError if the collection size does not match the expected size
   * @since 1.4.0
   * @deprecated Use {@link #assertContentSize(int)} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  TestAssert3Map assertMapSize(int size);

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
   * @return the current instance of {@code TestAssertLMap} for further assertions
   * @throws AssertionError if the maps do not match
   * @since 1.4.0
   * @deprecated Use {@link #assertContentEquals(Class, Class, Map)} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  <K, V> TestAssertLMap assertMapEquals(
      @NonNull Class<K> keyClass, @NonNull Class<V> valueClass, @NonNull Map<K, V> expectedMap);

  /**
   * Asserts that the map in the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the response is empty or invalid
   * @since 1.6.0
   */
  TestAssert2Map assertContentNotEmpty();

  /**
   * Asserts that the map in the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the response is not empty
   * @since 1.6.0
   */
  TestAssertLMap assertContentEmpty();

  /**
   * Asserts that the size of the map in the HTTP response matches the given size.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param size the expected size of the collection
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the response does not match the expected size
   * @since 1.6.0
   */
  TestAssert3Map assertContentSize(int size);

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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the maps do not match
   * @since 1.6.0
   */
  <K, V> TestAssertLMap assertContentEquals(
      @NonNull Class<K> keyClass, @NonNull Class<V> valueClass, @NonNull Map<K, V> expectedMap);
}
