package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestArrangeNormalizer.normalizeObject;
import static io.github.co_mmer.aaamockmvc.ej.test.web.mapper.TestGenericMapper.mapTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match.TestAssertMatch;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.exception.TestGenericMapperException;
import java.util.function.Predicate;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Provides methods for asserting HTTP response content in tests.
 *
 * <ul>
 *   <li>{@link #assertContentNotEmpty()}: Asserts that the class of the HTTP response is not empty.
 *   <li>{@link #assertContentEmpty()}: Asserts that the class of the HTTP response is empty.
 *   <li>{@link #assertContentEquals(Class, Object)}: Asserts that the class of the HTTP response
 *       matches the expected object.
 *   <li>{@link #assertContentMatchAll(Class, Predicate)}: Asserts that the class matches all
 *       specified conditions.
 *   <li>{@link #assertContentMatchAll(Class, Predicate...)}: Asserts that the class matches all
 *       specified conditions.
 *   <li>{@link #assertContentMatchAny(Class, Predicate)}: Asserts that the class matches at least
 *       one of the specified conditions.
 *   <li>{@link #assertContentMatchAny(Class, Predicate...)}: Asserts that the class matches at
 *       least one of the specified conditions.
 *   <li>{@link #assertContentMatchNone(Class, Predicate)}: Asserts that the class matches none of
 *       the specified conditions.
 *   <li>{@link #assertContentMatchNone(Class, Predicate...)}: Asserts that the class matches none
 *       of the specified conditions.
 *   <li>{@link #assertHead()}: Provides assertion methods for validating the HTTP response headers.
 * </ul>
 *
 * @since 1.4.0
 */
public final class TestAssertClassImpl
    implements TestAssert1Class,
        TestAssert2Class,
        TestAssert3Class,
        TestAssert4Class,
        TestAssertLClass {

  private final ResultActions actions;
  private final MockHttpServletResponse response;
  private final ObjectMapper objectMapper;

  /**
   * Constructs an instance of {@code TestAssertContent} with the provided {@code ResultActions}.
   *
   * @param actions the {@code ResultActions} from a performed HTTP request (must not be {@code
   *     null})
   * @param objectMapper the {@link ObjectMapper} used for JSON processing (must not be {@code
   *     null})
   * @throws NullPointerException if the {@code actions} is {@code null}
   * @since 1.4.0
   */
  public TestAssertClassImpl(@NonNull ResultActions actions, @NonNull ObjectMapper objectMapper) {
    this.actions = actions;
    this.response = actions.andReturn().getResponse();
    this.objectMapper = objectMapper;
  }

  /**
   * Asserts that the string class of the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.4.0
   * @deprecated Use {@link #assertContentNotEmpty()} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @Override
  public TestAssert2Class assertClassNotEmpty() {
    assertContentNotEmpty();
    return this;
  }

  /**
   * Asserts that the string class of the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.4.0
   * @deprecated Use {@link #assertContentEmpty()} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @Override
  public TestAssertLClass assertClassEmpty() {
    assertContentEmpty();
    return this;
  }

  /**
   * Asserts that the object class of the HTTP response matches the expected object, using the
   * provided deserializer(s).
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * <p>As of version 1.3.0, both the actual and expected response class are normalized using
   * Unicode Normalization Form C (NFC) to ensure consistent text representation across different
   * Unicode formats.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param expectedResponse the expected object (must not be {@code null})
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.4.0
   * @deprecated Use {@link #assertContentEquals(Class, T)} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @Override
  public <T> TestAssertLClass assertClassEquals(
      @NonNull Class<T> expectedClass, @NonNull T expectedResponse) {
    assertContentEquals(expectedClass, expectedResponse);
    return this;
  }

  /**
   * Asserts that the class matches all specified conditions.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param condition a predicate that the class must match (must not be {@code null})
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   * @since 1.4.0
   * @deprecated Use {@link #assertContentMatchAll(Class, Predicate)} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @Override
  public <T> TestAssert3Class assertClassMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {
    assertContentMatchAll(expectedClass, condition);
    return this;
  }

  /**
   * Asserts that the class matches all specified conditions.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param conditions a varargs array of predicates that the class must match (must not be {@code
   *     null})
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code conditions} is {@code null}
   * @since 1.4.0
   * @deprecated Use {@link #assertContentMatchAll(Class, Predicate[])} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @SafeVarargs
  @Override
  public final <T> TestAssert3Class assertClassMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions) {
    assertContentMatchAll(expectedClass, conditions);
    return this;
  }

  /**
   * Asserts that the class matches at least one of the specified conditions.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param condition a predicate that the class may match (must not be {@code null})
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   * @since 1.4.0
   * @deprecated Use {@link #assertContentMatchAny(Class, Predicate)} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @Override
  public <T> TestAssert4Class assertClassMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {
    assertContentMatchAny(expectedClass, condition);
    return this;
  }

  /**
   * Asserts that the class matches at least one of the specified conditions.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param conditions a varargs array of predicates that the class may match (must not be {@code
   *     null})
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code conditions} is {@code null}
   * @since 1.4.0
   * @deprecated Use {@link #assertContentMatchAny(Class, Predicate[])} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @SafeVarargs
  @Override
  public final <T> TestAssert4Class assertClassMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions) {
    assertContentMatchAny(expectedClass, conditions);
    return this;
  }

  /**
   * Asserts that the class matches none of the specified conditions.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param condition a predicate that the class must not match (must not be {@code null})
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   * @since 1.4.0
   * @deprecated Use {@link #assertContentMatchNone(Class, Predicate)} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @Override
  public <T> TestAssertLClass assertClassMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {
    assertContentMatchNone(expectedClass, condition);
    return this;
  }

  /**
   * Asserts that the class matches none of the specified conditions.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param conditions a varargs array of predicates that the class must not match (must not be
   *     {@code null})
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code conditions} is {@code null}
   * @since 1.4.0
   * @deprecated Use {@link #assertContentMatchNone(Class, Predicate[])} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @SafeVarargs
  @Override
  public final <T> TestAssertLClass assertClassMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions) {
    assertContentMatchNone(expectedClass, conditions);
    return this;
  }

  /**
   * Asserts that the string class of the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.6.0
   */
  @Override
  public TestAssert2Class assertContentNotEmpty() {
    try {
      assertThat(this.response.getContentAsString().isEmpty(), is(false));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the string class of the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.6.0
   */
  @Override
  public TestAssertLClass assertContentEmpty() {
    try {
      assertThat(this.response.getContentAsString().isEmpty(), is(true));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the object class of the HTTP response matches the expected object, using the
   * provided deserializer(s).
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * <p>As of version 1.3.0, both the actual and expected response class are normalized using
   * Unicode Normalization Form C (NFC) to ensure consistent text representation across different
   * Unicode formats.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param expectedResponse the expected object (must not be {@code null})
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.6.0
   */
  @Override
  public <T> TestAssertLClass assertContentEquals(
      @NonNull Class<T> expectedClass, @NonNull T expectedResponse) {

    try {
      var result = this.actions.andReturn();
      var actual = mapTo(this.objectMapper, result, expectedClass);
      assertThat(normalizeObject(actual), is(normalizeObject(expectedResponse)));
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the class matches all specified conditions.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param condition a predicate that the class must match (must not be {@code null})
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   * @since 1.6.0
   */
  @Override
  public <T> TestAssert3Class assertContentMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {

    try {
      var actual = mapTo(this.objectMapper, this.actions.andReturn(), expectedClass);
      TestAssertMatch.assertThat(actual).matchesAll(condition);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the class matches all specified conditions.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param conditions a varargs array of predicates that the class must match (must not be {@code
   *     null})
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code conditions} is {@code null}
   * @since 1.6.0
   */
  @SafeVarargs
  @Override
  public final <T> TestAssert3Class assertContentMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions) {

    try {
      var actual = mapTo(this.objectMapper, this.actions.andReturn(), expectedClass);
      TestAssertMatch.assertThat(actual).matchesAll(conditions);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the class matches at least one of the specified conditions.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param condition a predicate that the class may match (must not be {@code null})
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   * @since 1.6.0
   */
  @Override
  public <T> TestAssert4Class assertContentMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {

    try {
      var actual = mapTo(this.objectMapper, this.actions.andReturn(), expectedClass);
      TestAssertMatch.assertThat(actual).matchesAny(condition);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the class matches at least one of the specified conditions.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param conditions a varargs array of predicates that the class may match (must not be {@code
   *     null})
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code conditions} is {@code null}
   * @since 1.6.0
   */
  @SafeVarargs
  @Override
  public final <T> TestAssert4Class assertContentMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions) {

    try {
      var actual = mapTo(this.objectMapper, this.actions.andReturn(), expectedClass);
      TestAssertMatch.assertThat(actual).matchesAny(conditions);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the class matches none of the specified conditions.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param condition a predicate that the class must not match (must not be {@code null})
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   * @since 1.6.0
   */
  @Override
  public <T> TestAssertLClass assertContentMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {

    try {
      var actual = mapTo(this.objectMapper, this.actions.andReturn(), expectedClass);
      TestAssertMatch.assertThat(actual).matchesNone(condition);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the class matches none of the specified conditions.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param conditions a varargs array of predicates that the class must not match (must not be
   *     {@code null})
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code conditions} is {@code null}
   * @since 1.6.0
   */
  @SafeVarargs
  @Override
  public final <T> TestAssertLClass assertContentMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions) {

    try {
      var actual = mapTo(this.objectMapper, this.actions.andReturn(), expectedClass);
      TestAssertMatch.assertThat(actual).matchesNone(conditions);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Provides assertion methods for validating the HTTP response headers.
   *
   * <p>This method returns an instance of {@code TestAssertHead}, which provides assertion methods
   * for validating the headers of the HTTP response, such as checking for the presence or absence
   * of specific headers and comparing header values.
   *
   * @return an instance of {@code TestAssertHead} for asserting the response headers
   * @since 1.4.0
   */
  @Override
  public TestAssertHead assertHead() {
    return new TestAssertHeadImpl(this.actions);
  }
}
