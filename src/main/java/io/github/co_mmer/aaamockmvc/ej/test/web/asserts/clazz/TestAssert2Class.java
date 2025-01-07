package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import java.util.function.Predicate;
import lombok.NonNull;

/**
 * Provides methods for asserting HTTP response content in tests.
 *
 * <ul>
 *   <li>{@link #assertClassEquals(Class, Object)}: Asserts that the content of the HTTP response
 *       matches the expected object.
 *   <li>{@link #assertClassMatchAll(Class, Predicate)}: Asserts that the content matches all
 *       specified conditions.
 *   <li>{@link #assertClassMatchAll(Class, Predicate...)}: Asserts that the content matches all
 *       specified conditions.
 *   <li>{@link #assertClassMatchAny(Class, Predicate)}: Asserts that the content matches at least
 *       one of the specified conditions.
 *   <li>{@link #assertClassMatchAny(Class, Predicate...)}: Asserts that the content matches at
 *       least one of the specified conditions.
 *   <li>{@link #assertClassMatchNone(Class, Predicate)}: Asserts that the content matches none of
 *       the specified conditions.
 *   <li>{@link #assertClassMatchNone(Class, Predicate...)}: Asserts that the content matches none
 *       of the specified conditions.
 *   <li>{@link #assertHead()}: Provides assertion methods for validating the HTTP response headers.
 * </ul>
 *
 * @since 1.0.0
 */
public interface TestAssert2Class {

  /**
   * Asserts that the object content of the HTTP response matches the expected object, using the
   * provided deserializer(s).
   *
   * <p>As of version 1.3.0, both the actual and expected response content are normalized using
   * Unicode Normalization Form C (NFC) to ensure consistent text representation across different
   * Unicode formats.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param expectedResponse the expected object (must not be {@code null})
   * @return the current instance of {@code TestAssertLClass} for method chaining
   * @since 1.0.0
   */
  <T> TestAssertLClass assertClassEquals(
      @NonNull Class<T> expectedClass, @NonNull T expectedResponse);

  /**
   * Asserts that the content matches all specified conditions.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param condition a predicate that the content must match (must not be {@code null})
   * @return the current instance of {@code TestAssertLClass} for method chaining
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   */
  <T> TestAssert3Class assertClassMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition);

  /**
   * Asserts that the content matches all specified conditions.
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
  <T> TestAssert3Class assertClassMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions);

  /**
   * Asserts that the content matches at least one of the specified conditions.
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
  <T> TestAssert4Class assertClassMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition);

  /**
   * Asserts that the content matches at least one of the specified conditions.
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
  <T> TestAssert4Class assertClassMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions);

  /**
   * Asserts that the content matches none of the specified conditions.
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
  <T> TestAssertLClass assertClassMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition);

  /**
   * Asserts that the content matches none of the specified conditions.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param conditions a varargs array of predicates that the content must not match (must not be
   *     {@code null})
   * @return the current instance of {@code TestAssertLClass} for method chaining
   * @throws NullPointerException if {@code expectedClass} or {@code conditions} is {@code null}
   */
  <T> TestAssertLClass assertClassMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions);

  /**
   * Provides assertion methods for validating the HTTP response headers.
   *
   * <p>This method returns an instance of {@code TestAssertHead}, which provides assertion methods
   * for validating the headers of the HTTP response, such as checking for the presence or absence
   * of specific headers and comparing header values.
   *
   * @return an instance of {@code TestAssertHead} for asserting the response headers
   * @since 1.0.0
   */
  TestAssertHead assertHead();
}
