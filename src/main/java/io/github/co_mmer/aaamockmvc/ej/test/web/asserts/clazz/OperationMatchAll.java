package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz;

import java.util.function.Predicate;
import org.springframework.lang.NonNull;

interface OperationMatchAll<E> {

  /**
   * Asserts that the provided predicate returns {@code true} for the deserialized value.
   *
   * @param condition the predicate to evaluate; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the predicate evaluates to {@code false}
   * @since 2.0.0
   */
  TestAssert3Class<E> matchAll(@NonNull Predicate<E> condition);

  /**
   * Asserts that the provided predicate returns {@code true} for the deserialized value.
   *
   * @param condition1 the predicate to evaluate; must not be {@code null}
   * @param condition2 the predicate to evaluate; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the predicate evaluates to {@code false}
   * @since 2.0.0
   */
  TestAssert3Class<E> matchAll(@NonNull Predicate<E> condition1, @NonNull Predicate<E> condition2);

  /**
   * Asserts that the provided predicate returns {@code true} for the deserialized value.
   *
   * @param condition1 the predicate to evaluate; must not be {@code null}
   * @param condition2 the predicate to evaluate; must not be {@code null}
   * @param condition3 the predicate to evaluate; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the predicate evaluates to {@code false}
   * @since 2.0.0
   */
  TestAssert3Class<E> matchAll(
      @NonNull Predicate<E> condition1,
      @NonNull Predicate<E> condition2,
      @NonNull Predicate<E> condition3);

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
  TestAssert3Class<E> matchAll(@NonNull Predicate<E>... conditions);
}
