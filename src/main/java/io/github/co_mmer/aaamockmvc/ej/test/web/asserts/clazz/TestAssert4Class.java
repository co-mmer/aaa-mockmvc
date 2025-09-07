package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
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
 *  .matchNone(user -> user.id() == 45, user -> user.status() == INACTIVE);
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code actPerform().perform()} has been executed and {@code
 * content().asClass(T)} successfully deserialized the response body.
 *
 * @since 1.0.0
 */
public interface TestAssert4Class<T> {

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
   * Asserts that the provided predicate returns {@code false} for the deserialized value, or that
   * no predicate matches when used with {@code matchNone(p1, p2, ...)}.
   *
   * @param condition1 the predicate to evaluate; must not be {@code null}
   * @param condition2 the predicate to evaluate; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the predicate evaluates to {@code true}
   * @since 2.0.0
   */
  TestAssertLClass matchNone(@NonNull Predicate<T> condition1, @NonNull Predicate<T> condition2);

  /**
   * Asserts that the provided predicate returns {@code false} for the deserialized value, or that
   * no predicate matches when used with {@code matchNone(p1, p2, ...)}.
   *
   * @param condition1 the predicate to evaluate; must not be {@code null}
   * @param condition2 the predicate to evaluate; must not be {@code null}
   * @param condition3 the predicate to evaluate; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the predicate evaluates to {@code true}
   * @since 2.0.0
   */
  TestAssertLClass matchNone(
      @NonNull Predicate<T> condition1,
      @NonNull Predicate<T> condition2,
      @NonNull Predicate<T> condition3);

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

  /**
   * Switches to HTTP header assertions for the same response snapshot.
   *
   * <p>Use this to continue the assertion chain on headers (e.g. {@code containsKey}, {@code
   * containsEntry}). No additional I/O is performed; the headers captured during {@code
   * actPerform().perform()} are reused.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @since 2.0.0
   */
  TestAssertHead headers();
}
