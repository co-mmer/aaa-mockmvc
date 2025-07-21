package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import java.util.function.Predicate;
import lombok.NonNull;

/**
 * Provides assertion methods for validating HTTP response collections and maps.
 *
 * <ul>
 *   <li>{@link #assertContentMatchNone(Class, Predicate)}: Asserts that none of the elements in the
 *       collection in the HTTP response match the specified condition.
 *   <li>{@link #assertContentMatchNone(Class, Predicate...)}: Asserts that none of the elements in
 *       the collection match any of the specified conditions.
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
   * @param condition the condition that the elements must not match (must not be {@code null})
   * @param <T> the type of the objects in the collection
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws AssertionError if at least one element in the collection matches the condition
   * @since 1.4.0
   * @deprecated Use {@link #assertContentMatchNone} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws AssertionError if at least one element in the collection matches any of the conditions
   * @since 1.4.0
   * @deprecated Use {@link #assertContentMatchNone} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @SuppressWarnings("unchecked")
  <T> TestAssertLCollection assertCollectionMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions);

  /**
   * Asserts that no elements in the collection satisfy the specified condition.
   *
   * <p>This method verifies that <b>none of the elements</b> in the collection match the given
   * {@code condition}. If <b>any element</b> satisfies the condition, the assertion fails and an
   * {@link AssertionError} is thrown.
   *
   * <p><b>Example:</b>
   *
   * <pre>{@code
   * // Checks that no user is blocked
   * assertContentMatchNone(User.class, user -> user.isBlocked());
   * }</pre>
   *
   * @param expectedClass The class of the objects in the collection (must not be {@code null})
   * @param condition The condition that must not be satisfied by any element (must not be {@code
   *     null})
   * @param <T> The type of the objects in the collection
   * @return The next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if any element in the collection satisfies the specified condition
   * @since 1.6.0
   */
  <T> TestAssertLCollection assertContentMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition);

  /**
   * Asserts that no elements in the collection satisfy <b>any</b> of the specified conditions.
   *
   * <p>This method verifies that <b>none of the elements</b> in the collection match <b>any</b> of
   * the provided {@code conditions} (logical OR across all conditions). If any element satisfies at
   * least one of the conditions, the assertion fails and an {@link AssertionError} is thrown.
   *
   * <p><b>Example:</b>
   *
   * <pre>{@code
   * // Checks that no user is blocked or has a null email
   * assertContentMatchNone(
   *     User.class,
   *     user -> user.isBlocked(),
   *     user -> user.getEmail() == null
   * );
   * }</pre>
   *
   * @param expectedClass The class of the objects in the collection (must not be {@code null})
   * @param conditions One or more conditions that must not be satisfied by any element (must not be
   *     {@code null})
   * @param <T> The type of the objects in the collection
   * @return The next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if any element in the collection satisfies any of the specified
   *     conditions
   * @since 1.6.0
   */
  @SuppressWarnings("unchecked")
  <T> TestAssertLCollection assertContentMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions);

  /**
   * Asserts that the HTTP response is valid for a HEAD request.
   *
   * <p>This method returns an instance of {@code TestAssertHead} for asserting the headers of the
   * HTTP response. It allows various validations of response headers, such as checking for the
   * presence or absence of specific headers and comparing header values.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.4.0
   */
  TestAssertHead assertHead();
}
