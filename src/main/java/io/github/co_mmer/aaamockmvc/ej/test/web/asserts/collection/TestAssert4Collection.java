package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import java.util.function.Predicate;
import lombok.NonNull;

/**
 * Provides assertion methods for validating HTTP response collections and maps.
 *
 * <ul>
 *   <li>{@link #assertCollectionMatchAny(Class, Predicate)}: Asserts that at least one element in
 *       the collection in the HTTP response matches the specified condition.
 *   <li>{@link #assertCollectionMatchAny(Class, Predicate...)}: Asserts that at least one element
 *       in the collection matches the specified conditions.
 *   <li>{@link #assertCollectionMatchNone(Class, Predicate)}: Asserts that none of the elements in
 *       the collection in the HTTP response match the specified condition.
 *   <li>{@link #assertCollectionMatchNone(Class, Predicate...)}: Asserts that none of the elements
 *       in the collection match any of the specified conditions.
 * </ul>
 *
 * @since 1.4.0
 */
public interface TestAssert4Collection extends TestAssertLCollection {

  /**
   * Asserts that at least one element in the collection in the HTTP response matches the specified
   * condition.
   *
   * <p>If any element matches the condition, the assertion passes. If no element matches the
   * condition, the assertion fails.
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
}
