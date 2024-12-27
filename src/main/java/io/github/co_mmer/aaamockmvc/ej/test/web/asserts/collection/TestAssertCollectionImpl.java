package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestArrangeNormalizer.normalizeCollection;
import static io.github.co_mmer.aaamockmvc.ej.test.web.mapper.TestGenericMapper.mapToList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.exception.TestGenericMapperException;
import java.util.Collection;
import java.util.function.BiConsumer;
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
      assertThat(this.response.getContentAsString(), anyOf(is(Strings.EMPTY), is("[]"), is("{}")));
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
   * @param expectedClass the class of the objects in the collection (must not be {@code null})
   * @param expectedCollection the expected collection of objects (must not be {@code null})
   * @param <T> the type of the objects in the collection
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
