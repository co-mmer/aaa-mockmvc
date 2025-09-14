package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import java.util.function.Predicate;
import lombok.NonNull;

/**
 * Assertions for a deserialized {@link java.util.Collection} of elements {@code E}.
 *
 * <p><b>What it does:</b> Provides size, (non-)emptiness, membership and predicate-based checks on
 * the collection produced by a prior {@code content().asCollection(E)} / {@code asList(E)} / {@code
 * asSet(E)} step. The response body has already been deserialized once using the configured mapper
 * and is cached for all subsequent assertions.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *  .get("/api/items");
 *
 * actPerform()
 *  .perform();
 *
 * asserts()
 *  .content()
 *  .asCollection(Item.class)
 *  .isNotEmpty()
 *  .hasSize(3)
 *  .contains(new Item("A"), new Item("B"))
 *  .matchAny(item -> item.active());
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code actPerform().perform()} has been executed and {@code
 * content().asCollection(E)} (or {@code asList}/{@code asSet}) successfully deserialized a JSON
 * array into a Java collection.
 *
 * @since 1.4.0
 */
public interface TestAssert3Collection<E> extends TestAssertMatchAnyCollection<E> {

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

  /**
   * Asserts that no element in the collection satisfies the given predicate.
   *
   * @param condition predicate applied to elements; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any element satisfies the predicate
   * @since 2.0.0
   */
  TestAssertLCollection matchNone(@NonNull Predicate<E> condition);

  /**
   * Asserts that no element in the collection satisfies the given predicate.
   *
   * @param condition1 predicate applied to elements; must not be {@code null}
   * @param condition2 predicate applied to elements; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any element satisfies the predicate
   * @since 2.0.0
   */
  TestAssertLCollection matchNone(
      @NonNull Predicate<E> condition1, @NonNull Predicate<E> condition2);

  /**
   * Asserts that no element in the collection satisfies the given predicate.
   *
   * @param condition1 predicate applied to elements; must not be {@code null}
   * @param condition2 predicate applied to elements; must not be {@code null}
   * @param condition3 predicate applied to elements; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any element satisfies the predicate
   * @since 2.0.0
   */
  TestAssertLCollection matchNone(
      @NonNull Predicate<E> condition1,
      @NonNull Predicate<E> condition2,
      @NonNull Predicate<E> condition3);

  /**
   * Asserts that no element in the collection satisfies any of the given predicates.
   *
   * @param conditions predicates applied to elements; must not be {@code null} or empty
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any element satisfies any predicate
   * @since 2.0.0
   */
  @SuppressWarnings("unchecked")
  TestAssertLCollection matchNone(@NonNull Predicate<E>... conditions);

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
