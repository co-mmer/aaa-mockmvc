package ej.test.aaamockmvc.request.asserts.content;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import com.fasterxml.jackson.databind.JsonDeserializer;
import ej.test.aaamockmvc.request.asserts.head.TestAssertHead;
import ej.test.aaamockmvc.request.asserts.head.TestAssertHeadImpl;
import ej.test.aaamockmvc.request.asserts.mapper.TestAssertResultMapper;
import ej.test.aaamockmvc.request.asserts.mapper.exception.TestAssertResultMapperException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Implementation of the {@code TestAssertContent} interface for asserting content of HTTP responses
 * in tests.
 *
 * <p>This class provides various methods to assert the content of an HTTP response, including
 * checking for emptiness, matching expected values, and deserializing to specific types. It
 * utilizes the Spring's {@code ResultActions} and {@code MockHttpServletResponse} for handling the
 * HTTP response.
 *
 * <p>Each assertion method returns the current instance of {@code TestAssertContent} to allow for
 * method chaining.
 *
 * @since 1.0.0
 */
public final class TestAssertContentImpl implements TestAssertContent {

  private final ResultActions actions;

  private final MockHttpServletResponse response;

  /**
   * Constructs an instance of {@code TestAssertContent} with the provided {@code ResultActions}.
   *
   * @param actions the {@code ResultActions} from a performed HTTP request (must not be {@code
   *     null})
   * @throws NullPointerException if the {@code actions} is {@code null}
   * @since 1.0.0
   */
  public TestAssertContentImpl(@NonNull ResultActions actions) {
    this.actions = actions;
    this.response = actions.andReturn().getResponse();
  }

  /**
   * Asserts that the content of the HTTP response as a string is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssertContent assertContentStringIsNotEmpty() {
    try {
      var content = this.response.getContentAsString();
      assertThat(content, is(not(Strings.EMPTY)));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the content of the HTTP response as a string is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssertContent assertContentStringIsEmpty() {
    try {
      var content = this.response.getContentAsString();
      assertThat(content, is(Strings.EMPTY));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the content of the HTTP response matches the given string.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedString the expected content of the response (must not be {@code null})
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssertContent assertContentEquals(@NonNull String expectedString) {
    try {
      var content = this.response.getContentAsString();
      assertThat(content, is(expectedString));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the content of the HTTP response as a byte array is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssertContent assertContentByteIsNotEmpty() {
    try {
      var content = this.response.getContentAsByteArray();
      assertThat(content.length, is(not(0)));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the content of the HTTP response as a byte array is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssertContent assertContentByteIsEmpty() {
    try {
      var content = this.response.getContentAsByteArray();
      assertThat(content.length, is(0));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the content of the HTTP response matches the given byte array.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedByte the expected byte content of the response (must not be {@code null})
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssertContent assertContentEquals(byte[] expectedByte) {
    try {
      var content = this.response.getContentAsByteArray();
      assertThat(content, is(expectedByte));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the content of the HTTP response matches the given object of type {@code T}.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass the class of the expected object (must not be {@code null})
   * @param expectedResponse the expected object (must not be {@code null})
   * @param deserializers optional deserializers for custom object mapping
   * @param <T> the type of the expected response
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  @SuppressWarnings("unchecked")
  public <T> TestAssertContent assertContentEquals(
      @NonNull Class<T> expectedClass,
      @NonNull T expectedResponse,
      JsonDeserializer<T>... deserializers) {

    try {
      var result = this.actions.andReturn();
      var content = TestAssertResultMapper.mapTo(result, expectedClass, deserializers);
      assertThat(content, is(expectedResponse));
    } catch (TestAssertResultMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the content of the HTTP response matches the given list of objects.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass the class of the objects in the list (must not be {@code null})
   * @param expectedResponse the expected list of objects (must not be {@code null})
   * @param deserializers optional deserializers for custom object mapping
   * @param <T> the type of the objects in the expected list
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  @SuppressWarnings("unchecked")
  public <T> TestAssertContent assertContentEquals(
      @NonNull Class<T> expectedClass,
      @NonNull List<T> expectedResponse,
      JsonDeserializer<T>... deserializers) {

    try {
      var result = this.actions.andReturn();
      var content = TestAssertResultMapper.mapToList(result, expectedClass, deserializers);
      assertThat(content, is(expectedResponse));
    } catch (TestAssertResultMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the content of the HTTP response matches the given set of objects.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedClass the class of the objects in the set (must not be {@code null})
   * @param expectedResponse the expected set of objects (must not be {@code null})
   * @param deserializers optional deserializers for custom object mapping
   * @param <T> the type of the objects in the expected set
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  @SuppressWarnings("unchecked")
  public <T> TestAssertContent assertContentEquals(
      @NonNull Class<T> expectedClass,
      @NonNull Set<T> expectedResponse,
      JsonDeserializer<T>... deserializers) {

    try {
      var result = this.actions.andReturn();
      var content = TestAssertResultMapper.mapToSet(result, expectedClass, deserializers);
      assertThat(content, is(expectedResponse));
    } catch (TestAssertResultMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the content of the HTTP response matches the given map.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param keyClass the class of the keys in the map (must not be {@code null})
   * @param valueClass the class of the values in the map (must not be {@code null})
   * @param expectedResponse the expected map of key-value pairs (must not be {@code null})
   * @param deserializers optional deserializers for custom object mapping
   * @param <K> the type of the keys in the map
   * @param <V> the type of the values in the map
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  @SuppressWarnings("unchecked")
  public <K, V> TestAssertContent assertContentEquals(
      @NonNull Class<K> keyClass,
      @NonNull Class<V> valueClass,
      @NonNull Map<K, V> expectedResponse,
      JsonDeserializer<V>... deserializers) {

    try {
      var result = this.actions.andReturn();
      var content = TestAssertResultMapper.mapToMap(result, keyClass, valueClass, deserializers);
      assertThat(content, is(expectedResponse));
    } catch (TestAssertResultMapperException e) {
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
   * @since 1.0.0
   */
  @Override
  public TestAssertHead assertHead() {
    return new TestAssertHeadImpl(this.actions);
  }
}
