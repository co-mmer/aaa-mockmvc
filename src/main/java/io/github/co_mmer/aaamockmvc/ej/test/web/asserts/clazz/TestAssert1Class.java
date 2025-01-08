package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz;

import java.util.function.Predicate;
import lombok.NonNull;

/**
 * Provides methods for asserting HTTP response class in tests.
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
 * </ul>
 *
 * @since 1.4.0
 */
public interface TestAssert1Class {

  /**
   * Asserts that the string class of the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssert2Class} for method chaining
   * @since 1.4.0
   */
  TestAssert2Class assertClassNotEmpty();

  /**
   * Asserts that the string class of the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertLClass} for method chaining
   * @since 1.4.0
   */
  TestAssertLClass assertClassEmpty();

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
   * @return the current instance of {@code TestAssertLClass} for method chaining
   * @since 1.4.0
   */
  <T> TestAssertLClass assertClassEquals(
      @NonNull Class<T> expectedClass, @NonNull T expectedResponse);

  /**
   * Asserts that the class matches all specified conditions.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param condition a predicate that the class must match (must not be {@code null})
   * @return the current instance of {@code TestAssertLClass} for method chaining
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   */
  <T> TestAssert3Class assertClassMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition);

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
   * @return the current instance of {@code TestAssertLClass} for method chaining
   * @throws NullPointerException if {@code expectedClass} or {@code conditions} is {@code null}
   */
  @SuppressWarnings("unchecked")
  <T> TestAssert3Class assertClassMatchAll(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions);

  /**
   * Asserts that the class matches at least one of the specified conditions.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param condition a predicate that the class may match (must not be {@code null})
   * @return the current instance of {@code TestAssertLClass} for method chaining
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   */
  <T> TestAssert4Class assertClassMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition);

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
   * @return the current instance of {@code TestAssertLClass} for method chaining
   * @throws NullPointerException if {@code expectedClass} or {@code conditions} is {@code null}
   */
  @SuppressWarnings("unchecked")
  <T> TestAssert4Class assertClassMatchAny(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions);

  /**
   * Asserts that the class matches none of the specified conditions.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param <T> the type of the expected response object
   * @param expectedClass the class of the expected response object (must not be {@code null})
   * @param condition a predicate that the class must not match (must not be {@code null})
   * @return the current instance of {@code TestAssertLClass} for method chaining
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   */
  <T> TestAssertLClass assertClassMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T> condition);

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
   * @return the current instance of {@code TestAssertLClass} for method chaining
   * @throws NullPointerException if {@code expectedClass} or {@code conditions} is {@code null}
   */
  @SuppressWarnings("unchecked")
  <T> TestAssertLClass assertClassMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions);
}
