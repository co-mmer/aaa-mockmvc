package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import java.util.function.Predicate;
import lombok.NonNull;

/**
 * Provides assertion methods for validating HTTP response collections and maps.
 *
 * <ul>
 *   <li>{@link #assertContentMatchAll(Class, Predicate)}: Asserts that all elements in the
 *       collection in the HTTP response match the specified condition.
 *   <li>{@link #assertContentMatchAll(Class, Predicate...)}: Asserts that all elements in the
 *       collection match the specified conditions.
 *   <li>{@link #assertContentMatchAny(Class, Predicate)}: Asserts that at least one element in the
 *       collection in the HTTP response matches the specified condition.
 *   <li>{@link #assertContentMatchAny(Class, Predicate...)}: Asserts that at least one element in
 *       the collection matches the specified conditions.
 *   <li>{@link #assertContentMatchNone(Class, Predicate)}: Asserts that none of the elements in the
 *       collection in the HTTP response match the specified condition.
 *   <li>{@link #assertContentMatchNone(Class, Predicate...)}: Asserts that none of the elements in
 *       the collection match any of the specified conditions.
 *   <li>{@link #assertHead()}: Asserting properties of response headers for HTTP HEAD requests.*
 * </ul>
 *
 * @since 1.4.0
 */
public interface TestAssert3Collection {

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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any element in the collection does not match the condition
   * @since 1.4.0
   * @deprecated Use {@link #assertContentMatchAll} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any element in the collection does not match all the conditions
   * @since 1.4.0
   * @deprecated Use {@link #assertContentMatchAll} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @SuppressWarnings("unchecked")
  <T> TestAssert4Collection assertCollectionMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions);

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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws AssertionError if no element in the collection matches the condition
   * @since 1.4.0
   * @deprecated Use {@link #assertContentMatchAny} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws AssertionError if no element in the collection matches all the conditions
   * @since 1.4.0
   * @deprecated Use {@link #assertContentMatchAny} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
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
   * Asserts that all elements in the collection satisfy the specified condition.
   *
   * <p>This method verifies that <b>every single element</b> in the collection matches the given
   * {@code condition}. If <b>any element</b> does not satisfy the condition, the assertion fails
   * and an {@link AssertionError} is thrown.
   *
   * <p><b>Example:</b>
   *
   * <pre>{@code
   * // Checks that all users are at least 18 years old
   * assertCollectionMatchAll(User.class, user -> user.getAge() >= 18);
   * }</pre>
   *
   * @param expectedClass The class of the objects in the collection (must not be {@code null})
   * @param condition The condition that all elements must satisfy (must not be {@code null})
   * @param <T> The type of the objects in the collection
   * @return The next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if any element in the collection does not satisfy the condition
   * @since 1.6.0
   */
  <T> TestAssert4Collection assertContentMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition);

  /**
   * Asserts that all elements in the collection satisfy <b>all</b> of the specified conditions.
   *
   * <p>This method verifies that <b>every element</b> in the collection matches <b>every provided
   * condition</b> (logical AND across all conditions). If any element fails to meet at least one
   * condition, the assertion fails and an {@link AssertionError} is thrown.
   *
   * <p><b>Example:</b>
   *
   * <pre>{@code
   * // Checks that all users are at least 18 years old and have a non-null email address
   * assertContentMatchAll(
   *     User.class,
   *     user -> user.getAge() >= 18,
   *     user -> user.getEmail() != null
   * );
   * }</pre>
   *
   * @param expectedClass The class of the objects in the collection (must not be {@code null})
   * @param conditions One or more conditions that each element must satisfy (must not be {@code
   *     null})
   * @param <T> The type of the objects in the collection
   * @return The next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if any element in the collection does not satisfy all specified
   *     conditions
   * @since 1.6.0
   */
  @SuppressWarnings("unchecked")
  <T> TestAssert4Collection assertContentMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions);

  /**
   * Asserts that at least one element in the collection satisfies the specified condition.
   *
   * <p>This method checks whether <b>any element</b> in the collection matches the given {@code
   * condition}. If <b>no element</b> satisfies the condition, the assertion fails and an {@link
   * AssertionError} is thrown.
   *
   * <p><b>Example:</b>
   *
   * <pre>{@code
   * // Checks that at least one user is an admin
   * assertContentMatchAny(User.class, user -> user.isAdmin());
   * }</pre>
   *
   * @param expectedClass The class of the objects in the collection (must not be {@code null})
   * @param condition The condition that at least one element must satisfy (must not be {@code
   *     null})
   * @param <T> The type of the objects in the collection
   * @return The next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if no element in the collection satisfies the specified condition
   * @since 1.6.0
   */
  <T> TestAssert5Collection assertContentMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition);

  /**
   * Asserts that at least one element in the collection satisfies <b>at least one</b> of the
   * specified conditions.
   *
   * <p>This method checks whether <b>any element</b> in the collection matches <b>at least one</b>
   * of the provided {@code conditions} (logical OR across all conditions). If no element satisfies
   * any of the conditions, the assertion fails and an {@link AssertionError} is thrown.
   *
   * <p><b>Example:</b>
   *
   * <pre>{@code
   * // Checks that at least one user is an admin or is verified
   * assertContentMatchAny(
   *     User.class,
   *     user -> user.isAdmin(),
   *     user -> user.isVerified()
   * );
   * }</pre>
   *
   * @param expectedClass The class of the objects in the collection (must not be {@code null})
   * @param conditions One or more conditions; at least one must be satisfied by at least one
   *     element (must not be {@code null})
   * @param <T> The type of the objects in the collection
   * @return The next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if no element in the collection satisfies any of the specified
   *     conditions
   * @since 1.6.0
   */
  @SuppressWarnings("unchecked")
  <T> TestAssert5Collection assertContentMatchAny(
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
