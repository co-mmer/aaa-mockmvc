package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz;

import java.util.function.Predicate;
import org.springframework.lang.NonNull;

interface OperationMatchNone<T> {

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
}
