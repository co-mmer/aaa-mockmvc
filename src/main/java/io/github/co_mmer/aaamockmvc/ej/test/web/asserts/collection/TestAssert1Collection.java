package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import java.util.Collection;
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
public interface TestAssert1Collection<E> {

  /**
   * Asserts that the collection is not empty.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the collection is empty
   * @since 2.0.0
   */
  TestAssert2Collection<E> isNotEmpty();

  /**
   * Asserts that the collection is empty.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the collection is not empty
   * @since 2.0.0
   */
  TestAssertLCollection isEmpty();

  /**
   * Asserts that the collection has the given size.
   *
   * @param expectedSize the expected number of elements
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the size differs from {@code expectedSize}
   * @since 2.0.0
   */
  TestAssert2Collection<E> hasSize(int expectedSize);

  /**
   * Asserts that the collection is equal to the given collection (same elements and order).
   *
   * <p>Note: For predictable text comparison, both actual and expected values are normalized using
   * Unicode Normalization Form C (NFC) where applicable.
   *
   * @param expectedCollection the expected collection; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the collections are not equal
   * @since 2.0.0
   */
  TestAssertLCollection isEqualTo(@NonNull Collection<E> expectedCollection);

  /**
   * Asserts that the collection contains all of the given elements (order does not matter;
   * additional elements may exist).
   *
   * @param expectedElements the elements that must be present; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any expected element is missing
   * @since 2.0.0
   */
  TestAssert3Collection<E> contains(@NonNull Collection<E> expectedElements);

  /**
   * Asserts that the collection contains all of the given elements (order does not matter;
   * additional elements may exist).
   *
   * @param expectedElements the elements that must be present; must not be {@code null} or empty
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any expected element is missing
   * @since 2.0.0
   */
  @SuppressWarnings("unchecked")
  TestAssert3Collection<E> contains(@NonNull E... expectedElements);

  /**
   * Asserts that the collection contains exactly the elements of {@code expectedCollection},
   * regardless of order (multiplicity must match; no extras).
   *
   * @param expectedCollection the expected elements; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if elements differ (ignoring order)
   * @since 2.0.0
   */
  TestAssert3Collection<E> containsAnyOrder(@NonNull Collection<E> expectedCollection);

  /**
   * Asserts that none of the given elements are present in the collection.
   *
   * @param unexpectedElements elements that must be absent; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any unexpected element is present
   * @since 2.0.0
   */
  TestAssert3Collection<E> notContains(@NonNull Collection<E> unexpectedElements);

  /**
   * Asserts that none of the given elements are present in the collection.
   *
   * @param unexpectedElements elements that must be absent; must not be {@code null} or empty
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any unexpected element is present
   * @since 2.0.0
   */
  @SuppressWarnings("unchecked")
  TestAssert3Collection<E> notContains(@NonNull E... unexpectedElements);

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
}
