package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match.TestAssertMatchType.ALL;
import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match.TestAssertMatchType.ANY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match.TestAssertMatchType.NONE;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match.TestAssertMatchCollection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match.TestAssertMatchType;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.exception.TestGenericMapperException;
import java.util.Arrays;
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
 *   <li>{@link #assertCollectionNotEmpty()}: Asserts that the collection in the HTTP response is
 *       not empty.
 *   <li>{@link #assertCollectionEmpty()}: Asserts that the collection in the HTTP response is
 *       empty.
 *   <li>{@link #assertCollectionSize(int)}: Asserts that the size of the collection in the HTTP
 *       response matches the given size.
 *   <li>{@link #assertCollectionEquals(Class, Collection)}: Asserts that the content of the HTTP
 *       response matches the given collection of objects.
 *   <li>{@link #assertCollectionContains(Class, Collection)}: Asserts that the collection in the
 *       HTTP response contains the expected elements.
 *   <li>{@link #assertCollectionContains(Class, Object...)}: Asserts that the collection in the
 *       HTTP response contains the specified elements (varargs).
 *   <li>{@link #assertCollectionContainsAnyOrder(Class, Collection)}: Asserts that the collection
 *       in the HTTP response matches the given collection of objects, ignoring order.
 *   <li>{@link #assertCollectionNotContains(Class, Collection)}: Asserts that the collection in the
 *       HTTP response does not contain the specified elements.
 *   <li>{@link #assertCollectionNotContains(Class, Object...)}: Asserts that the collection in the
 *       HTTP response does not contain the specified elements (varargs).
 *   <li>{@link #assertCollectionMatchAll(Class, Predicate)}: Asserts that all elements in the
 *       collection in the HTTP response match the specified condition.
 *   <li>{@link #assertCollectionMatchAll(Class, Predicate...)}: Asserts that all elements in the
 *       collection match the specified conditions.
 *   <li>{@link #assertCollectionMatchAny(Class, Predicate)}: Asserts that at least one element in
 *       the collection in the HTTP response matches the specified condition.
 *   <li>{@link #assertCollectionMatchAny(Class, Predicate...)}: Asserts that at least one element
 *       in the collection matches the specified conditions.
 *   <li>{@link #assertCollectionMatchNone(Class, Predicate)}: Asserts that none of the elements in
 *       the collection in the HTTP response match the specified condition.
 *   <li>{@link #assertCollectionMatchNone(Class, Predicate...)}: Asserts that none of the elements
 *       in the collection match any of the specified conditions.
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
   * @return the current instance of {@code TestAssert2Collection} for further assertions
   * @throws AssertionError if the response collection is empty or invalid
   * @since 1.4.0
   */
  @Override
  public TestAssert2Collection assertCollectionNotEmpty() {
    try {
      assertThat(this.response.getContentAsString(), not(anyOf(is(EMPTY), is(EMPTY_ARRAY))));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

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
  @Override
  public TestAssertLCollection assertCollectionEmpty() {
    try {
      assertThat(this.response.getContentAsString(), anyOf(is(EMPTY), is(EMPTY_ARRAY)));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the size of the collection in the HTTP response matches the given size.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param size the expected size of the collection
   * @return the current instance of {@code TestAssert2Collection} for further assertions
   * @throws AssertionError if the collection size does not match the expected size
   * @since 1.4.0
   */
  @Override
  public TestAssert2Collection assertCollectionSize(int size) {
    try {
      this.actions.andExpect(jsonPath("$.length()", is(size)));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the content of the HTTP response matches the given collection of objects.
   *
   * <p>Both collections are normalized before comparison to ensure consistent results.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass the class of the objects in the list (must not be {@code null})
   * @param expectedCollection the expected list of objects (must not be {@code null})
   * @param <T> the type of the objects in the expected list
   * @throws AssertionError if the collections do not match
   * @since 1.4.0
   */
  @Override
  public <T> TestAssertLCollection assertCollectionEquals(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> expectedCollection) {

    performAssertion(
        expectedClass,
        expectedCollection,
        (actual, expected) ->
            assertThat(normalizeCollection(actual), is(normalizeCollection(expected))));
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
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if the collection does not contain the expected elements
   * @since 1.4.0
   */
  @Override
  public <T> TestAssert3Collection assertCollectionContains(
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
  @SafeVarargs
  @Override
  public final <T> TestAssert3Collection assertCollectionContains(
      @NonNull Class<T> expectedClass, @NonNull T... expectedElements) {

    return assertCollectionContains(expectedClass, List.of(expectedElements));
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
   * @return the current instance of {@code TestAssertCollection} for further assertions
   * @throws AssertionError if the collections do not match
   * @since 1.4.0
   */
  @Override
  public <T> TestAssertLCollection assertCollectionContainsAnyOrder(
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
  @Override
  public <T> TestAssert3Collection assertCollectionNotContains(
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
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if any element in the collection does not match the condition
   * @since 1.4.0
   */
  @Override
  public <T> TestAssert4Collection assertCollectionMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {

    assertCollectionMatch(ALL, expectedClass, List.of(condition));
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
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if any element in the collection does not match all the conditions
   * @since 1.4.0
   */
  @SafeVarargs
  @Override
  public final <T> TestAssert4Collection assertCollectionMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions) {

    assertCollectionMatch(ALL, expectedClass, Arrays.asList(conditions));
    return this;
  }

  private <T> void assertCollectionMatch(
      TestAssertMatchType matchType, Class<T> expectedClass, Collection<Predicate<T>> conditions) {

    try {
      var actual = mapToCollection(this.objectMapper, this.actions.andReturn(), expectedClass);
      TestAssertMatchCollection.assertMatch(matchType, actual, conditions);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
  }

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
  @Override
  public <T> TestAssert5Collection assertCollectionMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {

    assertCollectionMatch(ANY, expectedClass, List.of(condition));
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
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if no element in the collection matches all the conditions
   * @since 1.4.0
   */
  @SafeVarargs
  @Override
  public final <T> TestAssert5Collection assertCollectionMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions) {

    assertCollectionMatch(ANY, expectedClass, Arrays.asList(conditions));
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
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if at least one element in the collection matches the condition
   * @since 1.4.0
   */
  @Override
  public <T> TestAssertLCollection assertCollectionMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {

    assertCollectionMatch(NONE, expectedClass, List.of(condition));
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
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if at least one element in the collection matches any of the conditions
   * @since 1.4.0
   */
  @SafeVarargs
  @Override
  public final <T> TestAssertLCollection assertCollectionMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions) {

    assertCollectionMatch(NONE, expectedClass, Arrays.asList(conditions));
    return this;
  }

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
  @Override
  public TestAssertHead assertHead() {
    return new TestAssertHeadImpl(this.actions);
  }
}
