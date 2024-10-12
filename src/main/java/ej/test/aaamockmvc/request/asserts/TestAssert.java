package ej.test.aaamockmvc.request.asserts;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultMatcher;

/**
 * This interface defines a contract for performing various assertions on HTTP response results.
 *
 * <p>The {@code TestAssert} interface provides methods to validate the status and content of HTTP
 * responses, including assertions for status codes, string content, and byte array content.
 *
 * @since 1.0.0
 */
public interface TestAssert {

  /**
   * Asserts that the HTTP response status matches the given {@code HttpStatus}.
   *
   * @param status the expected {@code HttpStatus} of the response (must not be {@code null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  TestAssert assertStatus(@NonNull HttpStatus status);

  /**
   * Asserts that the HTTP response status matches the given status code.
   *
   * @param status the expected status code of the response
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  TestAssert assertStatus(int status);

  /**
   * Asserts that the string content of the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  TestAssert assertStringContentIsNotEmpty();

  /**
   * Asserts that the string content of the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  TestAssert assertStringContentIsEmpty();

  /**
   * Asserts that the string content of the HTTP response matches the expected string.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedString the expected content of the response (must not be {@code null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws NullPointerException if the {@code expectedString} is {@code null}
   * @since 1.0.0
   */
  TestAssert assertEquals(@NonNull String expectedString);

  /**
   * Asserts that the byte array content of the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  TestAssert assertByteContentIsNotEmpty();

  /**
   * Asserts that the byte array content of the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  TestAssert assertByteContentIsEmpty();

  /**
   * Asserts that the byte array content of the HTTP response matches the expected byte array.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedByte the expected byte array content (must not be {@code null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws NullPointerException if the {@code expectedByte} is {@code null}
   * @since 1.0.0
   */
  TestAssert assertEquals(byte[] expectedByte);

  /**
   * Asserts that the object content of the HTTP response matches the expected object, using the
   * provided deserializer(s).
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param expectedResponse the expected object (must not be {@code null})
   * @param deserializers optional deserializers to map the response content
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  <T> TestAssert assertEquals(
      @NonNull Class<T> expectedClass,
      @NonNull T expectedResponse,
      JsonDeserializer<T>... deserializers);

  /**
   * Asserts that the list of objects in the HTTP response matches the expected list, using the
   * provided deserializer(s).
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the objects in the list
   * @param expectedClass the class of the objects in the list (must not be {@code null})
   * @param expectedResponse the expected list of objects (must not be {@code null})
   * @param deserializers optional deserializers to map the response content
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  <T> TestAssert assertEquals(
      @NonNull Class<T> expectedClass,
      @NonNull List<T> expectedResponse,
      JsonDeserializer<T>... deserializers);

  /**
   * Asserts that the set of objects in the HTTP response matches the expected set, using the
   * provided deserializer(s).
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the objects in the set
   * @param expectedClass the class of the objects in the set (must not be {@code null})
   * @param expectedResponse the expected set of objects (must not be {@code null})
   * @param deserializers optional deserializers to map the response content
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  <T> TestAssert assertEquals(
      @NonNull Class<T> expectedClass,
      @NonNull Set<T> expectedResponse,
      JsonDeserializer<T>... deserializers);

  /**
   * Asserts that the map of objects in the HTTP response matches the expected map, using the
   * provided deserializer(s).
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <K> the type of the keys in the map
   * @param <V> the type of the values in the map
   * @param keyClass the class of the map keys (must not be {@code null})
   * @param valueClass the class of the map values (must not be {@code null})
   * @param expectedResponse the expected map (must not be {@code null})
   * @param deserializers optional deserializers to map the response content
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  <K, V> TestAssert assertEquals(
      @NonNull Class<K> keyClass,
      @NonNull Class<V> valueClass,
      @NonNull Map<K, V> expectedResponse,
      JsonDeserializer<V>... deserializers);

  /**
   * Asserts that the HTTP response matches the given {@link ResultMatcher}.
   *
   * @param matcher the {@code ResultMatcher} to be used for validation (must not be {@code null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws NullPointerException if the {@code matcher} is {@code null}
   * @since 1.0.0
   */
  TestAssert assertByResultMatcher(@NonNull ResultMatcher matcher);
}
