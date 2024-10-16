package ej.aaamockmvc.test.web.asserts.content;

import com.fasterxml.jackson.databind.JsonDeserializer;
import ej.aaamockmvc.test.web.asserts.head.TestAssertHead;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;

/**
 * This interface defines a set of assertions for validating the content of HTTP responses in a
 * testing context. It allows various checks on the response content, including string, byte array,
 * and object comparisons, as well as validations for lists, sets, and maps.
 *
 * <p>It is primarily used for validating the results of HTTP requests performed with the {@code
 * MockMvc} framework in a Spring web application context.
 *
 * @since 1.0.0
 */
public interface TestAssertContent {

  /**
   * Asserts that the string content of the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  TestAssertContent assertContentNotEmpty();

  /**
   * Asserts that the string content of the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  TestAssertContent assertContentEmpty();

  /**
   * Asserts that the string content of the HTTP response matches the expected string.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedString the expected content of the response (must not be {@code null})
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @throws NullPointerException if the {@code expectedString} is {@code null}
   * @since 1.0.0
   */
  TestAssertContent assertContentEquals(@NonNull String expectedString);

  /**
   * Asserts that the byte array content of the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  TestAssertContent assertContentByteIsNotEmpty();

  /**
   * Asserts that the byte array content of the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  TestAssertContent assertContentByteIsEmpty();

  /**
   * Asserts that the byte array content of the HTTP response matches the expected byte array.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedByte the expected byte array content (must not be {@code null})
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @throws NullPointerException if the {@code expectedByte} is {@code null}
   * @since 1.0.0
   */
  TestAssertContent assertContentEquals(byte[] expectedByte);

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
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  <T> TestAssertContent assertContentEquals(
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
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  <T> TestAssertContent assertContentEquals(
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
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  <T> TestAssertContent assertContentEquals(
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
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  <K, V> TestAssertContent assertContentEquals(
      @NonNull Class<K> keyClass,
      @NonNull Class<V> valueClass,
      @NonNull Map<K, V> expectedResponse,
      JsonDeserializer<V>... deserializers);

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
  TestAssertContent assertContentSize(int expectedSize);

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
  TestAssertHead assertHead();
}
