package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestArrangeNormalizer.normalizeAsObjects;
import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestArrangeNormalizer.normalizeCollection;
import static io.github.co_mmer.aaamockmvc.ej.test.web.mapper.TestGenericMapper.mapToCollection;
import static io.github.co_mmer.aaamockmvc.ej.test.web.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.utils.StringUtils.EMPTY_ARRAY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match.TestAssertMatch;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.exception.TestGenericMapperException;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Provides assertion methods for validating HTTP response collections and maps.
 *
 * <ul>
 *   <li>{@link #assertContentIsNotEmpty()}: Asserts that the collection in the HTTP response is not
 *       empty.
 *   <li>{@link #assertContentIsEmpty()}: Asserts that the collection in the HTTP response is empty.
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
 *   <li>{@link #assertHead()}: Asserting properties of response headers for HTTP HEAD requests.*
 * </ul>
 *
 * @since 1.4.0
 */
public final class TestAssertCollectionImpl
    implements TestAssert1Collection,
        TestAssert2Collection,
        TestAssert3Collection,
        TestAssert4Collection,
        TestAssert5Collection,
        TestAssertLCollection {

  private final ResultActions actions;
  private final MockHttpServletResponse response;
  private final ObjectMapper objectMapper;

  /**
   * Constructs a new {@code TestAssertCollectionImpl} instance with the given {@code ResultActions}
   * and {@code ObjectMapper}.
   *
   * @param actions the {@code ResultActions} containing the HTTP response (must not be {@code
   *     null})
   * @param objectMapper the {@code ObjectMapper} for JSON deserialization (must not be {@code
   *     null})
   * @throws NullPointerException if any of the parameters is {@code null}
   * @since 1.4.0
   */
  public TestAssertCollectionImpl(
      @NonNull ResultActions actions, @NonNull ObjectMapper objectMapper) {
    this.actions = actions;
    this.response = actions.andReturn().getResponse();
    this.objectMapper = objectMapper;
  }

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
   * @deprecated Use {@link #assertContentIsNotEmpty} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @Override
  public TestAssert2Collection assertCollectionNotEmpty() {
    assertContentIsNotEmpty();
    return this;
  }

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
   * @deprecated Use {@link #assertContentIsEmpty} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @Override
  public TestAssertLCollection assertCollectionEmpty() {
    assertContentIsEmpty();
    return this;
  }

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
  @Override
  public TestAssert2Collection assertCollectionSize(int size) {
    assertContentSize(size);
    return this;
  }

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
  @Override
  public <T> TestAssertLCollection assertCollectionEquals(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> expectedCollection) {

    assertContentEquals(expectedClass, expectedCollection);
    return this;
  }

  private <T> void performAssertion(
      Class<T> expectedClass,
      Collection<T> expectedResponse,
      BiConsumer<Collection<T>, Collection<T>> assertion) {

    try {
      var actual = mapToCollection(this.objectMapper, this.actions.andReturn(), expectedClass);
      assertion.accept(actual, expectedResponse);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
  }

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
  @Override
  public <T> TestAssert3Collection assertCollectionContains(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> expectedElements) {

    assertContentContains(expectedClass, expectedElements);
    return this;
  }

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
  @SafeVarargs
  @Override
  public final <T> TestAssert3Collection assertCollectionContains(
      @NonNull Class<T> expectedClass, @NonNull T... expectedElements) {

    return assertContentContains(expectedClass, List.of(expectedElements));
  }

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
  @Override
  public <T> TestAssertLCollection assertCollectionContainsAnyOrder(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> expectedCollection) {

    assertContentContainsAnyOrder(expectedClass, expectedCollection);
    return this;
  }

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
  @Override
  public <T> TestAssert3Collection assertCollectionNotContains(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> unexpectedElements) {

    return assertContentNotContains(expectedClass, unexpectedElements);
  }

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
  @SafeVarargs
  @Override
  public final <T> TestAssert3Collection assertCollectionNotContains(
      @NonNull Class<T> expectedClass, @NonNull T... unexpectedElements) {

    return assertCollectionNotContains(expectedClass, List.of(unexpectedElements));
  }

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
  @Override
  public <T> TestAssert4Collection assertCollectionMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {

    assertContentMatchAll(expectedClass, condition);
    return this;
  }

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
  @SafeVarargs
  @Override
  public final <T> TestAssert4Collection assertCollectionMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions) {

    assertContentMatchAll(expectedClass, conditions);
    return this;
  }

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
  @Override
  public <T> TestAssert5Collection assertCollectionMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {

    try {
      var actual = mapToCollection(this.objectMapper, this.actions.andReturn(), expectedClass);
      TestAssertMatch.assertThat(actual).matchesAny(condition);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

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
  @SafeVarargs
  @Override
  public final <T> TestAssert5Collection assertCollectionMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions) {

    try {
      var actual = mapToCollection(this.objectMapper, this.actions.andReturn(), expectedClass);
      TestAssertMatch.assertThat(actual).matchesAny(conditions);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

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
  @Override
  public <T> TestAssertLCollection assertCollectionMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {

    try {
      var actual = mapToCollection(this.objectMapper, this.actions.andReturn(), expectedClass);
      TestAssertMatch.assertThat(actual).matchesNone(condition);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

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
  @SafeVarargs
  @Override
  public final <T> TestAssertLCollection assertCollectionMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions) {

    try {
      var actual = mapToCollection(this.objectMapper, this.actions.andReturn(), expectedClass);
      TestAssertMatch.assertThat(actual).matchesNone(conditions);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

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
  @Override
  public TestAssert2Collection assertContentIsNotEmpty() {
    try {
      assertThat(this.response.getContentAsString(), not(anyOf(is(EMPTY), is(EMPTY_ARRAY))));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

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
  @Override
  public TestAssertLCollection assertContentIsEmpty() {
    try {
      assertThat(this.response.getContentAsString(), anyOf(is(EMPTY), is(EMPTY_ARRAY)));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

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
  @Override
  public TestAssert2Collection assertContentSize(int expectedSize) {
    try {
      var actual = mapToCollection(this.objectMapper, this.actions.andReturn(), Object.class);
      assertThat(
          "Expected collection size is " + expectedSize + ", but the content is null.",
          actual,
          is(not(nullValue())));
      assertThat(actual.size(), is(expectedSize));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

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
  @Override
  public <T> TestAssertLCollection assertContentEquals(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> expectedCollection) {

    performAssertion(
        expectedClass,
        expectedCollection,
        (actual, expected) ->
            assertThat(normalizeCollection(actual), is(normalizeCollection(expected))));
    return this;
  }

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
  @Override
  public <T> TestAssert3Collection assertContentContains(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> expectedElements) {

    performAssertion(
        expectedClass,
        expectedElements,
        (actual, expected) -> {
          List<Object> normalizeActual = normalizeAsObjects(actual);
          List<Object> normalizeExpected = normalizeAsObjects(expected);
          assertThat(normalizeActual, hasItems(normalizeExpected.toArray()));
        });
    return this;
  }

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
  @SafeVarargs
  @Override
  public final <T> TestAssert3Collection assertContentContains(
      @NonNull Class<T> expectedClass, @NonNull T... expectedElements) {

    return assertContentContains(expectedClass, List.of(expectedElements));
  }

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
  @Override
  public <T> TestAssertLCollection assertContentContainsAnyOrder(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> expectedCollection) {

    performAssertion(
        expectedClass,
        expectedCollection,
        (actual, expected) ->
            assertThat(
                normalizeCollection(actual),
                containsInAnyOrder(normalizeCollection(expected).toArray())));
    return this;
  }

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
  @Override
  public <T> TestAssert3Collection assertContentNotContains(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> unexpectedElements) {

    performAssertion(
        expectedClass,
        unexpectedElements,
        (actual, unexpected) -> {
          List<Object> actualNormalize = normalizeAsObjects(actual);
          List<Object> unexpectedNormalize = normalizeAsObjects(unexpected);
          assertThat(actualNormalize, not(hasItems(unexpectedNormalize.toArray())));
        });
    return this;
  }

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
  @SafeVarargs
  @Override
  public final <T> TestAssert3Collection assertContentNotContains(
      @NonNull Class<T> expectedClass, @NonNull T... unexpectedElements) {

    return assertContentNotContains(expectedClass, List.of(unexpectedElements));
  }

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
  @Override
  public <T> TestAssert4Collection assertContentMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {

    try {
      var actual = mapToCollection(this.objectMapper, this.actions.andReturn(), expectedClass);
      TestAssertMatch.assertThat(actual).matchesAll(condition);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

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
  @SafeVarargs
  @Override
  public final <T> TestAssert4Collection assertContentMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions) {

    try {
      var actual = mapToCollection(this.objectMapper, this.actions.andReturn(), expectedClass);
      TestAssertMatch.assertThat(actual).matchesAll(conditions);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

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
  @Override
  public <T> TestAssert5Collection assertContentMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {

    try {
      var actual = mapToCollection(this.objectMapper, this.actions.andReturn(), expectedClass);
      TestAssertMatch.assertThat(actual).matchesAny(condition);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

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
  @SafeVarargs
  @Override
  public final <T> TestAssert5Collection assertContentMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions) {

    try {
      var actual = mapToCollection(this.objectMapper, this.actions.andReturn(), expectedClass);
      TestAssertMatch.assertThat(actual).matchesAny(conditions);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

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
  @Override
  public <T> TestAssertLCollection assertContentMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {

    try {
      var actual = mapToCollection(this.objectMapper, this.actions.andReturn(), expectedClass);
      TestAssertMatch.assertThat(actual).matchesNone(condition);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

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
  @SafeVarargs
  @Override
  public final <T> TestAssertLCollection assertContentMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions) {

    try {
      var actual = mapToCollection(this.objectMapper, this.actions.andReturn(), expectedClass);
      TestAssertMatch.assertThat(actual).matchesNone(conditions);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

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
  @Override
  public TestAssertHead assertHead() {
    return new TestAssertHeadImpl(this.actions);
  }
}
