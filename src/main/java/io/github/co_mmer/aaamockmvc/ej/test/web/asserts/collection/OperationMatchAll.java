package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import java.util.function.Predicate;
import org.springframework.lang.NonNull;

interface OperationMatchAll<E> {

  /**
   * Asserts that every element in the collection satisfies the given predicate.
   *
   * @param condition predicate applied to each element; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any element does not satisfy the predicate
   * @since 2.0.0
   */
  TestAssert4Collection<E> matchAll(@NonNull Predicate<E> condition);

  /**
   * Asserts that every element in the collection satisfies the given predicate.
   *
   * @param condition1 predicate applied to each element; must not be {@code null}
   * @param condition2 predicate applied to each element; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any element does not satisfy the predicate
   * @since 2.0.0
   */
  TestAssert4Collection<E> matchAll(
      @NonNull Predicate<E> condition1, @NonNull Predicate<E> condition2);

  /**
   * Asserts that every element in the collection satisfies the given predicate.
   *
   * @param condition1 predicate applied to each element; must not be {@code null}
   * @param condition2 predicate applied to each element; must not be {@code null}
   * @param condition3 predicate applied to each element; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any element does not satisfy the predicate
   * @since 2.0.0
   */
  TestAssert4Collection<E> matchAll(
      @NonNull Predicate<E> condition1,
      @NonNull Predicate<E> condition2,
      @NonNull Predicate<E> condition3);

  /**
   * Asserts that every element in the collection satisfies all of the given predicates.
   *
   * @param conditions predicates applied to each element; must not be {@code null} or empty
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any element violates at least one predicate
   * @since 2.0.0
   */
  @SuppressWarnings("unchecked")
  TestAssert4Collection<E> matchAll(@NonNull Predicate<E>... conditions);
}
