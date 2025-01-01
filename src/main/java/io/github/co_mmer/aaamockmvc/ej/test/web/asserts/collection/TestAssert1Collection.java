package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import java.util.Collection;
import java.util.function.Predicate;
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
   * Asserts that the collection in the HTTP response contains the expected elements.
   *
   * <p>Both collections are normalized before comparison to ensure consistent results.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass the class of the objects in the collection (must not be {@code null})
   * @param expectedElements the collection of expected elements (must not be {@code null})
   * @param <T> the type of the objects in the collection
   * @return the current instance of {@code TestAssertCollection} for further assertions
   * @throws AssertionError if the collection does not contain the expected elements
   * @since 1.4.0
   */
  // @Override
  <T> TestAssertLCollection assertCollectionContains(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> expectedElements);

  /**
   * Asserts that the collection in the HTTP response contains the specified elements.
   *
   * <p>This method provides a varargs overload for specifying the expected elements directly, which
   * are converted into a collection and passed to the main {@code assertCollectionContains} method.
   *
   * <p>Both the actual and expected collections are normalized before comparison to ensure
   * consistent results.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass the class of the objects in the collection (must not be {@code null})
   * @param expectedElements the elements expected to be present in the collection (must not be
   *     {@code null})
   * @param <T> the type of the objects in the collection
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if the collection does not contain the specified elements
   * @since 1.4.0
   */
  <T> TestAssertLCollection assertCollectionContains(
      @NonNull Class<T> expectedClass, @NonNull T... expectedElements);

  /**
   * Asserts that the collection in the HTTP response does not contain the specified elements.
   *
   * <p>Both the actual and expected collections are normalized before comparison to ensure
   * consistent results.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass the class of the objects in the collection (must not be {@code null})
   * @param unexpectedElements the elements that must not be present in the collection (must not be
   *     {@code null})
   * @param <T> the type of the objects in the collection
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if the collection contains any of the specified elements
   * @since 1.4.0
   */
  <T> TestAssertLCollection assertCollectionNotContains(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> unexpectedElements);

  /**
   * Asserts that the collection in the HTTP response does not contain the specified elements.
   *
   * <p>This method provides a varargs overload for specifying the unexpected elements directly,
   * which are converted into a collection and passed to the main {@code
   * assertCollectionNotContains} method.
   *
   * <p>Both the actual and unexpected collections are normalized before comparison to ensure
   * consistent results.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass the class of the objects in the collection (must not be {@code null})
   * @param unexpectedElements the elements that must not be present in the collection (must not be
   *     {@code null})
   * @param <T> the type of the objects in the collection
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if the collection contains any of the specified elements
   * @since 1.4.0
   */
  <T> TestAssertLCollection assertCollectionNotContains(
      @NonNull Class<T> expectedClass, @NonNull T... unexpectedElements);

  /**
   * Asserts that all elements in the collection in the HTTP response match the specified condition.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass the class of the objects in the collection (must not be {@code null})
   * @param condition the condition that all elements must match (must not be {@code null})
   * @param <T> the type of the objects in the collection
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if any element in the collection does not match the condition
   * @since 1.4.0
   */
  <T> TestAssertLCollection assertCollectionMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition);

  /**
   * Asserts that at least one element in the collection in the HTTP response matches the specified
   * condition.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass the class of the objects in the collection (must not be {@code null})
   * @param condition the condition that at least one element must match (must not be {@code null})
   * @param <T> the type of the objects in the collection
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if no element in the collection matches the condition
   * @since 1.4.0
   */
  <T> TestAssertLCollection assertCollectionMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition);

  /**
   * Asserts that no elements in the collection in the HTTP response match the specified condition.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass the class of the objects in the collection (must not be {@code null})
   * @param condition the condition that no element must match (must not be {@code null})
   * @param <T> the type of the objects in the collection
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if any element in the collection matches the condition
   * @since 1.4.0
   */
  <T> TestAssertLCollection assertCollectionMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition);
}
