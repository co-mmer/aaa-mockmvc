package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz;

import java.util.function.Predicate;
import lombok.NonNull;

/**
 * Assertions for a deserialized content object of type {@code T}.
 *
 * <p><b>What it does:</b> Provides type-safe checks on the already mapped response body (e.g.,
 * nullability, equality, predicate-based matching). The body was deserialized once in the preceding
 * {@code content().asClass(T)} step and is cached for all subsequent assertions.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *  .get("/api/user/42");
 *
 * actPerform()
 *  .perform();
 *
 * asserts()
 *  .content()
 *  .asClass(User.class)
 *  .isNotNull()
 *  .matchAll(user -> user.id() == 42, user -> user.status() == ACTIVE);
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code actPerform().perform()} has been executed and {@code
 * content().asClass(T)} successfully deserialized the response body.
 *
 * @since 1.0.0
 */
public interface TestAssert1Class<T> {

  /**
   * Asserts that the deserialized value is not {@code null}.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the value is {@code null}
   * @since 2.0.0
   */
  TestAssert2Class<T> isNotNull();

  /**
   * Asserts that the deserialized value is {@code null}.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the value is not {@code null}
   * @since 2.0.0
   */
  TestAssert2Class<T> isNull();

  /**
   * Asserts that the deserialized value is equal to the given {@code expected} value.
   *
   * <p>Note: For predictable text comparison, both actual and expected values are normalized using
   * Unicode Normalization Form C (NFC) before comparison where applicable.
   *
   * @param expectedResponse the expected value; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the values are not equal
   * @since 2.0.0
   */
  TestAssertLClass isEqualTo(@NonNull T expectedResponse);

  /**
   * Asserts that the provided predicate returns {@code true} for the deserialized value.
   *
   * @param condition the predicate to evaluate; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the predicate evaluates to {@code false}
   * @since 2.0.0
   */
  TestAssert3Class<T> matchAll(@NonNull Predicate<T> condition);

  /**
   * Asserts that all provided predicates return {@code true} for the deserialized value.
   *
   * @param conditions predicates to evaluate; must not be {@code null} or empty
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any predicate evaluates to {@code false}
   * @since 2.0.0
   */
  @SuppressWarnings("unchecked")
  TestAssert3Class<T> matchAll(@NonNull Predicate<T>... conditions);

  /**
   * Asserts that the provided predicate returns {@code true} for the deserialized value, or that at
   * least one predicate matches when used with {@code matchAny(p1, p2, ...)}.
   *
   * @param condition the predicate to evaluate; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the predicate evaluates to {@code false}
   * @since 2.0.0
   */
  TestAssert4Class<T> matchAny(@NonNull Predicate<T> condition);

  /**
   * Asserts that at least one of the provided predicates returns {@code true} for the deserialized
   * value.
   *
   * @param conditions predicates to evaluate; must not be {@code null} or empty
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if all predicates evaluate to {@code false}
   * @since 2.0.0
   */
  @SuppressWarnings("unchecked")
  TestAssert4Class<T> matchAny(@NonNull Predicate<T>... conditions);

  /**
   * Asserts that the provided predicate returns {@code false} for the deserialized value, or that
   * no predicate matches when used with {@code matchNone(p1, p2, ...)}.
   *
   * @param condition the predicate to evaluate; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the predicate evaluates to {@code true}
   * @since 2.0.0
   */
  TestAssertLClass matchNone(@NonNull Predicate<T> condition);

  /**
   * Asserts that none of the provided predicates returns {@code true} for the deserialized value.
   *
   * @param conditions predicates to evaluate; must not be {@code null} or empty
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any predicate evaluates to {@code true}
   * @since 2.0.0
   */
  @SuppressWarnings("unchecked")
  TestAssertLClass matchNone(@NonNull Predicate<T>... conditions);
}
