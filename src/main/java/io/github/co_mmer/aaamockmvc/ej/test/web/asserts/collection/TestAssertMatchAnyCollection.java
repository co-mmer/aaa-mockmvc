package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import java.util.function.Predicate;
import lombok.NonNull;

public interface TestAssertMatchAnyCollection<E> {

  /**
   * Asserts that at least one element in the collection satisfies the given predicate.
   *
   * @param condition predicate applied to elements; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if no element satisfies the predicate
   * @since 2.0.0
   */
  TestAssert5Collection<E> matchAny(@NonNull Predicate<E> condition);

  /**
   * Asserts that at least one element in the collection satisfies the given predicate.
   *
   * @param condition1 predicate applied to elements; must not be {@code null}
   * @param condition2 predicate applied to elements; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if no element satisfies the predicate
   * @since 2.0.0
   */
  TestAssert5Collection<E> matchAny(
      @NonNull Predicate<E> condition1, @NonNull Predicate<E> condition2);

  /**
   * Asserts that at least one element in the collection satisfies the given predicate.
   *
   * @param condition1 predicate applied to elements; must not be {@code null}
   * @param condition2 predicate applied to elements; must not be {@code null}
   * @param condition3 predicate applied to elements; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if no element satisfies the predicate
   * @since 2.0.0
   */
  TestAssert5Collection<E> matchAny(
      @NonNull Predicate<E> condition1,
      @NonNull Predicate<E> condition2,
      @NonNull Predicate<E> condition3);

  /**
   * Asserts that at least one element in the collection satisfies any of the given predicates.
   *
   * @param conditions predicates applied to elements; must not be {@code null} or empty
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if no element satisfies any predicate
   * @since 2.0.0
   */
  @SuppressWarnings("unchecked")
  TestAssert5Collection<E> matchAny(@NonNull Predicate<E>... conditions);
}
