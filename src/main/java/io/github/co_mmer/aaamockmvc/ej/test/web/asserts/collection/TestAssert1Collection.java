package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import java.util.Collection;
import java.util.function.Predicate;
import lombok.NonNull;

/**
 * Provides assertion methods for validating HTTP response collections and maps.
 *
 * <ul>
 *   <li>{@link #assertContentNotEmpty()}: Asserts that the collection in the HTTP response is not
 *       empty.
 *   <li>{@link #assertContentEmpty()}: Asserts that the collection in the HTTP response is empty.
 *   <li>{@link #assertContentSize(int)}: Asserts that the size of the collection in the HTTP
 *       response matches the given size.
 *   <li>{@link #assertContentEquals(Class, Collection)}: Asserts that the content of the HTTP
 *       response matches the given collection of objects.
 *   <li>{@link #assertContentContains(Class, Collection)}: Asserts that the collection in the HTTP
 *       response contains the expected elements.
 *   <li>{@link #assertContentContains(Class, Object...)}: Asserts that the collection in the HTTP
 *       response contains the specified elements (varargs).
 *   <li>{@link #assertContentContainsAnyOrder(Class, Collection)}: Asserts that the collection in
 *       the HTTP response matches the given collection of objects, ignoring order.
 *   <li>{@link #assertContentNotContains(Class, Collection)}: Asserts that the collection in the
 *       HTTP response does not contain the specified elements.
 *   <li>{@link #assertContentNotContains(Class, Object...)}: Asserts that the collection in the
 *       HTTP response does not contain the specified elements (varargs).
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
 * </ul>
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the response collection is empty or invalid
   * @since 1.4.0
   * @deprecated Use {@link #assertContentNotEmpty} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  TestAssert2Collection assertCollectionNotEmpty();

  /**
   * Asserts that the collection in the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the response collection is not empty
   * @since 1.4.0
   * @deprecated Use {@link #assertContentEmpty} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  TestAssertLCollection assertCollectionEmpty();

  /**
   * Asserts that the size of the collection in the HTTP response matches the given size.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param size the expected size of the collection
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the collection size does not match the expected size
   * @since 1.4.0
   * @deprecated Use {@link #assertContentSize} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  TestAssert2Collection assertCollectionSize(int size);

  /**
   * Asserts that the content of the HTTP response matches the given collection of objects.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass the class of the objects in the list (must not be {@code null})
   * @param expectedCollection the expected list of objects (must not be {@code null})
   * @param <T> the type of the objects in the expected list
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.4.0
   * @deprecated Use {@link #assertContentEquals} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  <T> TestAssertLCollection assertCollectionEquals(
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the collection does not contain the expected elements
   * @since 1.4.0
   * @deprecated Use {@link #assertContentContains} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the collection does not contain the specified elements
   * @since 1.4.0
   * @deprecated Use {@link #assertContentContains} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @SuppressWarnings("unchecked")
  <T> TestAssert3Collection assertCollectionContains(
      @NonNull Class<T> expectedClass, @NonNull T... expectedElements);

  /**
   * Asserts that the collection in the HTTP response matches the given collection of objects,
   * ignoring order.
   *
   * <p>Both collections are normalized before comparison to ensure consistent results.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass the class of the objects in the collection (must not be {@code null})
   * @param expectedCollection the expected collection of objects (must not be {@code null})
   * @param <T> the type of the objects in the collection
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the collections do not match
   * @since 1.4.0
   * @deprecated Use {@link #assertContentContainsAnyOrder} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  <T> TestAssertLCollection assertCollectionContainsAnyOrder(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> expectedCollection);

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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the collection contains any of the specified elements
   * @since 1.4.0
   * @deprecated Use {@link #assertContentNotContains} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the collection contains any of the specified elements
   * @since 1.4.0
   * @deprecated Use {@link #assertContentNotContains} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @SuppressWarnings("unchecked")
  <T> TestAssert3Collection assertCollectionNotContains(
      @NonNull Class<T> expectedClass, @NonNull T... unexpectedElements);

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
   * Asserts that the collection is not empty.
   *
   * <p>This method verifies that the actual collection contains at least one element. If the
   * collection is empty, the assertion fails and an {@link AssertionError} is thrown.
   *
   * @return The next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if the collection is empty
   * @since 1.6.0
   */
  TestAssert2Collection assertContentNotEmpty();

  /**
   * Asserts that the collection is empty.
   *
   * <p>This method verifies that the actual collection contains no elements. If the collection is
   * not empty, the assertion fails and an {@link AssertionError} is thrown.
   *
   * @return The next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if the collection is not empty
   * @since 1.6.0
   */
  TestAssertLCollection assertContentEmpty();

  /**
   * Asserts that the collection has the specified size.
   *
   * <p>This method verifies that the actual collection contains exactly the given number of
   * elements. If the size of the collection does not match the specified {@code size}, the
   * assertion fails and an {@link AssertionError} is thrown.
   *
   * @param expectedSize The expected size of the collection
   * @return The next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if the size of the collection does not match the specified value
   * @since 1.6.0
   */
  TestAssert2Collection assertContentSize(int expectedSize);

  /**
   * Asserts that the collection exactly matches the specified expected collection, including order
   * and content.
   *
   * <p>This method verifies that the actual collection contains <b>exactly the same elements</b> as
   * the given {@code expectedCollection}, in the <b>same order</b> and with the <b>same size</b>.
   * If there are any missing, extra, or differently ordered elements, the assertion fails and an
   * {@link AssertionError} is thrown.
   *
   * @param expectedClass The class of the objects in the collection (must not be {@code null})
   * @param expectedCollection The collection that the actual collection must exactly match (must
   *     not be {@code null})
   * @param <T> The type of the objects in the collection
   * @return The next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if the collections differ in content, size, or order
   * @since 1.6.0
   */
  <T> TestAssertLCollection assertContentEquals(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> expectedCollection);

  /**
   * Asserts that the collection contains all the specified expected elements.
   *
   * <p>This method verifies that <b>each</b> element in the provided {@code expectedElements}
   * collection is present in the actual collection. The actual collection may contain additional
   * elements, but none of the expected elements may be missing. If any expected element is missing,
   * the assertion fails and an {@link AssertionError} is thrown.
   *
   * @param expectedClass The class of the objects in the collection (must not be {@code null})
   * @param expectedElements A collection of elements that must be present in the actual collection
   *     (must not be {@code null})
   * @param <T> The type of the objects in the collection
   * @return The next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if any of the expected elements are missing from the collection
   * @since 1.6.0
   */
  <T> TestAssert3Collection assertContentContains(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> expectedElements);

  /**
   * Asserts that the collection contains all the specified expected elements.
   *
   * <p>This method verifies that <b>each</b> element in the given {@code expectedElements}
   * collection is present in the actual collection. If any of these elements are missing, the
   * assertion fails and an {@link AssertionError} is thrown.
   *
   * @param expectedClass The class of the objects in the collection (must not be {@code null})
   * @param expectedElements A collection of elements that must be present in the actual collection
   *     (must not be {@code null})
   * @param <T> The type of the objects in the collection
   * @return The next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if any of the expected elements are missing from the collection
   * @since 1.6.0
   */
  @SuppressWarnings("unchecked")
  <T> TestAssert3Collection assertContentContains(
      @NonNull Class<T> expectedClass, @NonNull T... expectedElements);

  /**
   * Asserts that the collection contains all the specified expected elements, in any order.
   *
   * <p>This method verifies that the actual collection contains <b>exactly the same elements</b> as
   * the given {@code expectedCollection}, but the order does not matter. The assertion passes if
   * both collections have the same size and contain the same elements, regardless of order. If any
   * expected element is missing, or if there are extra elements, the assertion fails and an {@link
   * AssertionError} is thrown.
   *
   * @param expectedClass The class of the objects in the collection (must not be {@code null})
   * @param expectedCollection The collection that the actual collection must contain, in any order
   *     (must not be {@code null})
   * @param <T> The type of the objects in the collection
   * @return The next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if the collections differ in content or size
   * @since 1.6.0
   */
  <T> TestAssertLCollection assertContentContainsAnyOrder(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> expectedCollection);

  /**
   * Asserts that the collection does <b>not</b> contain any of the specified unexpected elements.
   *
   * <p>This method verifies that <b>none</b> of the elements in the given {@code
   * unexpectedElements} collection are present in the actual collection. If the collection contains
   * any of these elements, the assertion fails and an {@link AssertionError} is thrown.
   *
   * @param expectedClass The class of the objects in the collection (must not be {@code null})
   * @param unexpectedElements A collection of elements that must not be present in the actual
   *     collection (must not be {@code null})
   * @param <T> The type of the objects in the collection
   * @return The next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if any of the unexpected elements are found in the collection
   * @since 1.6.0
   */
  <T> TestAssert3Collection assertContentNotContains(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> unexpectedElements);

  /**
   * Asserts that the collection does <b>not</b> contain any of the specified unexpected elements.
   *
   * <p>This method verifies that <b>none</b> of the provided {@code unexpectedElements} are present
   * in the collection. If the collection contains any of these elements, the assertion fails and an
   * {@link AssertionError} is thrown.
   *
   * @param expectedClass The class of the objects in the collection (must not be {@code null})
   * @param unexpectedElements One or more elements that must not be present in the collection (must
   *     not be {@code null})
   * @param <T> The type of the objects in the collection
   * @return The next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if any of the unexpected elements are found in the collection
   * @since 1.6.0
   */
  @SuppressWarnings("unchecked")
  <T> TestAssert3Collection assertContentNotContains(
      @NonNull Class<T> expectedClass, @NonNull T... unexpectedElements);

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
}
