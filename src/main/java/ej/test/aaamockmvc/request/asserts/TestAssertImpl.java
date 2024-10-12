package ej.test.aaamockmvc.request.asserts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import com.fasterxml.jackson.databind.JsonDeserializer;
import ej.test.aaamockmvc.request.asserts.mapper.TestAssertResultMapper;
import ej.test.aaamockmvc.request.asserts.mapper.exception.TestAssertResultMapperException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

/**
 * This class provides implementation for assertions on HTTP responses in a testing context. It
 * allows various validations of response status, content, and headers.
 *
 * <p>It is primarily used for validating the results of HTTP requests performed with the {@code
 * MockMvc} framework in a Spring web application context.
 *
 * @since 1.0.0
 */
public final class TestAssertImpl implements TestAssert {

  private final ResultActions actions;
  private final MockHttpServletResponse response;

  /**
   * Constructs an instance of {@code TestAssert} with the provided {@code ResultActions}.
   *
   * @param actions the {@code ResultActions} from a performed HTTP request (must not be {@code
   *     null})
   * @throws NullPointerException if the {@code actions} is {@code null}
   * @since 1.0.0
   */
  public TestAssertImpl(@NonNull ResultActions actions) {
    this.actions = actions;
    this.response = actions.andReturn().getResponse();
  }

  /**
   * Asserts that the status of the HTTP response matches the given {@code HttpStatus}.
   *
   * @param status the expected {@code HttpStatus} of the response (must not be {@code null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssert assertStatus(@NonNull HttpStatus status) {
    assertThat(this.response.getStatus(), is(status.value()));
    return this;
  }

  /**
   * Asserts that the status of the HTTP response matches the given status code.
   *
   * @param status the expected status code of the response
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssert assertStatus(int status) {
    assertThat(this.response.getStatus(), is(status));
    return this;
  }

  /**
   * Asserts that the content of the HTTP response as a string is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssert assertStringContentIsNotEmpty() {
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
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssert assertStringContentIsEmpty() {
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
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssert assertEquals(@NonNull String expectedString) {
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
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssert assertByteContentIsNotEmpty() {
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
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssert assertByteContentIsEmpty() {
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
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssert assertEquals(byte[] expectedByte) {
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
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  @Override
  @SuppressWarnings("unchecked")
  public <T> TestAssert assertEquals(
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
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  @Override
  @SuppressWarnings("unchecked")
  public <T> TestAssert assertEquals(
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
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  @Override
  @SuppressWarnings("unchecked")
  public <T> TestAssert assertEquals(
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
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  @Override
  @SuppressWarnings("unchecked")
  public <K, V> TestAssert assertEquals(
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
   * Asserts that the result of the test request matches the given {@link ResultMatcher}.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param matcher the {@code ResultMatcher} to be used for validation (must not be {@code null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssert assertByResultMatcher(@NonNull ResultMatcher matcher) {
    try {
      this.actions.andExpect(matcher);
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }
}
