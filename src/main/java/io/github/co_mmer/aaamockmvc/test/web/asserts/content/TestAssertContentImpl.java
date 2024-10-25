package io.github.co_mmer.aaamockmvc.test.web.asserts.content;

import static io.github.co_mmer.aaamockmvc.test.web.mapper.TestGenericMapper.mapTo;
import static io.github.co_mmer.aaamockmvc.test.web.mapper.TestGenericMapper.mapToList;
import static io.github.co_mmer.aaamockmvc.test.web.mapper.TestGenericMapper.mapToMap;
import static io.github.co_mmer.aaamockmvc.test.web.mapper.TestGenericMapper.mapToSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.test.web.mapper.exception.TestGenericMapperException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;
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
  private final ObjectMapper objectMapper;

  /**
   * Constructs an instance of {@code TestAssertContent} with the provided {@code ResultActions}.
   *
   * @param actions the {@code ResultActions} from a performed HTTP request (must not be {@code
   *     null})
   * @param objectMapper the {@link ObjectMapper} used for JSON processing (must not be {@code
   *     null})
   * @throws NullPointerException if the {@code actions} is {@code null}
   * @since 1.0.0
   */
  public TestAssertContentImpl(@NonNull ResultActions actions, @NonNull ObjectMapper objectMapper) {
    this.actions = actions;
    this.response = actions.andReturn().getResponse();
    this.objectMapper = objectMapper;
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
  public TestAssertContent assertContentNotEmpty() {
    try {
      assertThat(this.response.getContentAsString().isEmpty(), is(false));
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
  public TestAssertContent assertContentEmpty() {
    try {
      assertThat(this.response.getContentAsString().isEmpty(), is(true));
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
      var content = mapTo(this.objectMapper, result, expectedClass, deserializers);
      assertThat(content, is(expectedResponse));
    } catch (TestGenericMapperException e) {
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
      var content = mapToList(this.objectMapper, result, expectedClass, deserializers);
      assertThat(content, is(expectedResponse));
    } catch (TestGenericMapperException e) {
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
      var content = mapToSet(this.objectMapper, result, expectedClass, deserializers);
      assertThat(content, is(expectedResponse));
    } catch (TestGenericMapperException e) {
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
      var content = mapToMap(this.objectMapper, result, keyClass, valueClass, deserializers);
      assertThat(content, is(expectedResponse));
    } catch (TestGenericMapperException e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the JSON content of the HTTP response has the specified size.
   *
   * <p>This method checks if the length of the JSON content matches the expected size. It uses
   * {@code jsonPath} to validate the presence and length of the JSON array or object. If the actual
   * size does not match the expected size, an assertion failure is triggered.
   *
   * @param expectedSize the expected size of the JSON content (must be greater than or equal to
   *     zero)
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssertContent assertContentSize(int expectedSize) {
    try {
      this.actions.andExpect(jsonPath("$.length()", is(expectedSize)));
    } catch (Exception e) {
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
