package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match.TestAssertMatchType.ALL;
import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match.TestAssertMatchType.ANY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match.TestAssertMatchType.NONE;
import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestArrangeNormalizer.normalizeObject;
import static io.github.co_mmer.aaamockmvc.ej.test.web.mapper.TestGenericMapper.mapTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match.TestAssertMatchClass;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match.TestAssertMatchType;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.exception.TestGenericMapperException;
import java.util.List;
import java.util.function.Predicate;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Provides methods for asserting HTTP response content in tests.
 *
 * <ul>
 *   <li>{@link #assertClassNotEmpty()}: Asserts that the class of the HTTP response is not empty.
 *   <li>{@link #assertClassEmpty()}: Asserts that the class of the HTTP response is empty.
 *   <li>{@link #assertClassEquals(Class, Object)}: Asserts that the class of the HTTP response
 *       matches the expected object.
 *   <li>{@link #assertClassMatchAll(Class, Predicate)}: Asserts that the class matches all
 *       specified conditions.
 *   <li>{@link #assertClassMatchAll(Class, Predicate...)}: Asserts that the class matches all
 *       specified conditions.
 *   <li>{@link #assertClassMatchAny(Class, Predicate)}: Asserts that the class matches at least one
 *       of the specified conditions.
 *   <li>{@link #assertClassMatchAny(Class, Predicate...)}: Asserts that the class matches at least
 *       one of the specified conditions.
 *   <li>{@link #assertClassMatchNone(Class, Predicate)}: Asserts that the class matches none of the
 *       specified conditions.
 *   <li>{@link #assertClassMatchNone(Class, Predicate...)}: Asserts that the class matches none of
 *       the specified conditions.
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
   * Asserts that the class of the HTTP response as a string is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.4.0
   */
  @Override
  public TestAssert2Class assertClassNotEmpty() {
    try {
      assertThat(this.response.getContentAsString().isEmpty(), is(false));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the class of the HTTP response as a string is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.4.0
   */
  @Override
  public TestAssertLClass assertClassEmpty() {
    try {
      assertThat(this.response.getContentAsString().isEmpty(), is(true));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the class of the HTTP response matches the given object of type {@code T}.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * <p>As of version 1.3.0, both the actual and expected response content are normalized using
   * Unicode Normalization Form C (NFC) to ensure consistent text representation across different
   * Unicode formats.
   *
   * @param expectedClass the class of the expected object (must not be {@code null})
   * @param expectedResponse the expected object (must not be {@code null})
   * @param <T> the type of the expected response
   * @return the current instance of {@code TestAssertLClass} for method chaining
   * @since 1.4.0
   */
  @Override
  public <T> TestAssertLClass assertClassEquals(
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
   * @param condition a predicate that the content must match (must not be {@code null})
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   */
  @Override
  public <T> TestAssert3Class assertClassMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {

    assertMatch(ALL, expectedClass, List.of(condition));
    return this;
  }

  private <T> void assertMatch(
      TestAssertMatchType matchType, Class<T> expectedClass, List<Predicate<T>> conditions) {

    try {
      var actual = mapTo(this.objectMapper, this.actions.andReturn(), expectedClass);
      TestAssertMatchClass.assertMatch(matchType, actual, conditions);
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
  }

  /**
   * Asserts that the class matches all specified conditions.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param conditions a varargs array of predicates that the content must match (must not be {@code
   *     null})
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @throws NullPointerException if {@code expectedClass} or {@code conditions} is {@code null}
   */
  @SafeVarargs
  @Override
  public final <T> TestAssert3Class assertClassMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions) {

    assertMatch(ALL, expectedClass, List.of(conditions));
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
   * @param condition a predicate that the content may match (must not be {@code null})
   * @return the current instance of {@code TestAssertLClass} for method chaining
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   */
  @Override
  public <T> TestAssert4Class assertClassMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {

    assertMatch(ANY, expectedClass, List.of(condition));
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
   * @param conditions a varargs array of predicates that the content may match (must not be {@code
   *     null})
   * @return the current instance of {@code TestAssertLClass} for method chaining
   * @throws NullPointerException if {@code expectedClass} or {@code conditions} is {@code null}
   */
  @SafeVarargs
  @Override
  public final <T> TestAssert4Class assertClassMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions) {

    assertMatch(ANY, expectedClass, List.of(conditions));
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
   * @param condition a predicate that the content must not match (must not be {@code null})
   * @return the current instance of {@code TestAssertLClass} for method chaining
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   */
  @Override
  public <T> TestAssertLClass assertClassMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition) {

    assertMatch(NONE, expectedClass, List.of(condition));
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
   * @param conditions a predicate that the content must not match (must not be {@code null})
   * @return the current instance of {@code TestAssertLClass} for method chaining
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   */
  @SafeVarargs
  @Override
  public final <T> TestAssertLClass assertClassMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions) {

    assertMatch(NONE, expectedClass, List.of(conditions));
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
