package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import java.util.function.Predicate;
import lombok.NonNull;

/**
 * Provides assertion methods for validating classes in the HTTP response.
 *
 * <ul>
 *   <li>{@link #assertContentMatchAny(Class, Predicate)}: Asserts that the class matches at least
 *       one of the specified conditions.
 *   <li>{@link #assertContentMatchAny(Class, Predicate...)}: Asserts that the class matches at
 *       least one of the specified conditions.
 *   <li>{@link #assertContentMatchNone(Class, Predicate)}: Asserts that the class matches none of
 *       the specified conditions.
 *   <li>{@link #assertContentMatchNone(Class, Predicate...)}: Asserts that the class matches none
 *       of the specified conditions.
 *   <li>{@link #assertHead()}: Asserts properties of response headers for HTTP HEAD requests.
 * </ul>
 *
 * @since 1.4.0
 */
public interface TestAssert3Class {

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

  /**
   * Asserts that the HTTP response is valid for a HEAD request.
   *
   * <p>This method returns an instance of {@code TestAssertHead} for asserting the headers of the
   * HTTP response. It allows various validations of response headers, such as checking for the
   * presence or absence of specific headers and comparing header values.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state.
   * @since 1.4.0
   */
  TestAssertHead assertHead();
}
