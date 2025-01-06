package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import java.util.function.Predicate;
import lombok.NonNull;

/**
 * Provides assertion methods for validating HTTP response collections and maps.
 *
 * <ul>
 *   <li>{@link #assertCollectionMatchNone(Class, Predicate)}: Asserts that none of the elements in the collection in the HTTP response match the specified condition.</li>
 *   <li>{@link #assertCollectionMatchNone(Class, Predicate...)}: Asserts that none of the elements in the collection match any of the specified conditions.</li>
 * </ul>
 *
 * @since 1.4.0
 */
public interface TestAssert5Collection {

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
   * @param condition     the condition that the elements must not match (must not be {@code null})
   * @param <T>           the type of the objects in the collection
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
   * <p>This method checks whether **none** of the elements in the collection satisfy **any** of
   * the
   * specified conditions. If no element matches any of the conditions, the assertion passes. If at
   * least one element matches any of the conditions, the assertion fails.
   *
   * <p>The conditions are applied to **each element** in the collection, and the method returns
   * the
   * current instance of {@code TestAssertLCollection} for further assertions.
   *
   * @param expectedClass the class of the objects in the collection (must not be {@code null})
   * @param conditions    the conditions that the elements must not match (must not be
   *                      {@code null})
   * @param <T>           the type of the objects in the collection
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
