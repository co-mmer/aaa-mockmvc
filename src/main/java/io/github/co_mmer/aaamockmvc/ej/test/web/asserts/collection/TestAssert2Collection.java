package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import java.util.Collection;
import java.util.function.Predicate;
import lombok.NonNull;

/**
 * Provides additional assertion methods for validating HTTP response collections and maps.
 *
 * <p>This interface focuses on verifying collection size and content equality, offering extended
 * functionality for detailed collection-based assertions.
 *
 * @since 1.4.0
 */
public interface TestAssert2Collection {

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
  <T> TestAssert3Collection assertCollectionContains(
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
  @SuppressWarnings("unchecked")
  <T> TestAssert3Collection assertCollectionContains(
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
  <T> TestAssert3Collection assertCollectionNotContains(
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
  @SuppressWarnings("unchecked")
  <T> TestAssert3Collection assertCollectionNotContains(
      @NonNull Class<T> expectedClass, @NonNull T... unexpectedElements);

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
  <T> TestAssertLCollection assertCollectionContainsAnyOrder(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> expectedCollection);

  /**
   * Asserts that all elements in the collection in the HTTP response match the specified condition.
   *
   * <p>This method checks whether **all** elements in the collection satisfy the specified
   * condition. If every element matches the condition, the assertion passes. If any element does
   * not match the condition, the assertion fails.
   *
   * <p>The condition is applied to **each element** in the collection, and the method returns the
   * current instance of {@code TestAssertLCollection} for further assertions.
   *
   * @param expectedClass the class of the objects in the collection (must not be {@code null})
   * @param condition the condition that all elements must match (must not be {@code null})
   * @param <T> the type of the objects in the collection
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if any element in the collection does not match the condition
   * @since 1.4.0
   */
  <T> TestAssert4Collection assertCollectionMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition);

  /**
   * Asserts that all elements in the collection in the HTTP response match the specified
   * conditions.
   *
   * <p>This method checks whether **all** elements in the collection satisfy **all** of the
   * specified conditions. If **every element** matches all conditions, the assertion passes. If any
   * element does not match the conditions, the assertion fails.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception. The conditions are applied to **each element** in the collection.
   *
   * @param expectedClass the class of the objects in the collection (must not be {@code null})
   * @param conditions the conditions that the elements must match (must not be {@code null})
   * @param <T> the type of the objects in the collection
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if any element in the collection does not match all the conditions
   * @since 1.4.0
   */
  @SuppressWarnings("unchecked")
  <T> TestAssert4Collection assertCollectionMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions);

  /**
   * Asserts that at least one element in the collection in the HTTP response matches the specified
   * condition.
   *
   * <p>This method checks whether **at least one element** in the collection satisfies the
   * specified condition. If any element matches the condition, the assertion passes. If no element
   * matches the condition, the assertion fails.
   *
   * <p>The condition is applied to **each element** in the collection, and the method returns the
   * current instance of {@code TestAssertLCollection} for further assertions.
   *
   * @param expectedClass the class of the objects in the collection (must not be {@code null})
   * @param condition the condition that at least one element must match (must not be {@code null})
   * @param <T> the type of the objects in the collection
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if no element in the collection matches the condition
   * @since 1.4.0
   */
  <T> TestAssert5Collection assertCollectionMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition);

  /**
   * Asserts that at least one element in the collection in the HTTP response matches the specified
   * conditions.
   *
   * <p>This method checks whether **any one** element in the collection satisfies **all** of the
   * specified conditions. If at least one element matches all conditions, the assertion passes. If
   * no element matches the conditions, the assertion fails.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception. The conditions are applied to **each element** in the collection.
   *
   * @param expectedClass the class of the objects in the collection (must not be {@code null})
   * @param conditions the conditions that the elements must match (must not be {@code null})
   * @param <T> the type of the objects in the collection
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if no element in the collection matches all the conditions
   * @since 1.4.0
   */
  @SuppressWarnings("unchecked")
  <T> TestAssert5Collection assertCollectionMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions);

  /**
   * Asserts that none of the elements in the collection in the HTTP response match the specified
   * condition.
   *
   * <p>This method checks whether **none** of the elements in the collection satisfy the specified
   * condition. If no element matches the condition, the assertion passes. If at least one element
   * matches the condition, the assertion fails.
   *
   * <p>The condition is applied to **each element** in the collection, and the method returns the
   * current instance of {@code TestAssertLCollection} for further assertions.
   *
   * @param expectedClass the class of the objects in the collection (must not be {@code null})
   * @param condition the condition that the elements must not match (must not be {@code null})
   * @param <T> the type of the objects in the collection
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if at least one element in the collection matches the condition
   * @since 1.4.0
   */
  <T> TestAssertLCollection assertCollectionMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition);

  /**
   * Asserts that none of the elements in the collection in the HTTP response match any of the
   * specified conditions.
   *
   * <p>This method checks whether **none** of the elements in the collection satisfy **any** of the
   * specified conditions. If no element matches any of the conditions, the assertion passes. If at
   * least one element matches any of the conditions, the assertion fails.
   *
   * <p>The conditions are applied to **each element** in the collection, and the method returns the
   * current instance of {@code TestAssertLCollection} for further assertions.
   *
   * @param expectedClass the class of the objects in the collection (must not be {@code null})
   * @param conditions the conditions that the elements must not match (must not be {@code null})
   * @param <T> the type of the objects in the collection
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if at least one element in the collection matches any of the conditions
   * @since 1.4.0
   */
  @SuppressWarnings("unchecked")
  <T> TestAssertLCollection assertCollectionMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions);

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
