package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz;

import java.util.function.Predicate;
import lombok.NonNull;

/**
 * Provides methods for asserting HTTP response class in tests.
 *
 * <ul>
 *   <li>{@link #assertContentIsNotEmpty()}: Asserts that the class of the HTTP response is not
 *       empty.
 *   <li>{@link #assertContentIsEmpty()}: Asserts that the class of the HTTP response is empty.
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.4.0
   * @deprecated Use {@link #assertContentIsNotEmpty()} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  TestAssert2Class assertClassNotEmpty();

  /**
   * Asserts that the string class of the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.4.0
   * @deprecated Use {@link #assertContentIsEmpty()} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.4.0
   * @deprecated Use {@link #assertContentEquals(Class, T)} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   * @since 1.4.0
   * @deprecated Use {@link #assertContentMatchAll(Class, Predicate)} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code conditions} is {@code null}
   * @since 1.4.0
   * @deprecated Use {@link #assertContentMatchAll(Class, Predicate[])} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   * @since 1.4.0
   * @deprecated Use {@link #assertContentMatchAny(Class, Predicate)} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code conditions} is {@code null}
   * @since 1.4.0
   * @deprecated Use {@link #assertContentMatchAny(Class, Predicate[])} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   * @since 1.4.0
   * @deprecated Use {@link #assertContentMatchNone(Class, Predicate)} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code conditions} is {@code null}
   * @since 1.4.0
   * @deprecated Use {@link #assertContentMatchNone(Class, Predicate[])} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @SuppressWarnings("unchecked")
  <T> TestAssertLClass assertClassMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions);

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
  TestAssert2Class assertContentIsNotEmpty();

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
  TestAssertLClass assertContentIsEmpty();

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
  <T> TestAssertLClass assertContentEquals(
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   * @since 1.6.0
   */
  <T> TestAssert3Class assertContentMatchAll(
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code conditions} is {@code null}
   * @since 1.6.0
   */
  @SuppressWarnings("unchecked")
  <T> TestAssert3Class assertContentMatchAll(
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   * @since 1.6.0
   */
  <T> TestAssert4Class assertContentMatchAny(
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code conditions} is {@code null}
   * @since 1.6.0
   */
  @SuppressWarnings("unchecked")
  <T> TestAssert4Class assertContentMatchAny(
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code condition} is {@code null}
   * @since 1.6.0
   */
  <T> TestAssertLClass assertContentMatchNone(
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @throws NullPointerException if {@code expectedClass} or {@code conditions} is {@code null}
   * @since 1.6.0
   */
  @SuppressWarnings("unchecked")
  <T> TestAssertLClass assertContentMatchNone(
      @NonNull Class<T> expectedClass, @NonNull Predicate<T>... conditions);
}
