package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.match.TestMatchType.ALL;
import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.match.TestMatchType.ANY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.match.TestMatchType.NONE;
import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestArrangeNormalizer.normalizeAsObjects;
import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestArrangeNormalizer.normalizeCollection;
import static io.github.co_mmer.aaamockmvc.ej.test.web.mapper.TestGenericMapper.mapToList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.match.TestMatchType;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.exception.TestGenericMapperException;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import lombok.NonNull;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Implementation of {@code TestAssertCollection} for asserting the contents of HTTP responses
 * containing collections.
 *
 * <p>This class provides various assertion methods to validate that the response body matches the
 * expected collections. It supports checking for empty or non-empty collections, validating the
 * size of collections, and asserting equality of collections.
 *
 * @since 1.4.0
 */
public final class TestAssertCollectionImpl
    implements TestAssert1Collection,
    TestAssert2Collection,
    TestAssert3Collection,
    TestAssertLCollection {

  private final ResultActions actions;
  private final MockHttpServletResponse response;
  private final ObjectMapper objectMapper;

  /**
   * Constructs a new {@code TestAssertCollectionImpl} instance with the given {@code ResultActions}
   * and {@code ObjectMapper}.
   *
   * @param actions      the {@code ResultActions} containing the HTTP response (must not be
   *                     {@code null})
   * @param objectMapper the {@code ObjectMapper} for JSON deserialization (must not be
   *                     {@code null})
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
      assertThat(this.response.getContentAsString(), not(anyOf(is(Strings.EMPTY), is("[]"))));
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
      assertThat(this.response.getContentAsString(), anyOf(is(Strings.EMPTY), is("[]")));
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
   * @return the current instance of {@code TestAssert3Collection} for further assertions
   * @throws AssertionError if the collection size does not match the expected size
   * @since 1.4.0
   */
  @Override
  public TestAssert3Collection assertCollectionSize(int size) {
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
   * @param expectedClass      the class of the objects in the list (must not be {@code null})
   * @param expectedCollection the expected list of objects (must not be {@code null})
   * @param <T>                the type of the objects in the expected list
   * @throws AssertionError if the collections do not match
   * @since 1.4.0
   */
  @Override
  public <T> TestAssertLCollection assertCollectionEquals(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> expectedCollection) {

    return performAssertion(
        expectedClass,
        expectedCollection,
        (actual, expected) ->
            assertThat(normalizeCollection(actual), is(normalizeCollection(expected))));
  }

  /**
   * Asserts that the collection in the HTTP response matches the expected collection, ignoring
   * order.
   *
   * <p>Both collections are normalized before comparison to ensure consistent results.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass      the class of the objects in the collection (must not be
   *                           {@code null})
   * @param expectedCollection the expected collection of objects (must not be {@code null})
   * @param <T>                the type of the objects in the collection
   * @return the current instance of {@code TestAssertCollection} for further assertions
   * @throws AssertionError if the collections do not match
   * @since 1.4.0
   */
  @Override
  public <T> TestAssertLCollection assertCollectionEqualsIgnoreOrder(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> expectedCollection) {

    return performAssertion(
        expectedClass,
        expectedCollection,
        (actual, expected) ->
            assertThat(
                normalizeCollection(actual),
                containsInAnyOrder(normalizeCollection(expected).toArray())));
  }

  private <T> TestAssertLCollection performAssertion(
      Class<T> expectedClass,
      Collection<T> expectedResponse,
      BiConsumer<Collection<T>, Collection<T>> assertion) {

    try {
      var result = this.actions.andReturn();
      var actual = mapToList(this.objectMapper, result, expectedClass);
      assertion.accept(actual, expectedResponse);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the collection in the HTTP response contains the expected elements.
   *
   * <p>Both collections are normalized before comparison to ensure consistent results.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass    the class of the objects in the collection (must not be {@code null})
   * @param expectedElements the collection of expected elements (must not be {@code null})
   * @param <T>              the type of the objects in the collection
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if the collection does not contain the expected elements
   * @since 1.4.0
   */
  @Override
  public <T> TestAssertLCollection assertCollectionContains(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> expectedElements) {

    return performAssertion(
        expectedClass,
        expectedElements,
        (actual, expected) -> {
          List<Object> normalizeActual = normalizeAsObjects(actual);
          List<Object> normalizeExpected = normalizeAsObjects(expected);
          assertThat(normalizeActual, hasItems(normalizeExpected.toArray()));
        });
  }

  /**
   * Asserts that the collection in the HTTP response contains the specified elements.
   *
   * <p>This method provides a varargs overload for specifying the expected elements directly,
   * which are converted into a collection and passed to the main {@code assertCollectionContains}
   * method.
   *
   * <p>Both the actual and expected collections are normalized before comparison to ensure
   * consistent results.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass    the class of the objects in the collection (must not be {@code null})
   * @param expectedElements the elements expected to be present in the collection (must not be
   *                         {@code null})
   * @param <T>              the type of the objects in the collection
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if the collection does not contain the specified elements
   * @since 1.4.0
   */
  @SafeVarargs
  @Override
  public final <T> TestAssertLCollection assertCollectionContains(
      @NonNull Class<T> expectedClass, @NonNull T... expectedElements) {

    return assertCollectionContains(expectedClass, List.of(expectedElements));
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
   * @param expectedClass      the class of the objects in the collection (must not be
   *                           {@code null})
   * @param unexpectedElements the elements that must not be present in the collection (must not be
   *                           {@code null})
   * @param <T>                the type of the objects in the collection
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if the collection contains any of the specified elements
   * @since 1.4.0
   */
  @Override
  public <T> TestAssertLCollection assertCollectionNotContains(
      @NonNull Class<T> expectedClass, @NonNull Collection<T> unexpectedElements) {

    return performAssertion(
        expectedClass,
        unexpectedElements,
        (actual, unexpected) -> {
          List<Object> actualNormalize = normalizeAsObjects(actual);
          List<Object> unexpectedNormalize = normalizeAsObjects(unexpected);
          assertThat(actualNormalize, not(hasItems(unexpectedNormalize.toArray())));
        });
  }

  /**
   * Asserts that the collection in the HTTP response does not contain the specified elements.
   *
   * <p>This method provides a varargs overload for specifying the unexpected elements directly,
   * which are converted into a collection and passed to the main
   * {@code assertCollectionNotContains} method.
   *
   * <p>Both the actual and unexpected collections are normalized before comparison to ensure
   * consistent results.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass      the class of the objects in the collection (must not be
   *                           {@code null})
   * @param unexpectedElements the elements that must not be present in the collection (must not be
   *                           {@code null})
   * @param <T>                the type of the objects in the collection
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if the collection contains any of the specified elements
   * @since 1.4.0
   */
  @SafeVarargs
  @Override
  public final <T> TestAssertLCollection assertCollectionNotContains(
      @NonNull Class<T> expectedClass, @NonNull T... unexpectedElements) {

    return assertCollectionNotContains(expectedClass, List.of(unexpectedElements));
  }

  /**
   * Asserts that all elements in the collection in the HTTP response match the specified
   * condition.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass the class of the objects in the collection (must not be {@code null})
   * @param condition     the condition that all elements must match (must not be {@code null})
   * @param <T>           the type of the objects in the collection
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if any element in the collection does not match the condition
   * @since 1.4.0
   */
  @Override
  public <T> TestAssertLCollection assertCollectionMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {

    assertCollectionMatch(expectedClass, condition, ALL);
    return this;
  }

  private <T> void assertCollectionMatch(
      Class<T> expectedClass, Predicate<T> condition, TestMatchType matchType) {

    try {
      var result = this.actions.andReturn();
      var actual = mapToList(this.objectMapper, result, expectedClass);

      var matches =
          switch (matchType) {
            case ALL -> actual.stream().allMatch(condition);
            case ANY -> actual.stream().anyMatch(condition);
            case NONE -> actual.stream().noneMatch(condition);
          };
      assertThat(matchType.getReason() + " Actual list: " + actual, matches, is(true));
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
   * @param condition     the condition that at least one element must match (must not be
   *                      {@code null})
   * @param <T>           the type of the objects in the collection
   * @return the current instance of {@code TestAssertLCollection} for further assertions
   * @throws AssertionError if no element in the collection matches the condition
   * @since 1.4.0
   */
  @Override
  public <T> TestAssertLCollection assertCollectionMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {

    assertCollectionMatch(expectedClass, condition, ANY);
    return this;
  }

  @Override
  public <T> TestAssertLCollection assertCollectionMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {

    assertCollectionMatch(expectedClass, condition, NONE);
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

  /**
   * Retrieves the {@link TestAnswer} instance for the executed HTTP request.
   *
   * <p>This method provides access to the response content and other aspects of the request's
   * outcome, enabling further validation and examination of the HTTP response.
   *
   * @return a {@code TestAnswer} instance for accessing the result of the request
   * @since 1.4.0
   */
  @Override
  public TestAnswer answer() {
    return null; // todo
  }
}
